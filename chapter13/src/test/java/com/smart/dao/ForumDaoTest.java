package com.smart.dao;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by shliangyan on 2017/10/9.
 */
public class ForumDaoTest {
    @Test
    public void testTime() {
        String dateStr = "1970-1-1";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = simpleDateFormat.parse(dateStr);
            System.out.println(date.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(new Date(0));
    }

}