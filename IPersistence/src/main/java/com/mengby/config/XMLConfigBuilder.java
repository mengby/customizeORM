package com.mengby.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mengby.io.Resources;
import com.mengby.pojo.Configuration;
import com.mengby.pojo.MappedStatement;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import javax.xml.parsers.SAXParser;
import java.beans.PropertyVetoException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

/**
 * @author bingye
 * @Date 2022/3/15
 */
public class XMLConfigBuilder {


    private static final String DRIVER_CLASS = "driverClass";
    private static final String JDBC_URL = "jdbcUrl";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String RESOURCE = "resource";


    private Configuration configuration;

    public XMLConfigBuilder() {
        this.configuration = new Configuration();
    }

    /**
     * 将配置文件解析
     */
    public Configuration parseConfig(InputStream inputStream) throws DocumentException, PropertyVetoException {

        final Document document = new SAXReader().read(inputStream);
        final Element rootElement = document.getRootElement();
        // configuration
        final List<Element> dataSourceList = rootElement.selectNodes("//propety");
        final Properties properties = new Properties();
        dataSourceList.forEach(element -> {
            final String name = element.attributeValue("name");
            final String value = element.attributeValue("value");
            properties.setProperty(name,value);
        });
        // connect pool
        final ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setDriverClass(properties.getProperty(DRIVER_CLASS));
        comboPooledDataSource.setJdbcUrl(properties.getProperty(JDBC_URL));
        comboPooledDataSource.setUser(properties.getProperty(USERNAME));
        comboPooledDataSource.setPassword(properties.getProperty(PASSWORD));
        configuration.setDataSource(comboPooledDataSource);

        // mapper.xml
        final List<Element> mapperList = rootElement.selectNodes("//mapper");
        mapperList.forEach(element -> {
            final String resource = element.attributeValue(RESOURCE);
            final InputStream resourceAsSteam = Resources.getResourceAsSteam(resource);
            final XMLMapperBuilder xmlMapperBuilder = new XMLMapperBuilder(configuration);
            xmlMapperBuilder.parse(resourceAsSteam);
        });
        return configuration;
    }
}