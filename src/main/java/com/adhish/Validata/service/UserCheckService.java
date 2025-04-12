package com.adhish.Validata.service;

import com.adhish.Validata.Entity.User;
import com.adhish.Validata.config.BloomFilterConfig;
import com.adhish.Validata.repo.UserRepository;
import com.google.common.hash.BloomFilter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserCheckService {

    @Autowired
    private BloomFilter<String> bloomFilter;

    @Autowired
    private UserRepository userRepo;

    public String checkUsername(String username) {

        //1 --> Check bloom Filter for Quick in memory lookup

        if (bloomFilter.mightContain(username)) {
            return "Username is Taken ✅ (from Bloom Filter)";
        }

        if (!bloomFilter.mightContain(username)) {
            Optional<User> user = userRepo.findByUsername(username);
            if (user.isPresent()) {
                bloomFilter.put(username);
                return "Username is Taken (Confirmed By DB)";
            }
        }

        return "Username is available ✅ (false positive from Bloom Filter)";

    }

    public boolean registerUsername(String username) {
        User user = new User();
        user.setUsername(username);

        if (!bloomFilter.mightContain(username)) {
            Optional<User> byUsername = userRepo.findByUsername(username);
            if (byUsername.isPresent()) {
                bloomFilter.put(username);
                return false;
            }

            userRepo.save(user);
            bloomFilter.put(username);
            return true;
        }
        return false;
    }
}

