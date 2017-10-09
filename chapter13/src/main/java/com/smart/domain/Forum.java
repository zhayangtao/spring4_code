package com.smart.domain;

import java.io.Serializable;

/**
 * Created by shliangyan on 2017/9/30.
 */
public class Forum implements Serializable{
    private int forumId;
    private String forumName;
    private String forumDesc;

    public int getForumId() {
        return forumId;
    }

    public void setForumId(int forumId) {
        this.forumId = forumId;
    }

    public String getForumName() {
        return forumName;
    }

    public void setForumName(String forumName) {
        this.forumName = forumName;
    }

    public String getForumDesc() {
        return forumDesc;
    }

    public void setForumDesc(String forumDesc) {
        this.forumDesc = forumDesc;
    }
}
