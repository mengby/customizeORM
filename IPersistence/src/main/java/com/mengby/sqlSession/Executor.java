package com.mengby.sqlSession;

import com.mengby.pojo.Configuration;
import com.mengby.pojo.MappedStatement;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

/**
 * @author bingye
 * @Date 2022/3/16
 */
public interface Executor {

    <E> List<E> query(Configuration configuration, MappedStatement mappedStatement,Object... params) throws SQLException, ClassNotFoundException, NoSuchFieldException, IllegalAccessException, IntrospectionException, InstantiationException, InvocationTargetException;
}
