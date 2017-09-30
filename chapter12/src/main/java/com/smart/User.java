package com.smart;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by shliangyan on 2017/9/30.
 * @version 1.0
 */
@Entity
@Table(name = "T_USER")
public class User {
    @Id
    @Column(name = "USER_NAME")
    private String userName;

    private String password;

    private int score;

    @Column(name = "LAST_LOGON_TIME")
    private long lastLogonTime = 0;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public long getLastLogonTime() {
        return lastLogonTime;
    }

    public void setLastLogonTime(long lastLogonTime) {
        this.lastLogonTime = lastLogonTime;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", score=" + score +
                ", lastLogonTime=" + lastLogonTime +
                '}';
    }
}
