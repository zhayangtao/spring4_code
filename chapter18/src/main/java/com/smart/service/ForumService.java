package com.smart.service;

import com.smart.dao.BoardDao;
import com.smart.dao.TopicDao;
import com.smart.domain.Board;
import com.smart.domain.MainPost;
import com.smart.domain.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by shliangyan on 2017/11/8.
 */
@Service
public class ForumService {
    @Autowired
    private TopicDao topicDao;

    @Autowired
    private BoardDao boardDao;

    public void addTopic(Topic topic) {
        Board board = boardDao.get(topic.getBoardId());
        board.setTopicNum(board.getTopicNum() + 1);
        topicDao.save(topic);

        topic.getMainPost().setTopic(topic);
        MainPost post = topic.getMainPost();
        post.setCreateTime(new Date());
        post.setUser(topic.getUser());

    }
}
