Spring Boot Admin
=================================================
使用Spring Boot Admin监控Spring Boot 应用

- spring-boot-admin-server

    spring boot admin 服务端，每一个注册的spring boot客户端的监控数据都在该UI界面展现
    
    http://localhost:8000
    
- spring-boot-admin-client

    spring boot 客户端，每一个需要监控的客户端都需要向服务端注册
    
    _application.properties_
    ```
    server.port=8080
    spring.application.name=Spring Boot Client
    spring.boot.admin.client.url=http://localhost:8000 # 指定spring boot admin server的地址
    management.endpoints.web.exposure.include=*
    ```