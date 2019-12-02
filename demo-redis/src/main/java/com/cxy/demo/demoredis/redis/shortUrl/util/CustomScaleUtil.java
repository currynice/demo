package com.cxy.demo.demoredis.redis.shortUrl.util;


import java.util.Stack;

/**
 * 62进制（一般用数字0到9和小写字母a-z,大写字母A-Z 到表示（其中:a~z即10~35,A~Z即36~61）。<br>
 * 例如10进制数181338494，62进制写作cgSqq。<br>
 *
 * @author cxy
 *
 */
public class CustomScaleUtil {


    private CustomScaleUtil() {
    }

    /**
     * 62进制字符的输出的素材数组
     */
    private static final char[] LIBRARY = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' ,'g','h','i','j',
                                            'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' ,'A','B','C','D',
                                            'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T' ,'U','V','W','X',
                                            'Y','Z'};


    public static String _10_to_62(long number){
        return _10_to_62(number,-1);
    }

    /**
     * 将10进制转化为62进制
     * @param number
     * @param length 转化成的62进制长度，不足length长度的话高位补0,足的话就忽略
     * @return
     */
    public static String _10_to_62(long number, int length){
        Long rest=number;
        Stack<Character> stack=new Stack<>();
        StringBuilder result=new StringBuilder(0);
        while(rest!=0){
            stack.add(LIBRARY[(int)(rest-(rest/62)*62)]);
            rest=rest/62;
        }
        for(;!stack.isEmpty();){
            result.append(stack.pop());
        }

        int result_length = result.length();
        if(result_length>=length){
            return result.toString();
        }
        StringBuilder temp0 = new StringBuilder();
        for(int i = 0; i < length - result_length; i++){
            temp0.append('0');
        }

        return temp0.toString() + result.toString();

    }


    /**
     * 将62进制转换成10进制数
     *
     * @param ident62
     * @return
     */
    private static String convertBase62ToDecimal( String ident62 ) {
        int decimal = 0;
        int base = 62;
        int keisu = 0;
        int cnt = 0;

        byte ident[] = ident62.getBytes();
        for ( int i = ident.length - 1; i >= 0; i-- ) {
            int num = 0;
            if ( ident[i] > 48 && ident[i] <= 57 ) {
                num = ident[i] - 48;
            }
            else if ( ident[i] >= 65 && ident[i] <= 90 ) {
                num = ident[i] - 65 + 10;
            }
            else if ( ident[i] >= 97 && ident[i] <= 122 ) {
                num = ident[i] - 97 + 10 + 26;
            }
            keisu = (int) Math.pow( (double) base, (double) cnt );
            decimal += num * keisu;
            cnt++;
        }
        return String.format( "%08d", decimal );
    }

    /**
     * @param args
     */
    public static void main(String[] args) {

        System.out.println("62:" +_10_to_62(Integer.parseInt("35174605"), 1));
        System.out.println("10:" +convertBase62ToDecimal("2NaWL"));
    }



    }