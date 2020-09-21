package com.shandilya.cache.storage;

import com.shandilya.cache.exceptions.NotFoundException;
import com.shandilya.cache.exceptions.StorageFullException;

import java.util.HashMap;
import java.util.Map;

public class HashMapBackedStorage<K,V> implements CacheStorage<K,V> {

    private Map<K,V> storage;
    private final Integer capacity;

    public HashMapBackedStorage(Integer capacity) {
        this.capacity = capacity;
        storage = new HashMap<>();
    }

    @Override
    public V get(K key) throws NotFoundException {
        if (!storage.containsKey(key)) throw new NotFoundException(key + "-- Doesn't Exist in the cache!");

        return storage.get(key);
    }

    @Override
    public void remove(K key) throws NotFoundException {
        if (!storage.containsKey(key)) throw new NotFoundException(key + "-- Doesn't Exist in the cache!");
        storage.remove(key);
    }

    @Override
    public void add(K key, V value) throws StorageFullException {
        if (isStorageFull()) throw new StorageFullException("Cache Capacity is Full...");
        storage.put(key, value);
    }

    private boolean isStorageFull() {
        return storage.size() == capacity;
    }
}