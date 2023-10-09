server:
  port: 8080
<#if hasUndertow>
  undertow:
    # 设置IO线程数, 它主要执行非阻塞的任务,它们会负责多个连接。不要设置过大，如果过大，启动项目会报错：打开文件数过多
    # 默认值为8，建议设置每个CPU核心一个线程
    io-threads: 4
    # 阻塞任务线程池, 当执行类似servlet请求阻塞操作, undertow会从这个线程池中取得线程
    # 默认等于 io-threads*8，它的值设置取决于系统的负载，可适当调大该值
    worker-threads: 128
    # 每块buffer的空间大小，越小空间被利用越充分
    # 不要设置太大，以免影响其他应用，合适即可
    buffer-size: 1024
    # 是否分配的直接内存(NIO直接分配的堆外内存)
    # 默认false
    direct-buffers: true
    # HTTP POST请求最大的大小
    # 默认0，无限制；建议10485760(10M)
    max-http-post-size: 0
    allow-unescaped-characters-in-url: true
</#if>

management:
  endpoints:
    web:
      exposure:
        include: '*'
  endpoint:
    health:
      show-details: ALWAYS

spring:
  profiles:
    active: dev
  application:
    name: ${name}
<#--  main:-->
<#--    allow-bean-definition-overriding: true-->
  cloud:
    nacos:
      discovery:
        server-addr: 127.0.0.1:8848
        namespace: 82c52fe1-1024-49d8-8aeb-0ef353552a1d
        cluster-name: DEFAULT
      config:
        file-extension: yml
        server-addr: 127.0.0.1:8848
        namespace: 82c52fe1-1024-49d8-8aeb-0ef353552a1d
        cluster-name: DEFAULT
<#if hasDB>
  # 排除原生Druid的快速配置类
  autoconfigure:
    exclude: com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure
  datasource:
    dynamic:
      type: com.alibaba.druid.pool.DruidDataSource
      druid:
        # 连接池的配置信息: 初始化大小，最小，最大
        initial-size: 5
        min-idle: 5
        maxActive: 20
        # 配置获取连接等待超时的时间
        maxWait: 60000
        # 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
        timeBetweenEvictionRunsMillis: 60000
        # 配置一个连接在池中最小生存的时间，单位是毫秒
        minEvictableIdleTimeMillis: 300000
        validationQuery: SELECT 1 FROM DUAL
        testWhileIdle: true
        testOnBorrow: false
        testOnReturn: false
        # 打开PSCache，并且指定每个连接上PSCache的大小
        poolPreparedStatements: true
        maxPoolPreparedStatementPerConnectionSize: 20
        # 配置监控统计拦截的filters，去掉后监控界面sql无法统计，'wall'用于防火墙
        filters: stat,wall,slf4j
      datasource:
        master:
          url: jdbc:mysql://127.0.0.1:6301/db_name?&useUnicode=true&useSSL=false&characterEncoding=UTF-8&autoReconnect=true&failOverReadOnly=false&serverTimezone=Asia/Shanghai&zeroDateTimeBehavior=convertToNull&rewriteBatchedStatements=true
          username: master_username
          password: master_password
          driver-class-name: com.mysql.jdbc.Driver
          druid:
            # 连接池的配置信息: 初始化大小，最小，最大
            initial-size: 5
            min-idle: 5
            maxActive: 20
        slave1:
          url: jdbc:mysql://127.0.0.1:6301/db_name?&useUnicode=true&useSSL=false&characterEncoding=UTF-8&autoReconnect=true&failOverReadOnly=false&serverTimezone=Asia/Shanghai&zeroDateTimeBehavior=convertToNull&rewriteBatchedStatements=true
          username: slave1_username
          password: slave1_password
          driver-class-name: com.mysql.jdbc.Driver
          druid:
            # 连接池的配置信息: 初始化大小，最小，最大
            initial-size: 5
            min-idle: 5
            maxActive: 20
</#if>
<#if hasCache>
  redis:
    host: 127.0.0.1
    port: 6379
    password: 123456
    timeout: 5000
    lettuce:
      pool:
        # 连接池最大连接数（使用负值表示没有限制） 太小可能出现connection.PoolExcelption
        max-active: 100
        max-idle: 20
        min-idle: 8
        # 连接池最大阻塞等待时间（使用负值表示没有限制）
        max-wait: 1000
      shutdown-timeout: 100
</#if>


<#if hasCache>
jetcache:
  statIntervalMinutes: 15
  areaInCacheName: false
  local:
    default:
      type: linkedhashmap
      keyConvertor: fastjson
      limit: 200
      expireAfterAccessInMillis: 60000
  remote:
    default:
      type: redis.lettuce
      uri: redis://${'$'}{spring.redis.host}:${'$'}{spring.redis.port}/?timeout=5s
      keyConvertor: fastjson
      # 序列化器的全局配置。仅remote类型的缓存需要指定，可选java和kryo
      valueEncoder: java
      valueDecoder: java
      # 以毫秒为单位指定超时时间的全局配置
      expireAfterWriteInMillis: 5000
      poolConfig:
        minIdle: 5
        maxIdle: 20
        maxTotal: 50
</#if>

<#if hasDB>
logging:
  level:
    com.baomidou.dynamic: debug
</#if>
