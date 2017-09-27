package com.smart.advice;

import java.sql.SQLException;

/**
 * Created by shliangyan on 2017/9/27.
 */
public class ForumService {
    public void removeForum(int forumId) {
        throw new RuntimeException("运行异常");
    }

    public void updateForum() throws Exception {
        throw new SQLException("数据库异常");
    }
}
