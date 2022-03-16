package com.mengby.sqlSession;

import com.mengby.config.BoundSql;
import com.mengby.pojo.Configuration;
import com.mengby.pojo.MappedStatement;
import com.mengby.utils.GenericTokenParser;
import com.mengby.utils.ParameterMapping;
import com.mengby.utils.ParameterMappingTokenHandler;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author bingye
 * @Date 2022/3/16
 */
public class SimpleExecutor implements Executor {
    @Override
    public <E> List<E> query(Configuration configuration, MappedStatement mappedStatement, Object... params) throws SQLException, ClassNotFoundException, NoSuchFieldException, IllegalAccessException, IntrospectionException, InstantiationException, InvocationTargetException {
        // 注册驱动、获取连接
        final Connection connection = configuration.getDataSource().getConnection();
        String sql = mappedStatement.getSql();
        // 解析sql
        BoundSql boundSql = getBoundSql(sql);
        // 获取preparedStatement
        final PreparedStatement preparedStatement = connection.prepareStatement(boundSql.getSqlText());
        // 设置参数
        final String parameterType = mappedStatement.getParameterType();
        Class<?> parameterTypeClass = getClassType(parameterType);
        final List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        for (int i = 0; i < parameterMappings.size(); i++) {
            ParameterMapping parameterMapping = parameterMappings.get(i);
            String content = parameterMapping.getContent();
            final Field declaredField = parameterTypeClass.getDeclaredField(content);
            declaredField.setAccessible(true);
            final Object o = declaredField.get(params[0]);
            preparedStatement.setObject(i + 1, o);
        }
        // 执行sql

        ResultSet resultSet = preparedStatement.executeQuery();
        // 封装返回结果
        final String resultType = mappedStatement.getResultType();

        Class<?> resultTypeClas = getClassType(resultType);
        Object o = resultTypeClas.newInstance();

        ArrayList<Object> objects = new ArrayList<>();
        while (resultSet.next()) {
            ResultSetMetaData metaData = resultSet.getMetaData();
            for (int i = 0; i < metaData.getColumnCount(); i++) {

                String columnName = metaData.getColumnName(i);
                Object value = resultSet.getObject(columnName);
                // 内省
                PropertyDescriptor propertyDescriptor = new PropertyDescriptor(columnName, resultTypeClas);
                propertyDescriptor.getWriteMethod().invoke(o, value);
            }
            objects.add(o);
        }
        return (List<E>) objects;
    }

    private Class<?> getClassType(String parameterType) throws ClassNotFoundException {
        if (parameterType != null) {
            Class<?> aClass = Class.forName(parameterType);
            return aClass;
        }
        return null;
    }

    /**
     * 解析#{xxx}的内容 解析工厂
     *
     * @param sql select * from users where id = #{id} and username = #{username}
     * @return BoundSql
     */
    private BoundSql getBoundSql(String sql) {
        ParameterMappingTokenHandler parameterMappingTokenHandler = new ParameterMappingTokenHandler();
        final GenericTokenParser genericTokenParser = new GenericTokenParser("#{", "}", parameterMappingTokenHandler);
        final String parse = genericTokenParser.parse(sql);
        // 解析出来的param的名称
        List<ParameterMapping> parameterMappings = parameterMappingTokenHandler.getParameterMappings();
        return new BoundSql(sql, parameterMappings);
    }
}
