package com.shandilya;

import com.shandilya.cache.Cache;
import com.shandilya.cache.factories.CacheFactory;

public class Runner {

    public static void main(String[] args) {
        CacheFactory<Integer, Integer> cacheFactory = new CacheFactory<>();
        final Cache<Integer, Integer> lruCache = cacheFactory.lruCache(3);

        lruCache.put(1,11);
        lruCache.put(2,22);
        lruCache.put(3,33);
        lruCache.put(4,33);
        
        System.out.println("Cache Fetch : " + lruCache.get(1));
    }
}