package com.smart.dao;

import org.hibernate.Query;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTabJc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.util.Assert;

import javax.print.DocFlavor;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by shliangyan on 2017/11/3.
 */
public class BaseDao<T> {
    private Class<T> entityClass;

    private HibernateTemplate hibernateTemplate;

    /**
     * 通过反射获取子类确定的泛型类
     */
    public BaseDao() {
        Type genType = getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        entityClass = (Class<T>) params[0];
    }

    //根据id加载po实例
    public T load(Serializable id) {
        return getHibernateTemplate().load(entityClass, id);
    }

    //根据id获取po实例
    public T get(Serializable id) {
        return hibernateTemplate.get(entityClass, id);
    }

    //获取po的所有对象
    public List<T> loadAll() {
        return getHibernateTemplate().loadAll(entityClass);
    }

    //保存po
    public void save(T entity) {
        getHibernateTemplate().save(entity);
    }

    //删除po
    public void remove(T entity) {
        getHibernateTemplate().delete(entity);
    }

    //更改po
    public void update(T entity) {
        getHibernateTemplate().delete(entity);
    }

    //执行hql查询语句
    public List find(String hql) {
        return getHibernateTemplate().find(hql);
    }

    //执行带参的hql查询
    public List find(String hql, Object... params) {
        return getHibernateTemplate().find(hql, params);
    }

    //对延迟加载的实体po执行初始化
    public void initialize(Object entity) {
        getHibernateTemplate().initialize(entity);
    }

    public Page pagedQuery(String hql, int pageNo, int pageSize, Object... values) {
        Assert.hasText(hql);
        Assert.isTrue(pageNo >= 1, "pageNo show start from 1");
        //count 查询
        String countQueryString = "select count (*) " + removeSelect(removeOrders(hql));
        List countList = getHibernateTemplate().find(countQueryString, values);
        long totalCount = (long) countList.get(0);
        if (totalCount < 1) {
            return new Page();
        }

        int startIndex = Page.getStartOfPage(pageNo, pageSize);
        Query query = createQuery(hql, values);
        List list = query.setFirstResult(startIndex).setMaxResults(pageSize).list();
        return new Page(startIndex, totalCount, pageSize, list);
    }

    private Query createQuery(String hql, Object[] values) {
        Assert.hasText(hql);
        Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
        for (int i = 0; i < values.length; i++) {
            query.setParameter(i, values[i]);
        }
        return query;
    }

    private static String removeSelect(String hql) {
        Assert.hasText(hql);
        int beginPos = hql.toLowerCase().indexOf("from");
        Assert.isTrue(beginPos != -1," hql : " + hql + "must has a keyword 'from'");
        return hql.substring(beginPos);
    }

    private static String removeOrders(String hql) {
        Assert.hasText(hql);
        Pattern pattern = Pattern.compile("order\\s*by[\\w|\\W|\\s|\\S]*", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(hql);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "");
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    @Autowired
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }
}
