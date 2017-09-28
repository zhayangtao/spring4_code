package com.smart.spel;

import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

/**
 * Created by shliangyan on 2017/9/28.
 */
public class SayHello {
    public static void main(String[] args) {
        ExpressionParser parser = new SpelExpressionParser();
        Expression expression = parser.parseExpression("'Hello' + ' World!'");
        String message = (String) expression.getValue();
        System.out.println(message);
    }
}
