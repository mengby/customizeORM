package com.mengby.config;

import com.mengby.pojo.Configuration;
import com.mengby.pojo.MappedStatement;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.List;

/**
 * @author bingye
 * @Date 2022/3/15
 */
public class XMLMapperBuilder {

    private Configuration configuration;
    private static final String NAMESPACE = "namespace";
    public static final String ID = "id";
    public static final String RESULT_TYPE = "resultType";
    public static final String PARAMETER_TYPE = "parameterType";

    public XMLMapperBuilder(Configuration configuration) {
        this.configuration = configuration;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void parse(InputStream resourceAsSteam) {
        Document mappersDocument = null;
        try {
            mappersDocument = new SAXReader().read(resourceAsSteam);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        final Element mappersDocumentRootElement = mappersDocument.getRootElement();
        final String namespace = mappersDocumentRootElement.attributeValue(NAMESPACE);
        final List<Element> selectElements = mappersDocumentRootElement.selectNodes("//select");
        selectElements.forEach(element -> {
            final String id = element.attributeValue(ID);
            final String resultType = element.attributeValue(RESULT_TYPE);
            final String parameterType = element.attributeValue(PARAMETER_TYPE);
            final String sql = element.getTextTrim();
            final MappedStatement mappedStatement = new MappedStatement();
            mappedStatement.setId(id);
            mappedStatement.setResultType(resultType);
            mappedStatement.setParameterType(parameterType);
            mappedStatement.setSql(sql);
            configuration.getMappedStatementMap().put(namespace + "." +id,mappedStatement);
        });
    }
}
