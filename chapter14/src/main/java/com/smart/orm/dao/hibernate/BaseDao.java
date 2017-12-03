package com.smart.orm.dao.hibernate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;

/**
 * Created by shliangyan on 2017/10/13.
 */
public class BaseDao {

    @Autowired
    public HibernateTemplate hibernateTemplate;
}
