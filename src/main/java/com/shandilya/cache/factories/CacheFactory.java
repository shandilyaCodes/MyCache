package com.shandilya.cache.factories;

import com.shandilya.cache.Cache;
import com.shandilya.cache.eviction.LRUEvictionPolicy;
import com.shandilya.cache.storage.HashMapBackedStorage;

public class CacheFactory<K,V> {

    public Cache<K,V> lruCache(final int capacity) {
        return new Cache<>(new LRUEvictionPolicy<>(), new HashMapBackedStorage<>(capacity));
    }
}