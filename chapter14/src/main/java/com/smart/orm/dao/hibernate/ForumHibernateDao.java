package com.smart.orm.dao.hibernate;

import com.smart.domain.Forum;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by shliangyan on 2017/10/13.
 */
@Repository
public class ForumHibernateDao extends BaseDao {

    public void addForum(Forum forum) {
        hibernateTemplate.save(forum);
    }

    public void updateForum(Forum forum) {
        hibernateTemplate.update(forum);
    }

    public Forum getForum(int forumId) {
        return hibernateTemplate.get(Forum.class, forumId);
    }

//    public List<Forum> findForumByName(String forumName) {
//        return (List<Forum>) hibernateTemplate.find("from Forum f where f.forumName like ?", forumName + "%");
//    }

//    public long getForumNum() {
//        Object obj = hibernateTemplate.iterate("select count(f.forumId) from Forum f").next();
//        return (long) obj;
//    }

    /*public void getForumNum2() {
        Long forumNum = hibernateTemplate.execute(new HibernateCallback<Long>() {
            @Override
            public Long doInHibernate(Session session) throws HibernateException {
                Object object = session.createQuery("select count(f.forumId) from Forum f").list()
                        .iterator().next();
                return (Long) object;
            }
        });
    }*/
}
