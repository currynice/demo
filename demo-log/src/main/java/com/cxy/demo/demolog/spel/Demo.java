package com.cxy.demo.demolog.spel;

import com.cxy.demo.demolog.entity.UpdateDelivery;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;

/**
 * Description:   </br>
 * Date: 2021/9/17 17:08
 *
 * @author :cxy </br>
 * @version : 1.0 </br>
 */
public class Demo {


    public static void main(String[] args) {
        SpelExpressionParser parser = new SpelExpressionParser();
        Expression expression = parser.parseExpression("#root.address");
        UpdateDelivery delivery = new UpdateDelivery();
        delivery.setAddress("南京");
        System.out.println(expression.getValue(delivery));
    }


}
