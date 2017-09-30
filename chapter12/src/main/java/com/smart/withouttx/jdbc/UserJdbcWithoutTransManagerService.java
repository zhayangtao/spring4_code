package com.smart.withouttx.jdbc;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.event.spi.SaveOrUpdateEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by shliangyan on 2017/9/30.
 */
@Service("userService")
public class UserJdbcWithoutTransManagerService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void addScore(String userName, int toAdd) {
        String sql = "UPDATE t_user u SET u.score = u.score + ? WHERE user_name = ?";
        jdbcTemplate.update(sql, toAdd, userName);
    }

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("jdbcWithoutTx.xml");
        UserJdbcWithoutTransManagerService service = context.getBean("userService", UserJdbcWithoutTransManagerService.class);
        JdbcTemplate jdbcTemplate = context.getBean("jdbcTemplate", JdbcTemplate.class);
        BasicDataSource basicDataSource = (BasicDataSource) jdbcTemplate.getDataSource();
        System.out.println("autoCommit:" + basicDataSource.getDefaultAutoCommit());
        jdbcTemplate.execute("INSERT INTO t_user(user_name,password,score,last_logon_time) " +
                "VALUES ('tom','123456',10," + System.currentTimeMillis() + ")");
        service.addScore("tom", 20);
        int score = jdbcTemplate.queryForObject("SELECT score FROM t_user WHERE user_name = 'tom'", Integer.class);
        System.out.println("score:" + score);
        jdbcTemplate.execute("DELECE FROM t_user WHERE user_name='tom'");
    }
}
