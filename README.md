# rpc-spring-boot-starter
利用netty实现的rpc框架
- 支持Java序列化和protobuf序列化协议
- 支持多种客户端负载均衡（随机、轮询、加权轮询和平滑加权轮询）算法

## 使用方法

1、下载代码到本地
```bash
git clone https://github.com/lin546/rpc-spring-boot-starter.git
```

2、打包到本地
```bash
mvn  clean install -DskipTests=true
```

3、添加maven依赖到你的项目

```bash
    <dependency>
           <groupId>cn.sp.rpc</groupId>
           <artifactId>rpc-spring-boot-starter</artifactId>
           <version>1.0.0-SNAPSHOT</version>
    </dependency>
```

## 客户端

使用`@InjectService` 注解注入远程方法
```java
@RestController
@RequestMapping("test")
public class TestController {

   @InjectService
   private UserService userService;

   @GetMapping("/user")
   public ApiResult<User> getUser(@RequestParam("id")Long id){
       return userService.getUser(id);
   }
}
```

配置项：
```
sp.rpc.protocol	            #消息序列化协议	 可选:java，protobuf(默认)，kryo
sp.rpc.register-address	    #注册中心地址         默认localhost:2181
sp.rpc.load-balance	    #负载均衡算法	 random、round（默认）、weightRound、smoothWeightRound
```

## 服务端

提供远程方法并注入IOC

```java
import cn.sp.rpc.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class UserServiceImpl implements UserService{

   private static  Logger logger = LoggerFactory.getLogger(UserService.class);


   @Override
   public ApiResult<User> getUser(Long id) {
       logger.info("现在是【2】号提供服务");
       User user = new User(1L,"XX",2,"www.aa.com");
       return ApiResult.success(user);
   }
}
```
注意： 这里的`@Service`注解是自定义的，不是Spring的。

配置项：
```
sp.rpc.protocol	            消息序列化协议	    java，protobuf(默认)，kryo
sp.rpc.register-address	    注册中心地址	            默认localhost:2181
sp.rpc.server-port	    服务端通信端口号	    默认9999
sp.rpc.weight	            权重	            默认1
```

## 运行

1.本地启动zookeeper

2.本地启动一个消费者，两个服务端，轮询算法

3.访问：http://localhost:8080/test/user?id=1
