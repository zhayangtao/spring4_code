package com.smart.connleak;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;

/**
 * Created by shliangyan on 2017/9/30.
 */
@Service
public class JdbcUserService2 {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    public void logon(String userName) {
        try {
            Connection connection = DataSourceUtils.getConnection(jdbcTemplate.getDataSource());
            String sql = "UPDATE t_user SET last_logon_time=? WHERE user_name=?";
            jdbcTemplate.update(sql, System.currentTimeMillis(), userName);
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
