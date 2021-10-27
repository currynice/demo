package com.cxy.demo.demolog.spel;

import org.springframework.expression.EvaluationContext;

/**
 * Description:   </br>
 * Date: 2021/9/18 13:10
 *
 * @author :cxy </br>
 * @version : 1.0 </br>
 */
public class LogRecordValueParser {
      private  EvaluationContext evaluationContext;

      private LogRecordExpressionEvaluator expressionEvaluator = new LogRecordExpressionEvaluator();

    public LogRecordValueParser() {

        this.expressionEvaluator = expressionEvaluator;
        this.evaluationContext =  expressionEvaluator.createEvaluationContext(method, args, targetClass, ret, errorMsg, beanFactory);

    }
}
