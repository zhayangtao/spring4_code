package com.smart.dao;

import com.smart.domain.User;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Created by shliangyan on 2017/11/7.
 */
@Repository
public class UserDao extends BaseDao<User> {
    private static final String GET_USER_BY_USERNAME = "from User u where userName = ?";
    private static final String QUERY_USER_BY_USERNAME = "from User u where u.userName like ?";

    public User getUserByUserName(String userName) {
        List<User> users = (List<User>) getHibernateTemplate().find(GET_USER_BY_USERNAME, userName);
        if (CollectionUtils.isEmpty(users)) {
            return null;
        }
        return users.get(0);
    }

    public List<User> queryUserByUserName(String userName) {
        return (List<User>) getHibernateTemplate().find(QUERY_USER_BY_USERNAME, userName + "%");
    }
}
