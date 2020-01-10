package com.demo.forest.config.mybatis.util;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import java.lang.reflect.Field;

public class MybatisUtil {

    /**
     * 反射获取字段值
     *
     * @param field
     * @param object
     * @return
     * @throws IllegalAccessException
     */
    public static Object getFieldValue(Field field, Object object) throws IllegalAccessException {
        field.setAccessible(true);
        Object value = field.get(object);
        return value;
    }

    /**
     * 反射获取字段名称
     *
     * @param field
     * @return
     */
    public static String getFieldName(Field field) {
        if (field.isAnnotationPresent(TableField.class)) {
            TableField annotation = field.getAnnotation(TableField.class);
            return annotation.value();
        } else {
            return field.getName();
        }
    }

    /**
     * 获取TableName注解值
     *
     * @param object
     * @return
     * @throws Exception
     */
    public static String getTableNameValue(Object object) throws NullPointerException {
        Class<?> aClass = object.getClass();
        if (aClass.isAnnotationPresent(TableName.class)) {
            return aClass.getAnnotation(TableName.class).value();
        } else {
            throw new NullPointerException("TableName Nonentity!");
        }
    }

//    public static Object classClone(Object copyObject, Object copyToObject) throws IllegalAccessException {
//        Field[] fields1 = copyObject.getClass().getDeclaredFields();
//        Field[] fields2 = copyToObject.getClass().getDeclaredFields();
//        for (int i = 0; i < fields2.length; i++) {
//            Field field1 = fields1[i];
//            Field field2 = fields2[i];
//            field1.setAccessible(true);
//            field2.setAccessible(true);
//            if (field2.get(copyToObject) != null) {
//                if (field2.getName().equals(field1.getName())) {
//
//                }
//            }
//        }
//        return null;
//    }
}
