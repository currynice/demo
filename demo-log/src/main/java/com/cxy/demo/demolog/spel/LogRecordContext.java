package com.cxy.demo.demolog.spel;

import java.util.Map;
import java.util.Stack;

/**
 * Description:   </br>
 * Date: 2021/9/18 9:15
 *
 * @author :cxy </br>
 * @version : 1.0 </br>
 */
public class LogRecordContext {

    private static final InheritableThreadLocal<Stack<Map<String, Object>>> variableMapStack = new InheritableThreadLocal<>();

    public static Map<String, Object> getVariables() {
        return null;
    }

    public static void putEmptySpan() {
    }

    public static void clear() {

        variableMapStack.get().clear();

    }
}
