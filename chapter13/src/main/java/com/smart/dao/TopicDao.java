package com.smart.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Administrator on 2017/10/3.
 */
public class TopicDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public double getReplyRate(int userId) {
        String sql = "SELECT topic_replies, topic_views FROM t_topic WHERE user)id=?";
        double rate = jdbcTemplate.queryForObject(sql, new Object[]{userId}, new RowMapper<Double>() {
            @Override
            public Double mapRow(ResultSet resultSet, int i) throws SQLException {
                int replies = resultSet.getInt("topic_replies");
                int views = resultSet.getInt("topic_views");
                if (views > 0) {
                    return new Double(replies / views);
                } else {
                    return new Double(0.0);
                }
            }
        });
        return rate;
    }
}
