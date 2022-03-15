package com.mengby.sqlSession;

import java.util.List;

/**
 * @author bingye
 * @Date 2022/3/15
 */
public class DefaultSqlSession implements SqlSession {

    @Override
    public <E> List<E> selectList(String statementId, Object... params) {
        return null;
    }

    @Override
    public <T> T selectOne(String statementId, Object... params) {
        return null;
    }
}
