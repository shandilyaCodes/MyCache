package com.shandilya.cache;

import com.shandilya.cache.eviction.EvictionPolicy;
import com.shandilya.cache.exceptions.NotFoundException;
import com.shandilya.cache.exceptions.StorageFullException;
import com.shandilya.cache.storage.CacheStorage;

public class Cache<K,V> {

    private final EvictionPolicy<K> evictionPolicy;
    private final CacheStorage<K,V> cacheStorage;

    public Cache(EvictionPolicy<K> evictionPolicy, CacheStorage<K, V> cacheStorage) {
        this.evictionPolicy = evictionPolicy;
        this.cacheStorage = cacheStorage;
    }

    /**
     * put() method puts an entry inside the cache
     * If cache contains the availability for a new entry
     * It stores the key in there and applies the Key Eviction Policy via keyAccessed method
     *
     * In the case when the cache is full extract out the key by applying the eviction policy
     * Remove the evicted key out of the map storage and now put the new entry
     */
    public void put(K key, V value) {
        try {
            this.cacheStorage.add(key, value);
            this.evictionPolicy.keyAccessed(key);
        } catch (StorageFullException sfe) {
            System.out.println("Storage has been filled up, Initiating Eviction...");
            K keyToRemove = evictionPolicy.evictKey();
            if (keyToRemove == null) {
                throw new RuntimeException("Unexpected State! Storage Full & No Key To Evict!");
            }
            cacheStorage.remove(keyToRemove);
            System.out.println("Creating Space by Evicting Item : " + keyToRemove);
            put(key, value);
        }
    }

    /**
     * get() method tries to look for an entry in the map
     * First it tries to find an element in the map storage
     * If it's found then the eviction policy is applied to it via keyAccessed method
     * And finally we return the value from the storage being accessed
     *
     * If the element is not found we get to the Exception block and return null
     * No eviction policy is applied as such
     */
    public V get(K key) {
        try {
            V value = cacheStorage.get(key);
            evictionPolicy.keyAccessed(key);
            return value;
        } catch (NotFoundException nfe) {
            System.out.println("Tried to Access a non existing Key...");
            return null;
        }
    }
}