package com.adhish.Validata.config;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.charset.Charset;

@Configuration
public class BloomFilterConfig {

    @Bean
    public BloomFilter<String> bloomFilter(){
        return BloomFilter.create(Funnels.stringFunnel(Charset.defaultCharset()),10000,0.01);
    }
}
