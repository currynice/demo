package base;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;

import java.util.List;


/**
 * Description:   </br>
 * Date: 2021/4/15 17:43
 *
 * @author :cxy </br>
 * @version : 1.0 </br>
 */
public class StringTest {

    public static void main(String[] args) {
        String a =",a, , b c ,";
        List<String> list = Splitter.on(',') .trimResults()// 去掉空格
        .omitEmptyStrings()// 去掉空值
        .splitToList(a);


        list.forEach(System.out::println);


        /**
         * String#join 的参数一是合并过程用到的分隔符，参数二是待合并数据源（支持数组和 List）
         *
         * 但是:
         * 1. 不支持依次链式 join 多个字符串 s 和 s1，
         *      String.join(",",s).join(",",s1)  第一次 join 的值会第二次 join 覆盖；
         *
         * 2.  数据源是 List，无法自动过滤掉 null 值
         *
         */
        Joiner joiner = Joiner.on(",").skipNulls();

        String result = joiner.join("hello",null,"china");

        System.out.println("链式 join 多个字符串:"+result);

        List<String> list2 = Lists.newArrayList("hello","china",null);

        System.out.println("join 时自动删除 list 中空值" + joiner.join(list2));


    }


}
