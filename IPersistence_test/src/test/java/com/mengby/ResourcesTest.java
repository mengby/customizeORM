package com.mengby;

import com.mengby.pojo.User;
import com.mengby.sqlSession.SqlSession;
import com.mengby.sqlSession.SqlSessionFactory;
import com.mengby.sqlSession.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.InputStream;

/**
 * @author bingye
 * @Date 2022/3/15
 */
public class ResourcesTest {

    @Test
    public void main() throws Exception {
        InputStream resourceAsSteam = Resources.getResourceAsSteam("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsSteam);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        User user = new User();
        user.setId(1);
        user.setUsername("zhangshan");
        User user1 = sqlSession.selectOne("User.selectOne", user);
        System.out.println(user1);

    }
}
