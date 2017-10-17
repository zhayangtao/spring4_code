package com.smart.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by shliangyan on 2017/10/17.
 */
@Entity
//@Table(name = "T_POST")
public class Post {
    @Id

    private int postId;
}
