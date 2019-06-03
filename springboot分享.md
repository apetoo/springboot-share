

# springboot分享

## springboot注解及基本使用
### springboot 目录格式
1. static
2. templates

### 启动类
1. 默认启动方式
```
public class SpringbootShareApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootShareApplication.class, args);
    }
}

```
2. 使用SpringApplicationBuilder构建
```
public class bootstrapOne {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(bootstrapOne.class)
                .web(WebApplicationType.NONE)
                .sources()
                .run(args);

        context.close();
    }
}

```
### 自定义banner
在resources下创建banner.txt(默认读取以此名开头)


### 异步servlet简单实现
使用 java中 @WebServlet 注解声明为一个webServlet 并继承 HttpServlet

1. req.startAsync();
2. asyncContext.start() //异步
3. 业务逻辑
4. asyncContext.complete(); 触发

以上顺序不能更改
eg: 
```
@WebServlet(urlPatterns = "/myServlet",asyncSupported = true)
public class MyServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        AsyncContext asyncContext = req.startAsync();
        asyncContext.start(() -> {
            try {
                resp.getWriter().println("hello-world");
                //触发
                asyncContext.complete();
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
    }
}
```

## 注解的派生与层次
```
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface MyComponent {

    String value() default "";
}
```

##### 注:
- MyComponent相当于继承了@Component所有功能

## 手动装配
### 1.@Profile("name")

可以将注解写在类上/方法上  表示只有在指定profile值相等的情况才会加载
```
@Profile("java7")
@Configuration()
public class Java7CalculateConfiguration  {
    ...
}

-------------------------------------------

@Profile("java8")
@Configuration()
public class Java7CalculateConfiguration  {
    ...
}

```
##### 使用:
- ConfigurableApplicationContext 中指定profile值
### 2.@Enable...

1.  @EnableScheduling  简单手动装配
```
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(SchedulingConfiguration.class) //直接指定Configuration
@Documented
public @interface EnableScheduling {

}
```
2. @EnableCaching  实现ImportSelector复杂手动装配
```
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(CachingConfigurationSelector.class)
public @interface EnableCaching {
    ...
}
```
##### 使用:
- 在启动类上加此类型注解则可实现自动装配



**实现手动装配**

1. 创建注解EnableHelloWorld
```
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
//@Import(HelloWorldConfiguration.class)  不灵活
@Import(HelloWorldImportSelector.class)  //灵活
public @interface EnableHelloWorld {
}
```
2. 创建Selector
```
public class HelloWorldImportSelector implements ImportSelector {

    /**
     * 返回类全路径用来初始化bean
     * @param importingClassMetadata
     * @return
     */
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        String name = HelloWorldConfiguration.class.getName();
        System.out.println(name);
        return new String[]{name};
    }
}

```

**注:**
- 返回的为被加载bean全路径

使用: 在启动类上加@EnableAutoConfiguration所有@Enable开头的注解生效(规范为Enable开头)

## 自动装配

- spring中有很多诸如此类spring.factories的自动装配
META-INF/spring.factories
使用:在启动类上加@EnableAutoConfiguration注解

- spring-boot-autoconfigure-2.1.5.RELEASE.jar中/META-INF/spring.factories中有很多自动装配,其中EnableAutoConfiguration接口中就有我们常用的WebMvcAutoConfiguration

- 自动装配 格式:1注释 2.接口全类名 3.实现 以\分隔 

```
# Auto Configure
org.springframework.boot.autoconfigure.EnableAutoConfiguration=\
org.springframework.boot.autoconfigure.admin.SpringApplicationAdminJmxAutoConfiguration,\
org.springframework.boot.autoconfigure.aop.AopAutoConfiguration,\
org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration,\
org.springframework.boot.autoconfigure.batch.BatchAutoConfiguration,\
org.springframework.boot.autoconfigure.cache.CacheAutoConfiguration,\
org.springframework.boot.autoconfigure.cassandra.CassandraAutoConfiguration,\
org.springframework.boot.autoconfigure.cloud.CloudServiceConnectorsAutoConfiguration,\
org.springframework.boot.autoconfigure.context.ConfigurationPropertiesAutoConfiguration,\
org.springframework.boot.autoconfigure.context.MessageSourceAutoConfiguration,\
org.springframework.boot.autoconfigure.context.PropertyPlaceholderAutoConfiguration,\
org.springframework.boot.autoconfigure.couchbase.CouchbaseAutoConfiguration,\
...
```

**实现自动装配**
- 此实现包含以上手动装配条件()
在项目的META-INF下创建spring.factories
```
#自动装配
org.springframework.boot.autoconfigure.EnableAutoConfiguration=\
com.warape.springbootshare.base.config.HelloWorldAutoConfiguration
```


- 启动类
```
/**
 * EnableAutoConfiguration 引导类
 * @program: springboot-share
 * @description:
 * @author: 万明宇
 * @create: 2019-05-28 19:03
 *
 * 条件判断  user.name=warape
 * 模块注解  @Configuration
 * @Enable @EnableHelloWorld -->  HelloWorldImportSelector --> HelloWorldConfiguration --> demoScan
 **/
//@EnableCaching
//@EnableScheduling
@ComponentScan(basePackages = "com.warape.springbootshare.base.other") //需要解答
@EnableAutoConfiguration(exclude = DataSourceAutoConfiguration.class)  //自动装配
public class EnableAutoConfigurationBootstrap {


    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(EnableAutoConfigurationBootstrap.class)
                .web(WebApplicationType.NONE)
                .run(args);


        context.close();
    }
}

```



## springboot集成以及配置

### aop

### jedis配置

### 线程池

### Web

### 全局异常

### 拦截器

### 规范和配置文件