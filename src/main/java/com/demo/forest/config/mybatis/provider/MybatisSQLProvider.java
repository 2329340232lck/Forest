package com.demo.forest.config.mybatis.provider;

import com.demo.forest.config.mybatis.util.MybatisUtil;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

public class MybatisSQLProvider extends BaseMybatisProvider {

    public String selectList(Map<String, ?> paramMap) throws Exception {
        Object paramObject = paramMap.get(PARAM_OBJECT);
        return generateSelectSQL(paramObject);
    }

    public String insert(Map<String, ?> paramMap) throws Exception {
        Object paramObject = paramMap.get(PARAM_OBJECT);
        return generateInsertSQL(paramObject);
    }

    public String update(Map<String, ?> paramMap) throws Exception {
        Object updateParam = paramMap.get(UPDATE_PARAM);
        Object conditionObject = paramMap.get(CONDITION_OBJECT);
        return generateUpdateSQL(updateParam, conditionObject);
    }

    public String delete(Map<String, ?> paramMap) throws Exception {
        Object conditionObject = paramMap.get(CONDITION_OBJECT);
        return generateDeleteSQL(conditionObject);
    }

    public String selectPage(Map<String, ?> paramMap) throws Exception {
        Object paramObject = paramMap.get(PARAM_OBJECT);
        return generateSelectSQL(paramObject);
    }

    public String selectCount(Map<String, ?> paramMap) throws Exception {
        SQL sql = new SQL();
        Object param = paramMap.get(CONDITION_OBJECT);
        sql.SELECT("COUNT(*)");
        sql.FROM(MybatisUtil.getTableNameValue(param));
        sql.WHERE(generateConditionSQL(param));
        return sql.toString();
    }
}
