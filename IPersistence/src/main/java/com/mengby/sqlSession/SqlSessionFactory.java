package com.mengby.sqlSession;

/**
 * @author bingye
 * @Date 2022/3/15
 */
public interface SqlSessionFactory {

    public SqlSession openSession();
}
