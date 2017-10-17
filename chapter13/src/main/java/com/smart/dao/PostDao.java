package com.smart.dao;

import com.mysql.jdbc.MySQLConnection;
import com.smart.domain.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.AbstractLobCreatingPreparedStatementCallback;
import org.springframework.jdbc.core.support.AbstractLobStreamingResultSetExtractor;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.support.incrementer.DataFieldMaxValueIncrementer;
import org.springframework.jdbc.support.incrementer.MySQLMaxValueIncrementer;
import org.springframework.jdbc.support.lob.LobCreator;
import org.springframework.jdbc.support.lob.LobHandler;
import org.springframework.stereotype.Repository;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by shliangyan on 2017/10/10.
 */
@Repository
public class PostDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private MySQLMaxValueIncrementer incrementer;

    /**
     * 获取本地连接
     */
    public void getNativeConn() {
        try {
            Connection connection = DataSourceUtils.getConnection(jdbcTemplate.getDataSource());
            connection = jdbcTemplate.getNativeJdbcExtractor().getNativeConnection(connection);
            MySQLConnection mySQLConnection = (MySQLConnection) connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Autowired
    private LobHandler lobHandler;

    public void addPost(final Post post) {
        String sql = "INSERT INTO t_post(post_id,user_id,post_text,post_attach) VALUES(?,?,?,?)";
        jdbcTemplate.execute(sql, new AbstractLobCreatingPreparedStatementCallback(this.lobHandler) {
            @Override
            protected void setValues(PreparedStatement preparedStatement, LobCreator lobCreator) throws SQLException, DataAccessException {
                preparedStatement.setInt(1, 1);
                preparedStatement.setInt(2, post.getUserId());
                lobCreator.setClobAsString(preparedStatement, 3, post.getPostText());
                lobCreator.setBlobAsBytes(preparedStatement, 4, post.getPostAttach());
            }
        });
    }

    /**
     * 以块方式读取LOB数据
     * @param userId
     * @return
     */
    public List<Post> getAttachs(final int userId) {
        String sql = "SELECT post_i, post_attach FROM t_post WHERE user_id = ? AND post_attach IS NOT NULL";
        List<Post> posts = jdbcTemplate.query(sql, new Object[]{userId}, new RowMapper<Post>() {

            @Override
            public Post mapRow(ResultSet resultSet, int i) throws SQLException {
                int postId = resultSet.getInt(1);
                byte[] attach = lobHandler.getBlobAsBytes(resultSet, 2);
                return null;
            }
        });
        return posts;
    }

    /**
     * 流方式处理LOB数据
     * @param postId
     * @param outputStream
     */
    public void getAttach(final int postId, final OutputStream outputStream) {
        String sql = "SELECT post_attach FROM t_post WHERE post_id=?";
        jdbcTemplate.query(sql, new Object[]{postId}, new AbstractLobStreamingResultSetExtractor<Object>() {

            @Override
            protected void streamData(ResultSet resultSet) throws SQLException, IOException, DataAccessException {
                InputStream inputStream = lobHandler.getBlobAsBinaryStream(resultSet, 1);
                if (inputStream != null) {
                    FileCopyUtils.copy(inputStream, outputStream);
                }
            }

            @Override
            protected void handleNoRowFound() throws DataAccessException {
                System.out.println("Not Found result!");
            }
        });
    }
}
