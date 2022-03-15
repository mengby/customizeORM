package com.mengby.io;

import com.mengby.pojo.User;
import com.mengby.sqlSession.SqlSession;
import com.mengby.sqlSession.SqlSessionFactory;
import com.mengby.sqlSession.SqlSessionFactoryBuilder;
import org.dom4j.DocumentException;
import org.junit.jupiter.api.Test;

import java.beans.PropertyVetoException;
import java.io.InputStream;

/**
 * @author bingye
 * @Date 2022/3/15
 */
public class ResourcesTest {

    @Test
    public void main() throws PropertyVetoException, DocumentException {
        final InputStream resourceAsSteam = Resources.getResourceAsSteam("sqlMapConfig.xml");
        final SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsSteam);
        final SqlSession sqlSession = sqlSessionFactory.openSession();
        final User user = new User();
        user.setId(1);
        user.setUsername("zhangsan");
        final User user1 = sqlSession.selectOne("user.selectOne", user);
    }
}
