package com.smart.cache.initcache;

import com.smart.cache.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by shliangyan on 2017/10/20.
 */
@Service(value = "initUserService")
public class UserService4 {
    private Map<Integer, User> userMap = new HashMap<>();
    {
        userMap.put(1, new User("1", "w1"));
        userMap.put(2, new User("2", "w2"));
    }

    @Autowired
    private CacheManager cacheManager;

    @PostConstruct
    public void setup() {
        Cache userCache = cacheManager.getCache("users");
        for (Integer key : userMap.keySet()) {
            userCache.put(key, userMap.get(key));
        }
    }

    @Cacheable(value = "users")
    public User getUser(int id) {
        System.out.println("User with id " + id + " requested.");
        return userMap.get(id);
    }
}
