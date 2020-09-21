package com.shandilya.cache.eviction;

public interface EvictionPolicy<K> {
    K evictKey();
    void keyAccessed(K key);
}