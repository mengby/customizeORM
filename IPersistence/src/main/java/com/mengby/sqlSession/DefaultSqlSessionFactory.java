package com.mengby.sqlSession;

import com.mengby.pojo.Configuration;

/**
 * @author bingye
 * @Date 2022/3/15
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory{

    private Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(configuration);
    }
}
