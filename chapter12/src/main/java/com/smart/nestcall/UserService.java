package com.smart.nestcall;

import com.smart.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by shliangyan on 2017/9/30.
 */
public class UserService extends BaseService {
    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Autowired
    private ScoreService scoreService;

    public void logon(String userName) {
        System.out.println("before userService.updateLastLogonTime...");
    }

    public void updateLastLogonTime(String userName) {
        User user = hibernateTemplate.get(User.class, userName);
        user.setLastLogonTime(System.currentTimeMillis());
        hibernateTemplate.update(user);
        hibernateTemplate.flush();
    }
}
