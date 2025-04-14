package com.adhish.Validata.bloom;

import java.util.BitSet;

public class CustomBloomFilter {

    private BitSet bitset;
    private int size;
    private int[] seeds;

    public CustomBloomFilter(int size, int[] seeds)
    {
        this.size = size;
        this.seeds = seeds;
        this.bitset = new BitSet(size);
    }

    private int getHash(String value,int seed){
        int result = 0;
        for(char c : value.toCharArray()){
            result = seed*result + c;
        }
        return (size - 1) & result;
    }

    public void add(String value){
        for(int seed : seeds){
            int hash = getHash(value,seed);
            bitset.set(hash,true);
        }
    }

    public boolean mightContain(String value){
        for(int seed : seeds){
            int hash = getHash(value,seed);
            if(!bitset.get(hash)){
                return false;
            }
        }
        return true;
    }
}
