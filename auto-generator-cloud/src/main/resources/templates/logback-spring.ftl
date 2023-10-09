<?xml version="1.0" encoding="UTF-8"?>
<configuration scan="true" scanPeriod="60 seconds">
    <springProperty scope="context" name="LOG_PATH"         source="logging.file.path"              defaultValue="/data/weblog/webapp"/>
    <springProperty scope="context" name="contextName"      source="spring.application.name"        defaultValue="${artifact}"/>
    <!-- 文件切割大小 -->
    <springProperty scope="context" name="maxFileSize"      source="logging.maxFileSize" defaultValue="100MB"/>
    <!-- 文档保留天数 -->
    <springProperty scope="context" name="maxHistory" source="logging.maxHistory" defaultValue="30"/>
    <!-- 文档保留总大小 -->
    <springProperty scope="context" name="totalSizeCap" source="logging.totalSizeCap" defaultValue="5GB"/>

    <conversionRule conversionWord="clr"    converterClass="org.springframework.boot.logging.logback.ColorConverter"/>
    <conversionRule conversionWord="wex"    converterClass="org.springframework.boot.logging.logback.WhitespaceThrowableProxyConverter"/>
    <conversionRule conversionWord="wEx"    converterClass="org.springframework.boot.logging.logback.ExtendedWhitespaceThrowableProxyConverter"/>
    <!-- 彩色日志格式 -->
    <property name="LOG_PATTERN" value="%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] [%X{x-traceId}] %-5level %logger{50} - %msg%n"/>
    <property name="CONSOLE_LOG_2"  value="${'$'}{CONSOLE_LOG_PATTERN:-%clr(%d{yyyy-MM-dd HH:mm:ss.SSS}){faint} [%X{x-traceId}] %clr(${'$'}{LOG_LEVEL_PATTERN:-%5p}) %clr(${'$'}{PID:- }){magenta} %clr(---){faint} %clr([%15.15t]){faint} %clr(%-40.40logger{39}){cyan} %clr(:){faint} %m%n${'$'}{LOG_EXCEPTION_CONVERSION_WORD:-%wEx}}"/>



    <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <pattern>${'$'}{LOG_PATTERN}</pattern>
            <charset>UTF-8</charset>
        </encoder>
    </appender>

    <appender name="FILE_ERROR" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <file>${'$'}{LOG_PATH}/${'$'}{contextName}/log_error.log</file>
        <rollingPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedRollingPolicy">
            <fileNamePattern>${'$'}{LOG_PATH}/${'$'}{contextName}/error/log-error-%d{yyyy-MM-dd}.%i.log</fileNamePattern>
            <maxFileSize>${'$'}{maxFileSize}</maxFileSize>
            <maxHistory>${'$'}{maxHistory}</maxHistory>
            <totalSizeCap>${'$'}{totalSizeCap}</totalSizeCap>
        </rollingPolicy>
        <append>true</append>
        <encoder class="ch.qos.logback.classic.encoder.PatternLayoutEncoder">
            <pattern>${'$'}{LOG_PATTERN}</pattern>
            <charset>utf-8</charset>
        </encoder>
        <filter class="ch.qos.logback.classic.filter.LevelFilter">
            <level>error</level>
            <onMatch>ACCEPT</onMatch>
            <onMismatch>DENY</onMismatch>
        </filter>
    </appender>


    <appender name="FILE_WARN" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <file>${'$'}{LOG_PATH}/${'$'}{contextName}/log_warn.log</file>
        <rollingPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedRollingPolicy">
            <fileNamePattern>${'$'}{LOG_PATH}/${'$'}{contextName}/warn/log-warn-%d{yyyy-MM-dd}.%i.log</fileNamePattern>
            <maxFileSize>${'$'}{maxFileSize}</maxFileSize>
            <maxHistory>${'$'}{maxHistory}</maxHistory>
            <totalSizeCap>${'$'}{totalSizeCap}</totalSizeCap>
        </rollingPolicy>
        <append>true</append>
        <encoder class="ch.qos.logback.classic.encoder.PatternLayoutEncoder">
            <pattern>${'$'}{LOG_PATTERN}</pattern>
            <charset>utf-8</charset>
        </encoder>
        <filter class="ch.qos.logback.classic.filter.LevelFilter">
            <level>warn</level>
            <onMatch>ACCEPT</onMatch>
            <onMismatch>DENY</onMismatch>
        </filter>
    </appender>


    <appender name="FILE_INFO" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <file>${'$'}{LOG_PATH}/${'$'}{contextName}/log_info.log</file>
        <rollingPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedRollingPolicy">
            <fileNamePattern>${'$'}{LOG_PATH}/${'$'}{contextName}/info/log-info-%d{yyyy-MM-dd}.%i.log</fileNamePattern>
            <maxFileSize>${'$'}{maxFileSize}</maxFileSize>
            <maxHistory>${'$'}{maxHistory}</maxHistory>
            <totalSizeCap>${'$'}{totalSizeCap}</totalSizeCap>
        </rollingPolicy>
        <append>true</append>
        <encoder class="ch.qos.logback.classic.encoder.PatternLayoutEncoder">
            <pattern>${'$'}{LOG_PATTERN}</pattern>
            <charset>utf-8</charset>
        </encoder>
        <filter class="ch.qos.logback.classic.filter.LevelFilter">
            <level>info</level>
            <onMatch>ACCEPT</onMatch>
            <onMismatch>DENY</onMismatch>
        </filter>
    </appender>


    <appender name="FILE" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <file>${'$'}{LOG_PATH}/${'$'}{contextName}/out.log</file>
        <rollingPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedRollingPolicy">
            <fileNamePattern>${'$'}{LOG_PATH}/${'$'}{contextName}/log-%d{yyyy-MM-dd}.%i.log</fileNamePattern>
            <maxFileSize>${'$'}{maxFileSize}</maxFileSize>
            <maxHistory>${'$'}{maxHistory}</maxHistory>
            <totalSizeCap>${'$'}{totalSizeCap}</totalSizeCap>
        </rollingPolicy>
        <append>true</append>
        <encoder class="ch.qos.logback.classic.encoder.PatternLayoutEncoder">
            <pattern>${'$'}{LOG_PATTERN}</pattern>
            <charset>utf-8</charset>
        </encoder>
    </appender>

    <appender name="TEMP_FILE" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <file>${'$'}{LOG_PATH}/${'$'}{contextName}/temp.log</file>
        <rollingPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedRollingPolicy">
            <fileNamePattern>${'$'}{LOG_PATH}/${'$'}{contextName}/temp-%d{yyyy-MM-dd}.%i.log</fileNamePattern>
            <maxFileSize>100MB</maxFileSize>
            <maxHistory>3</maxHistory>
            <totalSizeCap>500MB</totalSizeCap>
        </rollingPolicy>
        <append>true</append>
        <encoder class="ch.qos.logback.classic.encoder.PatternLayoutEncoder">
            <pattern>${'$'}{LOG_PATTERN}</pattern>
            <charset>utf-8</charset>
        </encoder>
    </appender>

    <appender name="CONSOLE" class="ch.qos.logback.core.ConsoleAppender">
        <withJansi>true</withJansi>
        <encoder>
            <pattern>${'$'}{CONSOLE_LOG_2}</pattern>
        </encoder>
    </appender>

    <logger name="io" level="WARN"/>
    <logger name="com" level="WARN"/>
    <logger name="de" level="WARN"/>
    <logger name="org" level="WARN"/>
    <logger name="net" level="WARN"/>
    <logger name="sun" level="WARN"/>
    <logger name="javax" level="WARN"/>
    <logger name="jdbc" level="ERROR"/>
    <logger name="liquibase" level="WARN"/>
    <logger name="reactor" level="WARN"/>
    <logger name="springfox" level="WARN"/>

    <logger name="com.minlia" level="ERROR"/>
    <logger name="ch.qos.logback" level="ERROR"/>
    <logger name="org.springframework" level="INFO"/>
    <logger name="org.apache.catalina.startup.DigesterFactory" level="OFF"/>
    <logger name="jdbc.resultsettable" level="INFO"/>
    <logger name="jdbc.sqlonly" level="INFO"/>
    <logger name="io.lettuce.core.protocol.ConnectionWatchdog" level="INFO"/>


    <!--生产环境-->
    <springProfile name="prod">
        <root level="INFO">
            <appender-ref ref="FILE_INFO"/>
            <appender-ref ref="FILE_WARN"/>
            <appender-ref ref="FILE_ERROR"/>
            <appender-ref ref="TEMP_FILE"/>
        </root>
    </springProfile>


    <!--预发布环境 -->
    <springProfile name="uat">
        <root level="INFO">
            <appender-ref ref="FILE_INFO"/>
            <appender-ref ref="FILE_WARN"/>
            <appender-ref ref="FILE_ERROR"/>
            <appender-ref ref="TEMP_FILE"/>
        </root>
    </springProfile>


    <!--测试环境-->
    <springProfile name="test">
        <root level="INFO">
            <appender-ref ref="FILE"/>
            <appender-ref ref="TEMP_FILE"/>
        </root>
    </springProfile>


    <!--开发环境-->
    <springProfile name="dev">
        <root level="DEBUG">
            <appender-ref ref="CONSOLE"/>
            <appender-ref ref="TEMP_FILE"/>
        </root>
    </springProfile>
</configuration>