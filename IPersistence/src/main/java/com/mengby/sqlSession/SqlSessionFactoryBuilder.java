package com.mengby.sqlSession;

import com.mengby.config.XMLConfigBuilder;
import com.mengby.pojo.Configuration;
import org.dom4j.DocumentException;

import java.beans.PropertyVetoException;
import java.io.InputStream;

/**
 * @author bingye
 * @Date 2022/3/15
 */
public class SqlSessionFactoryBuilder {

    /**
     *
     * @param in 输入一个解析为inputStream
     * @return 返回一个SqlSessionFactory
     */
    public SqlSessionFactory build(InputStream in) throws DocumentException, PropertyVetoException {
        // step1 用dom4j解析xml文件到 Configuration
        final XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder();
        final Configuration configuration = xmlConfigBuilder.parseConfig(in);
        // step2 创建SqlSessionFactory
        return new DefaultSqlSessionFactory(configuration);
    }
}
