## 项目(FirstSpringBoot04)
描述: 使用内存来保存数据, 还没有使用数据库
Get: 接受前台的参数

* @RequestParam("id") Integer id
* @ModelAttribute City city

## 项目(SpringBootMVC01)
描述: 使用jpa链接数据库
Get: 使用jpa(java presistence api)

1. pom.xml中需要加上jpa和mysql的依赖;

```
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>
		<dependency>
			<groupId>mysql</groupId>
			<artifactId>mysql-connector-java</artifactId>
			<scope>runtime</scope>
		</dependency>
```

2. dao层, CityRepository这个是个接口, 并且需要extends JpaRepository<City, Integer>, 两个泛型参数分别是实体和主键;
3. City需要加上@Entity和@Table两个注解, 这两个都是javax.persistence-api-2.2.jar里面的;
4. application.properties里加上spring.datasource.url, spring.datasource.username, spring.datasource.database三个参数, 用来连接数据库

## 项目(SpringBootMVC02 - jsp)

描述: 使用jsp

Get: 接受前台的参数

* @PathVariable("id") Integer id



使用jsp

1. pom.xml中需要加上相关的依赖才能使用jsp;

```
	<!-- jstl -->
	<dependency>
		<groupId>javax.servlet</groupId>
		<artifactId>jstl</artifactId>
	</dependency>
	<!-- jasper -->
	<dependency>
		<groupId>org.apache.tomcat.embed</groupId>
		<artifactId>tomcat-embed-jasper</artifactId>
		<scope>provided</scope>
	</dependency>
```

另外, 要把thymeleaf的依赖去掉, 不然访问http://localhost:8080/city/list/1的时候, 通过thymeleaf去渲染list1这个页面了.

2. app里设置jsp文件目录

```
spring.mvc.view.prefix=/WEB-INF/jsp/
spring.mvc.view.suffix=.jsp
```

这里webapp是根目录, 下面的html页面是能直接访问到的, 访问地址为http://localhost:8080/xxoo.html

tomcat对WEB-INF这个目录是有保护的, 无法直接访问到http://localhost:8080/WEB-INF/xxoo.html