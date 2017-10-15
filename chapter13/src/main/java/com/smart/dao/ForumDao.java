package com.smart.dao;

import com.smart.domain.Forum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhayangtao
 */
@Repository
public class ForumDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void addForum(Forum forum) {
        String sql = "INSERT INTO t_forum(forum_name, forum_desc) VALUES(?,?)";
        Object[] params = new Object[]{forum.getForumName(), forum.getForumDesc()};
        jdbcTemplate.update(sql, params);
    }

    public void addForum1(Forum forum) {
        String sql = "INSERT INTO t_forum(forum_name, forum_desc) VALUES(?,?)";
        Object[] params = new Object[]{forum.getForumName(), forum.getForumDesc()};
        jdbcTemplate.update(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(1, forum.getForumName());
                preparedStatement.setString(2, forum.getForumDesc());
            }
        });
    }

    public void testGenerateKeys(final Forum forum) {
        final String sql = "INSERT INTO t_form(forum_name,forum_desc) VALUES (?,?)";
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
        final String sql = "INSERT INTO t_forum(forum_name, forum_desc) VALUES(?,?)";
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

    public Forum getForum(int forumId) {
        String sql = "SELECT forum_name, forum_desc FROM t_forum WHERE forum_id=?";
        Forum forum = new Forum();
        jdbcTemplate.query(sql, new Object[]{forumId}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                forum.setForumId(forumId);
                forum.setForumName(resultSet.getString("forum_name"));
                forum.setForumDesc(resultSet.getString("forum_desc"));
            }
        });
        return forum;
    }

    public List<Forum> getForums(int forumId, int toId) {
        String sql = "SELECT forum_id, forum_name, forum_desc FROM t_forum WHERE forum_id BETWEEN ? AND ?";
        List<Forum> forums = new ArrayList();
        jdbcTemplate.query(sql, new Object[]{forumId, toId}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                Forum forum = new Forum();
                forum.setForumId(resultSet.getInt("forum_id"));
                forum.setForumName(resultSet.getString("forum_name"));
                forum.setForumDesc(resultSet.getString("forum_desc"));
                forums.add(forum);
            }
        });
        return forums;
    }

    public List<Forum> getForums1(int fromId, int toId) {
        String sql = "SELECT forum_id, forum_name, forum_desc FROM t_forum WHERE forum_id BETWEEN ? AND ?";
        return jdbcTemplate.query(sql, new Object[]{fromId, toId}, new RowMapper<Forum>() {
            @Override
            public Forum mapRow(ResultSet resultSet, int i) throws SQLException {
                Forum forum = new Forum();
                forum.setForumId(resultSet.getInt("forum_id"));
                forum.setForumName(resultSet.getString("forum_name"));
                forum.setForumDesc(resultSet.getString("forum_desc"));
                return forum;
            }
        });
    }


}
