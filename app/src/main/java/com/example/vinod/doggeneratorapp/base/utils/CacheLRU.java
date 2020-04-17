package com.example.vinod.doggeneratorapp.base.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

public class CacheLRU<K, V> {

    private int maxSize;
    private LinkedHashMap<K, V> cache;
    private ArrayList<K> used;

    public CacheLRU(int maxSize) {
        if (maxSize < 1) {
            throw new IllegalArgumentException("Max size should be greater than zero");
        }
        this.maxSize = maxSize;
        //LinkedHashMap is used to maintain insertion order of image urls
        this.cache = new LinkedHashMap<>(maxSize);
        this.used = new ArrayList<>(maxSize);
    }

    public CacheLRU() {
        this(20);
    }

    public synchronized V get(K key) {
        updateUsed(key);
        return cache.get(key);
    }

    public synchronized void put(K key, V value) {
        if (cache.size() >= maxSize) {
            cache.remove(used.get(0));
            used.remove(0);
        }
        updateUsed(key);
        cache.put(key, value);
    }

    public synchronized void putAllValues(ArrayList<V> list) {
        ArrayList<K> keysList = (ArrayList<K>) new ArrayList<>(list);
        for (int i = 0; i < list.size(); i++) {
            put(keysList.get(i), list.get(i));
        }
    }

    private void updateUsed(K key) {
        used.remove(key);
        used.add(key);
    }

    public synchronized int size() {
        return cache.size();
    }

    public synchronized V remove(K key) {
        used.remove(key);
        return cache.remove(key);
    }

    public synchronized void clear() {
        cache.clear();
        used.clear();
    }

    public Set<K> keySet() {
        return cache.keySet();
    }

    public ArrayList<V> values() {
        ArrayList<V> list = new ArrayList<>(cache.values());
        Collections.reverse(list);
        return list;
    }

}
