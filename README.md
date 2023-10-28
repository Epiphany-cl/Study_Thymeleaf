# Thymeleaf

---

<!-- TOC -->
* [Thymeleaf](#thymeleaf)
  * [一、 认识Thymeleaf](#一-认识thymeleaf)
  * [二、 准备工作(SSM)](#二-准备工作ssm)
    * [1. 添加依赖](#1-添加依赖)
    * [2. springMVC 视图解析器](#2-springmvc-视图解析器)
    * [3. 创建index.html](#3-创建indexhtml)
    * [4. 创建ThymeleafController](#4-创建thymeleafcontroller)
    * [5. 启动！](#5-启动)
  * [三、 Thymeleaf的表达式](#三-thymeleaf的表达式)
  * [四、 Thymeleaf的常见属性](#四-thymeleaf的常见属性)
<!-- TOC -->

---

## 一、 认识Thymeleaf

Thymeleaf 是一个流行的模板引擎，该模板引擎采用 Java 语言开发

模板引擎是一个技术名词，是跨领域跨平台的概念，在 Java 语言体系下有模板引擎， 在 C#、PHP 语言体系下也有模板引擎，甚至在
JavaScript 中也会用到模板引擎技术，Java 生态下的模板引擎有 Thymeleaf 、Freemaker、Velocity、Beetl（国产） 等。

Thymeleaf 对网络环境不存在严格的要求，既能用于 Web 环境下，也能用于非 Web 环境下。在非Web 环境下，他能直接显示模板上的静态数据；在
Web 环境下，它能像 Jsp 一样从后台接收数据并替换掉模板上的静态数据。它是基于 HTML 的，以 HTML 标签为载体， Thymeleaf 要寄托在
HTML 标签下实现。

SpringBoot 集成了 Thymeleaf 模板技术，并且 Spring Boot 官方也推荐使用 Thymeleaf 来替代 JSP 技术，Thymeleaf
是另外的一种模板技术，它本身并不属于 Spring Boot，Spring Boot 只是很好地集成这种模板技术，作为前端页面的数据展示，在过去的
Java Web 开发中，我们往往会选择使用 Jsp 去完成页面的动态渲染，但是 jsp 需要翻译编译运行，效率低

- [Thymeleaf 的官方网站](http://www.thymeleaf.org)
- [Thymeleaf 官方手册](https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html)

## 二、 准备工作(SSM)

### 1. 添加依赖

```xml
<!-- Spring5和Thymeleaf整合包 -->
<dependency>
    <groupId>org.thymeleaf</groupId>
    <artifactId>thymeleaf-spring5</artifactId>
    <version>3.0.12.RELEASE</version>
</dependency>
```

### 2. springMVC 视图解析器

```xml
<!--thymeleaf-->
<bean id="viewResolver" class="org.thymeleaf.spring5.view.ThymeleafViewResolver">
    <property name="order" value="1"/>
    <property name="characterEncoding" value="UTF-8"/>
    <property name="templateEngine">
        <bean class="org.thymeleaf.spring5.SpringTemplateEngine">
            <property name="templateResolver">
                <bean class="org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver">
                    <!-- 视图前缀 -->
                    <property name="prefix" value="/"/>
                    <!-- 视图后缀 -->
                    <property name="suffix" value=".html"/>
                    <property name="templateMode" value="HTML5"/>
                    <property name="characterEncoding" value="UTF-8" />
                </bean>
            </property>
        </bean>
    </property>
</bean>
```

### 3. 创建index.html

```html
<!DOCTYPE html>
<html lang="zh" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<h1>hello Thymeleaf</h1>
<!--Thymeleaf 前端框架以Html 为载体-->
<span th:text="${data}"></span>
<p th:text="${data}"></p>
<div th:text="${data}"></div>
</body>
</html>
```

> HTML 页面的<html>元素中加入以下属性：`<html xmlns:th="http://www.thymeleaf.org">`

### 4. 创建ThymeleafController

```java
@Controller
@RequestMapping("/thymeleaf")
public class ThymeleafController {
    //http://localhost:8080/thymeleaf01/thymeleaf/index
    @RequestMapping(value = "/index")
    public String index(Model model) {
        model.addAttribute("data", "SpringBoot 成功集成 Thymeleaf 模版！");
        return "index";
    }
}
```

### 5. 启动！

![image.png](https://s2.loli.net/2023/10/12/o6LtRQ5WKw3avxT.png)


## 三、 Thymeleaf的表达式

- 标准变量表达式 `${……}`

```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>user</title>
</head>
<body>
<h2>展示 User 用户信息：</h2>
用户编号：<span th:text="${user.id}"></span><br/>
用户姓名：<span th:text="${user.name}"></span><br/>
用户手机号：<span th:text="${user.phone}"></span><br/>
用户地址：<span th:text="${user.address}"></span><br/>
用户生日：<span th:text="${#dates.format(user.birthday,'yyyy-MM-dd')}"></span><br/>
<hr/>
<button th:onclick="show([[${user.id}]])">show</button>
</body>
</html>
<script>
    function show(i) {
        alert(i)
    }
</script>
```

- 选择变量表达式 `*{……}`（了解，不推荐使用）

```html
<h2>展示 User 用户信息（星号表达式,仅在 div 范围内有效）：</h2>
<div th:object="${user}">
    用户编号：<span th:text="*{id}"></span><br/>
    用户姓名：<span th:text="*{name}"></span><br/>
    用户手机号：<span th:text="*{phone}"></span><br/>
    用户地址：<span th:text="*{address}"></span><br/>
</div>
```

- URL表达式 `@{……}`

主要用于链接、地址的展示，可用于
`<script src="...">、<link href="...">、<a href="...">、<form action="...">、<img src="">`等，可以在 URL 路径中动态获取数据

## 四、 Thymeleaf的常见属性

- `th:each`

```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>userList</title>
</head>
<body>
<h1>th:each 循环遍历 List 集合</h1>
<div style="color: red"> 1.user:当前对象的变量名<br/>
    2.userStat:当前对象的状态变量名<br/>
    3.${userList}:循环遍历的集合<br/> 4.变量名自定义
</div>
<div th:each="user,userStat:${list}" th:style="'background-color:'+@{(${userStat.odd}?'red':'blue')}">
    <span th:text="${userStat.index}"></span>
    <span th:text="${user.id}"></span>
    <span th:text="${user.name}"></span>
    <span th:text="${user.phone}"></span>
    <span th:text="${user.address}"></span>
    <span th:text="${#dates.format(user.birthday,'yyyy-MM-dd HH:mm:ss')}"></span>
</div>
</body>
</html
```