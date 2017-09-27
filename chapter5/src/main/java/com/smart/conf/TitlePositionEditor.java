package com.smart.conf;

import java.beans.PropertyEditorSupport;

/**
 * Created by shliangyan on 2017/9/26.
 */
public class TitlePositionEditor extends PropertyEditorSupport {
    private String [] options = {"Left", "Center", "Right"};

    @Override
    public String [] getTags() {
        return options;
    }

    @Override
    public String getJavaInitializationString() {
        return "" + getValue();
    }

    @Override
    public String getAsText() {
        int value = (int) getValue();
        return options[value];
    }

    //将外部设置的字符串转换为内部属性的值
    @Override
    public void setAsText(String s) {
        for (int i = 0; i < options.length; i++) {
            if (options[i].equals(s)) {
                setValue(i);
                return;
            }
        }
    }
}
