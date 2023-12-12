server:
  port: 9206
#  servlet:
#    context-path: /gateway

spring:
  application:
    name: sc-gateway
#  profiles:
#    active: dev
  cloud:
    nacos:
      discovery:
        server-addr: 127.0.0.1:8848

      config:
        server-addr: 127.0.0.1:8848
        file-extension: yaml
    gateway:
      discovery:
        locator:
          enabled: true
          lower-case-service-id: true
      ro
