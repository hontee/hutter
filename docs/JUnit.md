# 单元测试

- [Spring Boot Junit单元测试](https://yq.aliyun.com/articles/6925)
- [Spring Boot 性能优化](https://yq.aliyun.com/articles/2599)

## 断言 Assert的使用

- org.junit.Assert: 适用于JUnit测试
- org.springframework.util.Assert: 适用于程序中的条件判断

## 类的命名规则

1. 自动化测试的类存放在：com.hutter.master.auto.*
2. 普通类存在在：com.hutter.master.commons.*

需要执行自动化测试的类应严格遵循以下规则：

```
<plugin>
	<groupId>org.apache.maven.plugins</groupId>
	<artifactId>maven-surefire-plugin</artifactId>
	<configuration>
		<includes>
			<include>**/*Tests.java</include>
			<include>**/*Test.java</include>
		</includes>
		<excludes>
			<exclude>**/Abstract*.java</exclude>
		</excludes>
	</configuration>
</plugin>
```