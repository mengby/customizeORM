package com.mengby.sqlSession;

import com.mengby.pojo.Configuration;
import com.mengby.pojo.MappedStatement;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

/**
 * @author bingye
 * @Date 2022/3/15
 */
public class DefaultSqlSession implements SqlSession {

    private Configuration configuration;

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public <E> List<E> selectList(String statementId, Object... params) throws SQLException, ClassNotFoundException, NoSuchFieldException, IntrospectionException, InvocationTargetException, IllegalAccessException, InstantiationException {
        Executor executor = new SimpleExecutor();
        final MappedStatement mappedStatement = configuration.getMappedStatementMap().get(statementId);
        return executor.query(configuration, mappedStatement, params);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T selectOne(String statementId, Object... params) throws SQLException, ClassNotFoundException, NoSuchFieldException, IntrospectionException, InvocationTargetException, IllegalAccessException, InstantiationException {
        List<Object> objects = this.selectList(statementId, params);
        if (objects.size() == 1) {
            return (T) objects.get(0);
        } else {
            throw new RuntimeException("查询结果为NUll,或查询结果为多条");
        }
    }
}
