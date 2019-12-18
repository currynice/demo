package com.cxy.demo.democonverter.config.formatters;

import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;

/**
 * @Author: cxy
 * @Date: 2019/12/18 14:47
 * @Description:
 *
 *
public void addFormatters(FormatterRegistry registry) {
Iterator var2 = this.getBeansOfType(Converter.class).iterator();
 */
@Component
public class TestFormatter implements Formatter<String>  {
    @Override
    public String parse(String text, Locale locale) throws ParseException {

        if(text.endsWith("RMB")){
            String price =  text.substring(0,text.length()-3);
            return price+"人名币";
        }
        if(text.endsWith("YB")){
            String price =  text.substring(0,text.length()-2);
            return price+"英镑";
        }
        return null;
    }

    @Override
    public String print(String object, Locale locale) {
        return object.toString();
    }
}
