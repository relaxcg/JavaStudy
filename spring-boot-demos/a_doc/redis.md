### Redis

一款基于内存的高速缓存数据库（key-value）。

### 特性

+ 读写性能优异
+ 数据类型丰富
    + String、List、Hash、Set、Zset、Geo、HyperLogLogs等
+ 原子性
    + 所有的操作都是原子性的（执行命令是单线程）、事务
+ 发布/订阅、key过期
+ 持久化
    + RDB、AOF
+ 分布式
    + Cluster

### 使用场景

+ 热点数据缓存
+ 限时业务（expire）
+ 计数器
+ 分布式锁（Redisson）
+ 延时操作（key过期提醒）
+ 排行榜（Zset）
+ 点赞、好友等相互关系（交集、并集、差集）
+ 简单队列（List push/pop）

### 基础数据类型

String、List、Hash、Set、Zset

|   类型   | 值              | 
|:------:|:---------------|
| String | 字符串、整数、浮点数     |
|  List  | 链表，每个节点包含一个字符串 |  
|  Set   | 包含字符串的无序集合     |
|  Hash  | 包含键值对的无序散列表    |
|  Zset  | 有序的Set         |

#### String

> String 类型是二进制安全的，即可以包含任何数据。如数字、字符串、图片、序列化对象等。

常用命令

|   命令   | 简述         |      使用       |
|:------:|:-----------|:-------------:|
|  GET   | 获取给定key的值  |    GET key    |
|  SET   | 设置给定key的值  | SET key value |
|  DEL   | 删除给定key的值  |    DEL key    |
|  INCR  | 将key的值加1   |   INCR key    |
|  DECR  | 将key的值减1   |   DECR key    |
| INCRBY | 将key的值加上整数 | INCRBY key 2  |
| DECRBY | 将key的值减去整数 | DECRBY key 2  |

#### List

> 双端链表。

常用命令：

|   命令   | 简述                                            |           使用           |
|:------:|:----------------------------------------------|:----------------------:|
| RPUSH  | 将给定值推入到列表右端                                   |    RPUSH key value     |
| LPUSH  | 将给定值推入到列表左端                                   |    LPUSH key value     |
|  RPOP  | 从列表的右端弹出一个值，并返回被弹出的值                          |        RPOP key        |
|  LPOP  | 从列表的左端弹出一个值，并返回被弹出的值                          |        LPOP key        |
| LRANGE | 获取列表在给定范围上的所有值                                |    LRANGE key 0 -1     |
| LINDEX | 通过索引获取列表中的元素。也可使用负数下标                         |   LINDEX  key index    |
|  LTRM  | 保留指定key的值的范围内的数据                              | LTRM key index1 index2 |
| BRPOP  | 移出并获取列表的最后一个元素， 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止 |   BRPOP key timeout    |

使用技巧：

+ lpush+lpop=Stack
+ lpush+rpop=Queue
+ lpush+ltrm=Capped Collection(有限集合)
+ lpush+brpop=Message Queue

#### Set

> 哈希表实现。

常用命令：

|     命令     | 简述                         |          使用          |
|:----------:|:---------------------------|:--------------------:|
|    SADD    | 向集合添加一个或多个成员               |    SADD key value    |
|   SCARD    | 获取集合的成员数                   |      SCARD key       |
|  SMEMBERS  | 返回集合中的所有成员                 |     SMEMBERS key     |
| SISMEMBER	 | 判断 member 元素是否是集合 key 的成员	 | SISMEMBER key member |

#### Hash

常用命令：

|   命令    | 简述                   |              使用               |
|:-------:|:---------------------|:-----------------------------:|
|  HSET   | 添加键值对                | HSET hash-key sub-key1 value1 |
|  HGET   | 获取指定散列键的值            |      HGET hash-key key1       |
| HGETALL | 获取散列中包含的所有键值对        |       HGETALL hash-key        |
|  HDEL   | 如果给定键存在于散列中，那么就移除这个键 |    HDEL hash-key sub-key1     |

#### Zset

