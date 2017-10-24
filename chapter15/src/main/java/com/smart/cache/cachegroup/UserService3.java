package com.smart.cache.cachegroup;

import com.smart.cache.domain.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by shliangyan on 2017/10/20.
 */
@Service(value = "cacheGroupUserService")
public class UserService3  {
    private Map<Integer, User> ppl = new HashMap<>();
    {
        ppl.put(1, new Member("1", "21"));
        ppl.put(2, new Visitor("2", "w2"));
    }

    @Caching(cacheable = {
            @Cacheable(value = "members", condition = "#user instanceof T(com.smart.cache.cachegroup.Member)"),
            @Cacheable(value = "visitors", condition = "#user instanceof T(com.smart.cache.cachegroup.Visitor)")
    })
    public User getUser(User user) {
        return ppl.get(Integer.valueOf(user.getUserId()));
    }
}
