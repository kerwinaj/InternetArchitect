# mybatis

MyBatis是一个优秀的持久层框架，它对jdbc的操作数据库的过程进行封装，使开发者只需要关注SQL本身，而不需要花费精力去处理例如注册驱动、创建connection、创建statement、手动设置参数、结果集检索等jdbc繁杂的过程代码。



hibernate 与mybatis的比较

> 首先是运行速度，hibernate是在jdbc上进行了一次封装，而mybatis基于原生的jdbc，因此mybatis天生就有运行速度上的优势。
>
> 然后mybatis开放了插件接口。也许mybatis团队知道自己人少力单，索性把很多功能接口都开放了。不好分页？网上大神写的分页插件多得很；需要手写sql？按注解生成自动生成sql的插件早就有了；还有缓存的插件等等。可以说，只要肯在mybatis上花时间，你会发现orm这一块的所有问题它都有解决方案。这方面不是说hibernate不好，但是我还真没听说过hibernate有插件了。
>
> 还有就是对遗留系统的支持。很多系统在设计之初还没有orm思想，现在想“抢救”一下，用mybatis就比hibernate更合适。因为mybatis可以很容易做到不规范的映射对象和规范的映射对象共存，如果这种系统中再需要增加个需要复杂sql的功能，mybatis只需要把sql手写出来，先把功能运行起来后再看看能不能变成自动生成的sql，而对hibernate来说就很困难了。
>
> 作者：李萌
> 链接：https://www.zhihu.com/question/21104468/answer/85524803
> 来源：知乎
> 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

> 我的经验是主要看这3个指标来确定，第一个因素权重最大。
> 1、数据量、有以下情况的最好就用MyBatis
>     如果有超过千万级别的表，
>     如果有单次业务大批量数据提交的需求(百万条及其以上的)，这个尤其不建议用hibernate
>     如果有单次业务大批量数据读取需求(百万条及其以上的)
> 2、表关联复杂度
>     如果主要业务表的关联表超过20个的(大概数量，根据表的大小不同有差异)不建议用hibernate
> 3、人员
>     如果开发成员多数不是多年使用hibernate的情况(一般开发水平评估)，建议使用MyBatis
>
> 作者：老Tom
> 链接：https://www.zhihu.com/question/21104468/answer/84997949
> 来源：知乎
> 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

jdbc, spring jdbc template --> mybatis --> hibernate(重量级)

mybatis的配置文件, 相对于jpa来说就是解耦了(改个表名啥的就方便了)

## 原理

SqlSessionFactory创建SqlSession

![image-20200514224027030](readme.assets/image-20200514224027030.png)



## 配置文件



### MybatisConfig.xml

  SSM中需要配置

- 数据url
- 数据库连接池
- 映射文件
- 事务

在SpringBoot中整合到property中了

### Mapper.xml

#### namespace

接口绑定 和接口

就可以不用写DAO实现类，Mybatis会通过绑定自动找到要执行的sql语句。

**resultMap**

结果集对应到实体类的字段到属性映射

## xml 方式

## 传统方式

#### xml配置



```xml
<?xml version="1.0" encoding="UTF-8"?>  
<!DOCTYPE configuration PUBLIC "-//mybatis.org//DTD Config 3.0//EN" "http://mybatis.org/dtd/mybatis-3-config.dtd">  
  
<!-- version: $Id$ -->  
<configuration>  
    <!-- 引用JDBC属性的配置文件 -->  
    <properties resource="database.properties" />  
  
    <environments default="development">  
        <environment id="development">  
            <!-- 使用JDBC的事务管理 -->  
            <transactionManager type="JDBC" />  
            <!-- POOLED ：JDBC连接对象的数据源连接池的实现，不直接支持第三方数据库连接池 -->  
            <dataSource type="POOLED">  
                <property name="driver" value="${database.driver}" />  
                <property name="url" value="${database.url}" />  
                <property name="username" value="${database.username}" />  
                <property name="password" value="${database.password}" />  
            </dataSource>  
        </environment>  
  
    </environments>  
  
    <!-- ORM映射文件 -->  
    <mappers>  
        <!-- 注解的方式 -->  
        <mapper class="com.iflytek.dao.mapper.AccountMapper" />  
        <!-- XML的方式 -->  
        <mapper resource="com/mashibing/dao/xml/AccountMapper.xml" />  
        <!-- 这里对于注解，还可以通过<package name="com.mashibing.dao.mapper"/> -->  
    </mappers>  
</configuration>  
```



#### Service 配置



