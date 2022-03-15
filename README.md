# customizeORM
自定义的持久化框架(本质就是对JDBC代码的封装)

设计思路
总共两个角色:
- 使用端:
   提供两部分配置信息:数据库配置信息、sql配置信息:sql语句、参数类型、返回值类型
  - sqlMapConfig.xml ： 存放数据库配置信息
  - Mapper.xml: 存放sql配置信息

- 自定义持久化框架端:
  - 加载配置文件
  - 解析配置文件(通过javaBean存放)  dom4j
      - 创建SqlSessionFactoryBuilder
      - 创建SqlSessionFactory:生产sqlSession：回话对象(工厂模式)
  - 创建SqlSessionFactory接口及实现类
      - openSession()
  - 创建SqlSession接口及实现DefaultSession
      - 创建建CRUD
  - 创建Exector接口及实现类
      - query(Configration,MappedStatement,Object ...params) 执行JDBC代码
   