

# springboot分享

## springboot注解及基本使用
### springboot 目录格式
1. static
2. templates

### 启动类
1. 默认启动方式
```java
public class SpringbootShareApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootShareApplication.class, args);
    }
}

```
2. 使用SpringApplicationBuilder构建
```java
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


### 项目配置文件结构

- application.yml
  - application-local.properties
  - application-local.yml
  - application-dev.properties
  - application-dev.yml
  - application-pro.properties
  - application-pro.yml
  

### 异步servlet简单实现
使用 java中 @WebServlet 注解声明为一个webServlet 并继承 HttpServlet

1. req.startAsync();
2. asyncContext.start() //异步
3. 业务逻辑
4. asyncContext.complete(); 触发

以上顺序不能更改
eg: 
```java
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
```java
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
```java
@Profile("java7")
@Configuration()
public class Java7CalculateConfiguration  {
    //...
}

//-------------------------------------------

@Profile("java8")
@Configuration()
public class Java7CalculateConfiguration  {
    //...
}

```

##### 使用:
- ConfigurableApplicationContext 中指定profile值
##### 场景:
- 可以根据不同的参数加载不同的bean

### 2.@Enable...

1.  @EnableScheduling  简单手动装配
```java
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(SchedulingConfiguration.class) //直接指定Configuration
@Documented
public @interface EnableScheduling {

}
```
2. @EnableCaching  实现ImportSelector复杂手动装配
```java
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(CachingConfigurationSelector.class)
public @interface EnableCaching {
    //...
}
```
##### 使用:
- 在启动类上加此类型注解则可实现自动装配



**实现手动装配**

1. 创建注解EnableHelloWorld
```java
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
//@Import(HelloWorldConfiguration.class)  不灵活
@Import(HelloWorldImportSelector.class)  //灵活
public @interface EnableHelloWorld {
}
```
2. 创建Selector
```java
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


3. Condition 条件装配
```java
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD })
@Documented
@Conditional(OnSystemPropertyCondition.class)
public @interface ConditionOnSystemProperty {

    String name();

    String value();
}

```

可以根据一些自定义条件实现装配
```java
public class OnSystemPropertyCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Map<String, Object> attributes = metadata.getAnnotationAttributes(ConditionOnSystemProperty.class.getName());
        String name = (String) attributes.get("name");
        String value = (String) attributes.get("value");
        Environment environment = context.getEnvironment();
        String property = environment.getProperty("properties");
        return property.equals(value);
    }
}
```
启动类

```java
public class SystemPropertyConditionBootstrap {


    //条件试注解  是否使bean生效
    @ConditionOnSystemProperty(name = "user.name", value = "warape")
    @Bean
    public String helloWorld() {
        return "条件试注解 hello world";
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(SystemPropertyConditionBootstrap.class)
                .web(WebApplicationType.NONE)
                .run(args);


        String helloWorld = null;
        try {
            helloWorld = context.getBean("helloWorld", String.class);
        } catch (NoSuchBeanDefinitionException e) {
            System.out.println("没有找到该 (helloWorld) Bean");
        }

        System.out.println(helloWorld);

        context.close();
    }
}

```
使用: 将@ConditionOnSystemProperty注解写在被装配类上

场景: 比如springboot中他在装配的时候回首先判断是否有这个类(是否引入了此jar包下的哪个类等条件...),如果没有则不装配

## 自动装配

- spring中有很多诸如此类spring.factories的自动装配
META-INF/spring.factories
使用:在启动类上加@EnableAutoConfiguration注解

- spring-boot-autoconfigure-2.1.5.RELEASE.jar中/META-INF/spring.factories中有很多自动装配,其中EnableAutoConfiguration接口中就有我们常用的WebMvcAutoConfiguration

- 自动装配 格式:1注释 2.接口全类名 3.实现 以\分隔 

```properties
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
```properties
#自动装配
org.springframework.boot.autoconfigure.EnableAutoConfiguration=\
com.warape.springbootshare.base.config.HelloWorldAutoConfiguration
```


- 启动类
```java
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
ResponseAspect
### jedis配置
单机:JedisClientConfig
集群:JedisClusterConfig
### 线程池
ExecutorConfig  @EnableAsync
### Web
WebMvcConfig
### 全局异常
AppException & AppExceptionHandler
### 拦截器
MyInterceptor
### 定时器
@EnableScheduling
### freemaker
FreemakerController