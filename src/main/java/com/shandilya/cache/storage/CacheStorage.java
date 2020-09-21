package com.shandilya.cache.storage;

import com.shandilya.cache.exceptions.NotFoundException;
import com.shandilya.cache.exceptions.StorageFullException;

public interface CacheStorage<K,V> {
    V get(K key) throws NotFoundException;
    void remove(K key) throws NotFoundException;
    void add(K key, V value) throws StorageFullException;
}