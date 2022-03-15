package com.mengby.pojo;

/**
 * @author bingye
 * @Date 2022/3/15
 */
public class MappedStatement {
    /*
    <mapper namespace="User">
        <select id="selectList" resultType="com.mengby.pojo.User">
            select * from users;
        </select>
        <select id="selectOne" resultType="com.mengby.pojo.User" paramterType="com.mengby.pojo.User">
            select * from users where id = #{id} and username = #{username}
        </select>
    </mapper>
     */

    // id标识
    private String id;
    // 返回值类型
    private String resultType;
    // 参数值类型
    private String paramterType;
    // sql
    private String sql;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public String getParamterType() {
        return paramterType;
    }

    public void setParamterType(String paramterType) {
        this.paramterType = paramterType;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }
}
