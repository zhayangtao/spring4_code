package com.smart.dao;

import com.smart.domain.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.Iterator;

/**
 * Created by shliangyan on 2017/11/7.
 */
@Repository
public class BoardDao extends BaseDao<Board> {

    private static final String GET_BOARD_NUM = "select count(f.boardId) from Board f";

    public long getBoardNum() {
        Iterator iterator = getHibernateTemplate().iterate(GET_BOARD_NUM);
        return (long) iterator.next();
    }
}
