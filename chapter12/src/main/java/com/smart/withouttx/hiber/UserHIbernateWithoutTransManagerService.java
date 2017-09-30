package com.smart.withouttx.hiber;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by shliangyan on 2017/9/30.
 */
@Service("hiberService")
public class UserHIbernateWithoutTransManagerService {
    @Autowired
    private HibernateTemplate hibernateTemplate;

    public void addScore(String userName, int toAdd) {

    }
}
