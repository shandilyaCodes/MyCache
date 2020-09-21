package com.shandilya.cache.eviction;

import com.shandilya.dsUtils.DoublyLinkedList;
import com.shandilya.dsUtils.DoublyLinkedListNode;

import java.util.HashMap;
import java.util.Map;

public class LRUEvictionPolicy<K> implements EvictionPolicy<K> {

    private DoublyLinkedList<K> dll;
    private Map<K, DoublyLinkedListNode<K>> mapper;

    public LRUEvictionPolicy() {
        dll = new DoublyLinkedList<>();
        mapper = new HashMap<>();
    }

    /**
     * As per the implementation of the LRU Policy the first element in the DLL
     * is least recently used, whereas the last element in the DLL would be most
     * Recently used. For eviction of a key we find the first key and remove it
     */
    @Override
    public K evictKey() {
        DoublyLinkedListNode<K> first = dll.getFirstNode();
        if (first == null) {
            return null;
        }
        dll.detachNode(first);
        return first.getElement();
    }

    @Override
    public void keyAccessed(K key) {
        // If the already present element is accessed
        // Pluck it from the linked list and add it in last
        if (mapper.containsKey(key)) {
            dll.detachNode(mapper.get(key));
            dll.addNodeAtLast(mapper.get(key));
        } else {
            // If the element is not present in the cache and its accessed
            // Which means we are putting a new element inside the cache
            DoublyLinkedListNode<K> newNode = dll.addElementAtLast(key);
            mapper.put(key, newNode);
        }
    }
}
