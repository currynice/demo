package com.cxy.demo.democonverter.config.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

import java.util.Arrays;

/**
 * Description: String 对象 -> Enum对象  <br>
 * Date: 2020/6/11 11:49  <br>
 *
 * @author :cxy <br>
 * @version : 1.0 <br>
 */
public class EnumIgnoreCaseConverterFactory implements ConverterFactory<String, Enum> {
    @Override
    public <T extends Enum> Converter<String, T> getConverter(Class<T> targetType) {
        return new String2EnumConverter(targetType);
    }
    class String2EnumConverter<T extends Enum<T>> implements Converter<String, T> {
        private Class<T> enumType;
        private String2EnumConverter(Class<T> enumType) {
            this.enumType = enumType;
        }
        @Override
        public T convert(String source) {
            return Arrays.stream(enumType.getEnumConstants())
                    .filter(e -> e.name().equalsIgnoreCase(source))
                    .findAny().orElse(null);
        }
    }
}
