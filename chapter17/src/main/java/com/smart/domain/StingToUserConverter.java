package com.smart.domain;

import org.springframework.core.convert.converter.Converter;

/**
 * Created by shliangyan on 2017/11/2.
 */
public class StingToUserConverter implements Converter<String, User> {
    @Override
    public User convert(String s) {
        User user = new User();
        if (s != null) {
            String [] items = s.split(":");
            user.setUserName(items[0]);
            user.setPassword(items[1]);
            user.setRealName(items[2]);
        }
        return user;
    }
}
