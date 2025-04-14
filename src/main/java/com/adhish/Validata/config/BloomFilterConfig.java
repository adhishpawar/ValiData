package com.adhish.Validata.config;

import com.adhish.Validata.bloom.CustomBloomFilter;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import org.checkerframework.checker.units.qual.C;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.charset.Charset;

@Configuration
public class BloomFilterConfig {

    @Bean
    public CustomBloomFilter bloomFilter() {
        return new CustomBloomFilter(10000, new int[]{7, 11, 13, 31, 37, 61});
    }
}
