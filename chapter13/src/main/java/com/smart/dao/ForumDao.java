package com.smart.dao;

import com.smart.domain.Forum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

/**
 * Created by shliangyan on 2017/9/30.
 */
@Repository
public class ForumDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void initDb() {
        String sql = "create table t_user(user_id int primary key,user_name varchar(60))";
        jdbcTemplate.execute(sql);
    }

    public void addForum(Forum forum) {
        String sql = "INSERT INTO t_forum(forum_name, forum_desc) VALUES (?,?)";
        Object[] params = new Object[]{forum.getForumName(), forum.getForumDesc()};
        jdbcTemplate.update(sql, params, new int[]{Types.VARCHAR, Types.VARCHAR});
    }

    public void addForum2(final Forum forum) {
        final String sql = "INSERT INTO t_forum(forum_name,forum_desc) VALUES (?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, forum.getForumName());
                preparedStatement.setString(2, forum.getForumDesc());
                return preparedStatement;
            }
        }, keyHolder);
        forum.setForumId(keyHolder.getKey().intValue());
    }

    public void addForums(final List<Forum> forumList) {
        final String sql = "INSERT INTO t_forum(forum_name,forum_desc) VALUES (?,?)";
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                Forum forum = forumList.get(i);
                preparedStatement.setString(1, forum.getForumName());
                preparedStatement.setString(2, forum.getForumDesc());
            }

            @Override
            public int getBatchSize() {
                return forumList.size();
            }
        });
    }
}
