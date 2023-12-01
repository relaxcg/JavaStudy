create table if not exists sc_account
(
    id          bigint      not null comment '用户id',
    password    varchar(128) null comment '密码',
    phone       varchar(16) null comment '手机号',
    email       varchar(32) null comment '邮箱',
    status      tinyint(4)  not null default 1 comment '状态',
    create_time timestamp   not null comment '创建时间',
    create_user bigint      not null comment '创建人',
    modify_time timestamp   null comment '修改时间',
    modify_user bigint      null comment '修改人',
    primary key (id)
) engine = InnoDB
  character set = utf8
  collate = utf8mb3_general_ci comment = '账户表';

create table if not exists sc_user
(
    id          bigint       not null comment '用户id',
    username    varchar(32)  null comment '用户名',
    nick_name   varchar(32)  null comment '昵称',
    address     varchar(128) null comment '地址',
    create_time timestamp    not null comment '创建时间',
    create_user bigint       not null comment '创建人',
    modify_time timestamp    null comment '修改时间',
    modify_user bigint       null comment '修改人',
    primary key (id)
) engine = InnoDB
  character set = utf8
  collate = utf8mb3_general_ci comment = '用户表';