```java
public class AccountService {  

    public boolean insertAccount(Account account) {  
        boolean flag = false;  
        SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();  
        try {  
            accountMapper accountMapper = sqlSession.getMapper(AccountMapper.class);  
            int result = accountMapper.insert(student);  
            if (result > 0) {  
                flag = true;  
            }  
            sqlSession.commit();  
        } finally {  
            sqlSession.close();  
        }  
        return flag;  
    }  
  

    public Student getAccountById(int id) {  
        SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();  
        try {  
            AccountMapper AccountMapper = sqlSession.getMapper(AccountMapper.class);  
            return AccountMapper.selectByPrimaryKey(id);  
        } finally {  
            sqlSession.close();  
        }  
    }  
  

    public List<Student> getAllStudents() {  
        SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();  
        try {  
            StudentMapper StudentMapper = sqlSession.getMapper(StudentMapper.class);  
            return StudentMapper.selectByExample(new StudentExample());  
        } finally {  
            sqlSession.close();  
        }  
    }  
  

    public boolean updateAccount(Account account) {  
        SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();  
        boolean flag = false;  
        try {  
            AccountMapper AccountMapper = sqlSession.getMapper(AccountMapper.class);  
            int result = AccountMapper.updateByPrimaryKey(Account);  
            if (result > 0) {  
                flag = true;  
            }  
            sqlSession.commit();  
        } finally {  
            sqlSession.close();  
        }  
        return flag;  
  
    }  
  
 
    public boolean deleteAccount(int id) {  
        SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();  
        boolean flag = false;  
        try {  
            AccountMapper AccountMapper = sqlSession.getMapper(AccountMapper.class);  
            int result = AccountMapper.deleteByPrimaryKey(id);  
            if (result > 0) {  
                flag = true;  
            }  
            sqlSession.commit();  
        } finally {  
            sqlSession.close();  
        }  
        return flag;  
    }  
  
}  
```







## 与SpringBoot整合

### 引入依赖

主要是: mybatis-spring-boot-starter

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>2.1.6.RELEASE</version>
		<relativePath/> <!-- lookup parent from repository -->
	</parent>
	<groupId>com.example</groupId>
	<artifactId>springboot03-mybatis</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>springboot03-mybatis</name>
	<description>Demo project for Spring Boot</description>

	<properties>
		<java.version>1.8</java.version>
	</properties>

	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-thymeleaf</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
		<dependency>
			<groupId>org.mybatis.spring.boot</groupId>
			<artifactId>mybatis-spring-boot-starter</artifactId>
			<version>2.0.1</version>
		</dependency>

		<dependency>
			<groupId>mysql</groupId>
			<artifactId>mysql-connector-java</artifactId>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-devtools</artifactId>
		</dependency>
		
	</dependencies>

	<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
			</plugin>
		</plugins>
	</build>

</project>

```



### mapper

``` xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.mashibing.springboot.mapper.AccountMapper">
   
   <resultMap type="com.mashibing.springboot.mapper.Account" id="BaseResultMap">
   
   	<result column="login_name" property="loginName"/>
   	<result column="password" property="password"/>
   
   </resultMap>
   
   
    <insert id="save" parameterType="Account">
        INSERT INTO account(login_name,password)
        VALUES
        (
        #{loginName},#{password}
        )
    </insert>
    
    <select id="findAll" resultMap="BaseResultMap">
        select * from account
    </select>
    
</mapper>



```

### application.properties

``` pr
spring.datasource.url=jdbc:mysql://localhost:3306/ssm?characterEncoding=utf8&useSSL=false&serverTimezone=UTC
##数据库用户名
spring.datasource.username=root
##数据库密码
spring.datasource.password=840416

# 用来实例化mapper接口
mybatis.type-aliases-package=com.mashibing.springboot.mapper
# 对应的sql映射
mybatis.mapper-locations=classpath:mybatis/mapper/*.xml
```

### AccountMapper

``` java
package com.mashibing.springboot.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountMapper {

	void save(Account account);
}


```

### Account

``` java

public class Account {
	private int id;
	private String loginName;
	private String password;
	private String nickName;
	private int age;
	private String location;
	private int banlance;
}
```



### 显示日志

logging.level.com.mashibing.springboot.mapper=debug



## 注解查询

```java
	@Select("select * from account1")
	List<Account> findAll();

```





## 查找mapper接口

两种方法

### 在入口加入 MapperScan

@MapperScan("com.mashibing.springboot.mapper")
public class Springboot03MybatisApplication {

### 每一个mapper接口上加入

@Mapper
public interface AccountMapper {

## Mapper 自动生成

### eclipse插件 市场搜素

MyBatis Generator

### 图形化

https://github.com/zouzg/mybatis-generator-gui

对于一个menu表, 会自动生成下面4个文件

![image-20200515010829268](readme.assets/image-20200515010829268.png)

## 分页查询



### 依赖

```xml
<!-- https://mvnrepository.com/artifact/com.github.pagehelper/pagehelper-spring-boot-starter -->
<dependency>
    <groupId>com.github.pagehelper</groupId>
    <artifactId>pagehelper-spring-boot-starter</artifactId>
    <version>1.2.12</version>
</dependency>

```

### Service

```java
	public Object findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		AccountExample example = new AccountExample();
		return mapper.selectByExample(example );
	}
```

