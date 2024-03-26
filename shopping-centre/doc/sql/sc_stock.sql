create table if not exists sc_stock
(
    id          bigint       not null comment '库存id',
    create_time timestamp(3) not null comment '创建时间',
    create_user bigint       not null comment '创建人',
    modify_time timestamp(3) null comment '修改时间',
    modify_user bigint       null comment '修改人',
    goods_id    bigint       not null comment '商品id',
    goods_name  varchar(64)  not null comment '商品名称',
    inventory   int(32)      not null comment '库存量',
    status      tinyint(4)   not null default 1 comment '状态',
    primary key (id)
) engine = InnoDB
  character set = utf8
  collate = utf8mb3_general_ci comment = '库存表';



