package com.smart.cache.mycache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by shliangyan on 2017/10/17.
 */
public class CacheManager<T> {
    private Map<String , T> cache = new ConcurrentHashMap<>();

    public T getValue(Object key) {
        return cache.get(key);
    }

    public void addOrUpdateCache(String key, T value) {
        cache.put(key, value);
    }

    /**
     * 删除记录
     * @param key
     */
    public void evictCache(String key) {
        if (cache.containsKey(key)) {
            cache.remove(key);
        }
    }

    /**
     * 删除所有记录
     */
    public void evictCache() {
        cache.clear();
    }
}
