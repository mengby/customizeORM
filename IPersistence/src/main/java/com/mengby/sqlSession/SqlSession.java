package com.mengby.sqlSession;

import java.sql.SQLException;
import java.util.List;

/**
 * @author bingye
 * @Date 2022/3/15
 */
public interface SqlSession {

    <E> List<E> selectList(String statementId,Object... params) throws SQLException, ClassNotFoundException, NoSuchFieldException;

    <T> T selectOne(String statementId,Object... params) throws SQLException, ClassNotFoundException, NoSuchFieldException;

}
