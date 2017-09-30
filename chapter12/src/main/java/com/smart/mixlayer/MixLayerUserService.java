package com.smart.mixlayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by shliangyan on 2017/9/30.
 */
@Controller
public class MixLayerUserService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping("/logon.do")
    @Transactional
    public String logon(String userName, String password) {
        String sql = "UPDATE t_user u SET u.score=u.score + ? WHERE user_name=?";
        jdbcTemplate.update(sql, 20, userName);
        return "success";
    }

}
