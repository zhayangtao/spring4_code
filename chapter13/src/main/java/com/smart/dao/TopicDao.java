package com.smart.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

/**
 * Created by shliangyan on 2017/10/11.
 */
public class TopicDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public SqlRowSet getTopicRowSet(int userId) {
        String sql = "SELECT topic_id,topic_title FROM t_topic WHERE user_id=?";
        return jdbcTemplate.queryForRowSet(sql, userId);
    }
}
