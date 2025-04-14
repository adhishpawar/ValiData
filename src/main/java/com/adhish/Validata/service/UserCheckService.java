package com.adhish.Validata.service;

import com.adhish.Validata.Entity.User;
import com.adhish.Validata.bloom.CustomBloomFilter;
import com.adhish.Validata.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserCheckService {

    @Autowired
    private CustomBloomFilter bloomFilter;

    @Autowired
    private UserRepository userRepo;

    public String checkUsername(String username) {

        // 1️⃣ Bloom Filter quick lookup
        if (bloomFilter.mightContain(username)) {
            // Potential match → Confirm via DB
            Optional<User> user = userRepo.findByUsername(username);
            if (user.isPresent()) {
                return "Username is Taken ✅ (from Bloom Filter)";
            } else {
                return "Username is available ✅ (false positive from Bloom Filter)";
            }
        } else {
            // Definitely not present in Bloom → Confirm and Add
            Optional<User> user = userRepo.findByUsername(username);
            if (user.isPresent()) {
                bloomFilter.add(username); // Update Bloom to reduce future misses
                return "Username is Taken (Confirmed By DB)";
            } else {
                return "Username is available ✅";
            }
        }
    }

    public boolean registerUsername(String username) {
        Optional<User> userOptional = userRepo.findByUsername(username);

        if (userOptional.isPresent()) {
            bloomFilter.add(username); // Refresh Bloom Filter if somehow missed
            return false; // Already taken
        }

        // Safe to add
        User user = new User();
        user.setUsername(username);
        userRepo.save(user);

        bloomFilter.add(username); // Add to Bloom after DB insert
        return true;
    }
}
