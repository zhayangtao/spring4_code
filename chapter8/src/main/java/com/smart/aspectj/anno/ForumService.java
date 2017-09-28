package com.smart.aspectj.anno;

/**
 * Created by shliangyan on 2017/9/28.
 * 使用注解
 */
public class ForumService {
    @NeedTest(value = true)
    public void deleteForum(int forumId) {
        System.out.println("删除模块:" + forumId);
    }

    @NeedTest(value = false)
    public void deleteTopic(int postId) {
        System.out.println("删除论坛主题:" + postId);
    }
}
