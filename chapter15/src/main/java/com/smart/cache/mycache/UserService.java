package com.smart.cache.mycache;

import com.smart.cache.domain.User;

/**
 * Created by shliangyan on 2017/10/17.
 */
public class UserService {
    private CacheManager<User> cacheManager;

    public UserService() {
        cacheManager = new CacheManager<>();
    }

    public User getUserById(String userId) {
        User user = cacheManager.getValue(userId);
        if (user != null) {
            System.out.println("get from cache..." + userId);
            return user;
        }
        user = getFromDB(userId);
        if (user != null) {
            cacheManager.addOrUpdateCache(userId, user);
        }
        return user;

    }

    public void reload() {
        cacheManager.evictCache();
    }

    private User getFromDB(String userId) {
        System.out.println("real querying db..." + userId);
        return new User(userId);
    }
}
