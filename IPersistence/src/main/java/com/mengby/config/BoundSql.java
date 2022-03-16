package com.mengby.config;

import com.mengby.utils.ParameterMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bingye
 * @Date 2022/3/16
 */
public class BoundSql {

    private String sqlText;
    private List<ParameterMapping> parameterMappings;

    public BoundSql(String sqlText, List<ParameterMapping> parameterMappings) {
        this.sqlText = sqlText;
        this.parameterMappings = parameterMappings;
    }

    public String getSqlText() {
        return sqlText;
    }

    public void setSqlText(String sqlText) {
        this.sqlText = sqlText;
    }

    public List<ParameterMapping> getParameterMappings() {
        return parameterMappings;
    }

    public void setParameterMappings(List<ParameterMapping> parameterMappings) {
        this.parameterMappings = parameterMappings;
    }
}
