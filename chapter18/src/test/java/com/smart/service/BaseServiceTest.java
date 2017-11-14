package com.smart.service;

import org.springframework.orm.hibernate4.HibernateTemplate;
import org.unitils.UnitilsTestNG;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBean;

/**
 * Created by shliangyan on 2017/11/7.
 */
@SpringApplicationContext({"xiaochun-service.xml", "xiaochun-dao.xml"})
public class BaseServiceTest extends UnitilsTestNG{
    @SpringBean("hibernateTemplate")
    public HibernateTemplate hibernateTemplate;
}
