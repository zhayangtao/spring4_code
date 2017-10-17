package com.smart.cache.simplecache;

import com.smart.cache.domain.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Created by shliangyan on 2017/10/17.
 */
@Service(value = "userService2")
public class Userservice2 {
    /**
     * 使用一个名为users的缓存
     * @param userId
     * @return
     */
    @Cacheable(cacheNames = "users")
    public User getUserById(String userId) {
        System.out.println("real query user..." + userId);
        return getFromDB(userId);
    }

    private User getFromDB(String userId) {
        System.out.println("real querying db..." + userId);
        return new User(userId);
    }
}
