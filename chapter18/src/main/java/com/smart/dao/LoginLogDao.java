package com.smart.dao;

import com.smart.domain.LoginLog;
import org.springframework.stereotype.Repository;

/**
 * Created by shliangyan on 2017/11/7.
 */
@Repository
public class LoginLogDao extends BaseDao{
    public void save(LoginLog loginLog) {
        this.getHibernateTemplate().save(loginLog);
    }
}
