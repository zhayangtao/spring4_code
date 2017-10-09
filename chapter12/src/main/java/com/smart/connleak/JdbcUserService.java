package com.smart.connleak;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by shliangyan on 2017/9/30.
 */
@Service
public class JdbcUserService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    public void logon(String userName) {
        try {
            Connection connection = jdbcTemplate.getDataSource().getConnection();
            String sql = "UPDATE t_user SET last_logon_time=? WHERE user_name=?";
            jdbcTemplate.update(sql, System.currentTimeMillis(), userName);
            Thread.sleep(1000);
        } catch (SQLException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void asynchrLogon(JdbcUserService userService, String userName) {
        UserServiceRunner runner = new UserServiceRunner(userService, userName);
        runner.start();
    }

    private static class UserServiceRunner extends Thread {
        private JdbcUserService userService;
        private String userName;

        public UserServiceRunner(JdbcUserService userService, String userName) {
            this.userService = userService;
            this.userName = userName;
        }

        @Override
        public void run() {
            userService.logon(userName);
        }
    }

    public static void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void reportConn(BasicDataSource basicDataSource) {
        System.out.println("连接数[active:idle]-[" + basicDataSource.getNumActive() + ":" + basicDataSource.getNumIdle() + "]");
    }

    public static void main(String[] args) {


    }
}
