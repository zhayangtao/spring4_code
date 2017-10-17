package com.smart.dao;

import com.smart.domain.Forum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by shliangyan on 2017/9/30.
 */
@Repository
public class ForumDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

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

    public Forum getForum(final int forumId) {
        String sql = "SELECT forum_name, forum_desc FROM t_forum WHERE forum_id=?";
        final Forum forum = new Forum();
        jdbcTemplate.query(sql, new Object[]{forumId}, resultSet -> {
            forum.setForumId(forumId);
            forum.setForumName(resultSet.getString("forum_name"));
            forum.setForumDesc(resultSet.getString("forum_desc"));
        });
        return forum;
    }

    public List<Forum> getForums(final int fromId, final int toId) {
        String sql = "SELECT forum_id, forum_name, forum_desc FROM t_forum WHERE forum_id BETWEEN ? AND ?";
        final List<Forum> forums = new ArrayList<>();
        jdbcTemplate.query(sql, new Object[]{fromId, toId}, resultSet -> {
            Forum forum = new Forum();
            forum.setForumId(resultSet.getInt("forum_id"));
            forum.setForumName(resultSet.getString("forum_name"));
            forum.setForumDesc(resultSet.getString("forum_desc"));
            forums.add(forum);
        });
        return forums;
    }

    public List<Forum> getForum2(final int fromId, final int toId) {
        String sql = "SELECT forum_id, forum_name, forum_desc FROM t_forum WHERE forum_id BETWEEN ? AND ?";
        List<Forum> forums = jdbcTemplate.query(sql, new Object[]{fromId, toId}, (resultSet, i) -> {
            Forum forum = new Forum();
            forum.setForumId(resultSet.getInt("forum_id"));
            forum.setForumName(resultSet.getString("forum_name"));
            forum.setForumDesc(resultSet.getString("forum_desc"));
            return forum;
        });
        return forums;
    }

    public void addForumByNamedParams(final Forum forum) {
        final String sql = "INSERT INTO t_forum(forum_name,forum_desc) VALUES (:forumName,:forumDesc)";
        SqlParameterSource source = new BeanPropertySqlParameterSource(forum);
        namedParameterJdbcTemplate.update(sql, source);
    }

    public void addForum3(final Forum forum) {
        final String sql = "INSERT INTO t_forum(forum_name, forum_desc) VALUES (:forumName,:forumDesc)";
        SqlParameterSource source = new MapSqlParameterSource().addValue("forumName", forum.getForumName())
                .addValue("forumDesc", forum.getForumDesc());
        namedParameterJdbcTemplate.update(sql, source);
    }
}
