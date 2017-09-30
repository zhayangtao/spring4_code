package com.smart.nestcall;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by shliangyan on 2017/9/30.
 */
@Service(value = "scoreUserService")
public class ScoreService extends BaseService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void addScore(String userName, int toAdd) {
        String sql = "UPDATE t_user u SET u.score = u.score + ? WHERE user_name =?";
        jdbcTemplate.update(sql, toAdd, userName);
        BasicDataSource basicDataSource = (BasicDataSource) jdbcTemplate.getDataSource();
        System.out.println("激活连接数量:" + basicDataSource.getNumActive());
    }
}
