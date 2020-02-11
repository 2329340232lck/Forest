package com.demo.forest.config.mybatis.util;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import java.lang.reflect.Field;

public class MybatisUtil {

    public static Object getFieldValue(Field field, Object object) throws IllegalAccessException {
        field.setAccessible(true);
        Object value = field.get(object);
        return value;
    }

    public static String getFieldName(Field field) {
        if (field.isAnnotationPresent(TableField.class)) {
            TableField annotation = field.getAnnotation(TableField.class);
            return annotation.value();
        } else {
            return field.getName();
        }
    }

    public static String getTableNameValue(Object object) throws NullPointerException {
        Class<?> aClass = object.getClass();
        if (aClass.isAnnotationPresent(TableName.class)) {
            return aClass.getAnnotation(TableName.class).value();
        } else {
            throw new NullPointerException("TableName Nonentity!");
        }
    }
}
