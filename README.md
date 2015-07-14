#EasyIM-Server

EasyIM是一个移动即时通讯应用。是我的本科毕业设计。  
  
因为实习占用了大四的大部分时间，导致EasyIM只完成了部分功能便参与答辩。虽然最终侥幸过了毕设答辩，但成果与自己期望的相去甚远。  
  
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

目录结构：

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

[1]:https://github.com/xiezefan/EasyIM-Server
[2]:https://github.com/xiezefan/EasyIM-Android