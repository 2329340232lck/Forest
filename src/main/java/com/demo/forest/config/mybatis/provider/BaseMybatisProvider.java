package com.demo.forest.config.mybatis.provider;

import com.demo.forest.config.mybatis.util.MybatisUtil;
import org.apache.ibatis.jdbc.SQL;

import java.lang.reflect.Field;

public abstract class BaseMybatisProvider {

    //普通参数对象
    protected final String PARAM_OBJECT = "paramObject";

    //条件对象
    protected final String CONDITION_OBJECT = "conditionObject";

    //更新参数对象
    protected final String UPDATE_PARAM = "updateParam";

    //等于运算符
    protected final String EQUAL = " = ";

    //与运算符
    protected final String AND = " AND ";

    //生成查询SQL
    String generateSelectSQL(Object paramObject) throws Exception {
        int i = 0;
        Field[] declaredFields = paramObject.getClass().getDeclaredFields();
        SQL sql = new SQL();
        sql.SELECT("*");
        sql.FROM(MybatisUtil.getTableNameValue(paramObject));
        StringBuilder where = new StringBuilder();
        for (Field declaredField : declaredFields) {
            String fieldName = MybatisUtil.getFieldName(declaredField);
            Object fieldValue = MybatisUtil.getFieldValue(declaredField, paramObject);
            if (fieldValue != null) {
                if (i == 0) {
                    where.append(fieldName + EQUAL + "#{paramObject." + fieldName + "}");
                } else {
                    where.append(AND + fieldName + EQUAL + "#{paramObject." + fieldName + "}");
                }
                i++;
            }
        }
        if (!"".equals(where.toString())) {
            sql.WHERE(where.toString());
        }
        return sql.toString();
    }

    //生成插入SQL
    String generateInsertSQL(Object paramObject) throws Exception {
        SQL sql = new SQL();
        sql.INSERT_INTO(MybatisUtil.getTableNameValue(paramObject));
        Field[] declaredFields = paramObject.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            Object fieldValue = MybatisUtil.getFieldValue(field, paramObject);
            if (fieldValue != null) {
                String fieldName = MybatisUtil.getFieldName(field);
                sql.VALUES(fieldName, "#{paramObject." + field.getName() + "}");
            }
        }
        return sql.toString();
    }

    //生成更新SQL
    String generateUpdateSQL(Object updateParam, Object conditionParam) throws Exception {
        Field[] updateFields = updateParam.getClass().getDeclaredFields();
        Field[] conditionFields = conditionParam.getClass().getDeclaredFields();
        int i = 0;
        SQL sql = new SQL();
        sql.UPDATE(MybatisUtil.getTableNameValue(updateParam));
        //拼接更新字段
        for (Field field : updateFields) {
            String fieldName = MybatisUtil.getFieldName(field);
            Object fieldValue = MybatisUtil.getFieldValue(field, updateParam);
            if (fieldValue != null) {
                sql.SET(fieldName + EQUAL + "#{updateParam." + field.getName() + "}");
            }
        }
        //拼接更新条件
        StringBuilder where = new StringBuilder();
        for (Field field : conditionFields) {
            Object fieldValue = MybatisUtil.getFieldValue(field, conditionParam);
            if (fieldValue != null) {
                String fieldName = MybatisUtil.getFieldName(field);
                if (i == 0) {
                    where.append(fieldName).append(EQUAL).append("#{conditionObject.").append(field.getName()).append("}");
                } else {
                    where.append(AND).append(fieldName).append(EQUAL).append("#{conditionObject.").append(field.getName()).append("}");
                }
                i++;
            }
        }
        sql.WHERE(where.toString());
        return sql.toString();
    }

    //生成删除SQL
    String generateDeleteSQL(Object conditionObject) throws Exception {
        Field[] declaredFields = conditionObject.getClass().getDeclaredFields();
        SQL sql = new SQL();
        sql.DELETE_FROM(MybatisUtil.getTableNameValue(conditionObject));
        StringBuilder where = new StringBuilder();
        int i = 0;
        //拼接条件语句
        for (Field declaredField : declaredFields) {
            Object fieldValue = MybatisUtil.getFieldValue(declaredField, conditionObject);
            String fieldName = MybatisUtil.getFieldName(declaredField);
            if (fieldValue != null) {
                if (i == 0) {
                    where.append(fieldName + EQUAL + "#{conditionObject." + fieldName + "}");
                } else {
                    where.append(AND + fieldName + EQUAL + "#{conditionObject." + fieldName + "}");
                }
                i++;
            }
        }
        sql.WHERE(where.toString());
        return sql.toString();
    }

    //生成条件SQL
    String generateConditionSQL(Object conditionObject) throws Exception {
        boolean isFirst = true;
        Field[] declaredFields = conditionObject.getClass().getDeclaredFields();
        StringBuilder sb = new StringBuilder();
        for (Field declaredField : declaredFields) {
            String fieldName = MybatisUtil.getFieldName(declaredField);
            Object fieldValue = MybatisUtil.getFieldValue(declaredField, conditionObject);
            if (fieldValue != null) {
                if (isFirst) {
                    sb.append(fieldName + EQUAL + "#{conditionObject." + fieldName + "}");
                    isFirst = false;
                } else {
                    sb.append(AND + fieldName + EQUAL + "#{conditionObject." + fieldName + "}");
                }
            }
        }
        return sb.toString();
    }

    //生成参数SQL
//    protected String generateParameterSQL(Object paramObject) throws Exception {
//        Field[] declaredFields = paramObject.getClass().getDeclaredFields();
//        StringBuilder sb = new StringBuilder();
//        boolean isFirst = true;
//        for (Field declaredField : declaredFields) {
//            String fieldName = MybatisUtil.getFieldName(declaredField);
//            Object fieldValue = MybatisUtil.getFieldValue(declaredField, paramObject);
//            if (fieldValue != null) {
//                if (isFirst) {
//                    sb.append("#{paramObject." + fieldName + "}");
//                    isFirst = false;
//                } else {
//                    sb.append(",#{paramObject." + fieldName + "}");
//                }
//            }
//        }
//        return sb.toString();
//    }
}
