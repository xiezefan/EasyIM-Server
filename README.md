#EasyIM-Server

EasyIM是一个移动即时通讯应用。是我的本科毕业设计。  
  
因为实习占用了大四的大部分时间，导致EasyIM只完成了部分功能便参与答辩。虽然最终侥幸过了毕设答辩，但效果与自己期望的相去甚远。  
  
开始工作后，有比较多的空闲时间，我打算慢慢完善该项目，并将近期所学习的一些新技术应用到该项目中。  


## 简述

该项目分为两部分：

* [EasyIM-Server][1] : EasyIM服务端
* [EasyIM-Android][2] : EasyIM Android端

EasyIM-Server主要完成一下功能：

* 用户基础功能
* 好友功能
* 点对点的消息发送功能
* 离线消息功能

### 目录结构

```
.
├── README.md
├── pom.xml 
├── sql 数据库文件
└── src 
    ├── main/java/me/xiezefan/easyim/server
    │   │   ├── common --- 业务公共类
    │   │   ├── dao ------ 数据持久化层
    │   │   ├── filter --- 权限过滤
    │   │   ├── model ---- 实体类
    │   │   ├── resource - Restful API层
    │   │   ├── service -- 逻辑业务层
    │   │   ├── util ----- 工具类
    │   │   └── web ------ Web相关
    │   ├── resources
    │   │   ├── applicationContext.xml
    │   │   ├── log4j.properties
    │   │   ├── mapper ---  MyBatis映射文件
    │   │   └── mybatis-configuration.xml
    │   └── webapp/WEB-INF  
    └── test

```

### 快速运行

前置条件：

* 安装Mysql
* 安装Maven

运行步骤：

1. 创建数据库`easyim`,并导入`sql/easy_im_v1.sql`
2. 修改`src/main/resources/applicationContext.xml`中mysql的配置
3. 在`EasyIM-Server`根目录执行`maven jetty:run`
4. 使用Post请求`localhost:8080/EasyIM-Server/users/register`确定服务是否正常启动


### 框架与技术

EasyIM Server 主要为EasyIM客户端提供Restful API。 主要使用了以下框架及第三方服务：

* Jersey   主要提供Restful支持
* Jetty    Server运行的容器(亦支持Tomcat)
* Spring   提供依赖注入
* MyBatis  数据库映射框架
* MySQL    
* RabbitMQ 主要做消息发送队列用（暂未实行）
* 七牛云    用户发送的图片，语言存储服务
* JPush    提供最核心的消息推送支持

### API 

#### Authoirzation

EasyIM使用Basic认证， 除了用户登录，注册接口外，其余接口都需要将用户名`username`，密码`password`依照以下规制生成`authcode`，在每次API请求时，附带在`headers`中的`Authorization`中。

```
authcode = "Basic " + Base64(username:md5(password))
```
(一种更合理的设计应该是，第一次鉴权后，生成一个有一定时限的`access_token`来作为`authcode`)

#### User 用户接口

**POST ~/users/login**  用户登录  
**POST ~/users/register** 用户注册  
**GET ~/users** 获取用户列表  
**GET ~/users/search** 用户搜索
**GET ~/users/{user_id}** 获取指定用户的信息  
**PUT ~/users/self** 更新个人信息  

#### Friendship 好友接口

**POST ~/friends** 添加好友关系  
**DELETE ~/friends/{friend_id}** 删除指定好友关系  
**GET ~/friends** 获取当前用户的好友列表

#### Message 消息发送接口

**POST /mesasges/send** 发送消息(加好友，文本，图文)  
**GET /messages/offline** 获取离线消息  
**PUT /messages/status**  批量更新消息状态(主要为标记离线/未读消息)  
**PUT /messages/status/{mid}** 更新指定消息状态



[1]:https://github.com/xiezefan/EasyIM-Server
[2]:https://github.com/xiezefan/EasyIM-Android