create table if not exists sc_order
(
    id           bigint               not null comment '订单id',
    create_time  timestamp(3)         not null comment '创建时间',
    create_user  bigint               not null comment '创建人',
    modify_time  timestamp(3)         null comment '修改时间',
    modify_user  bigint               null comment '修改人',
    order_amount decimal(10, 2)       not null comment '订单金额',
    order_status tinyint(4) default 1 not null comment '订单状态',
    `comment`    varchar(256)         null comment '描述',
    primary key (id)
) engine = InnoDB
  character set = utf8
  collate = utf8mb3_general_ci comment = '订单表';

create table if not exists sc_order_detail
(
    id          bigint         not null comment '订单明细id',
    create_time timestamp(3)   not null comment '创建时间',
    create_user bigint         not null comment '创建人',
    modify_time timestamp(3)   null comment '修改时间',
    modify_user bigint         null comment '修改人',
    order_id    bigint         not null comment '订单id',
    goods_id    bigint         not null comment '商品id',
    goods_name  varchar(128)   not null comment '商品名称',
    goods_num   int(16)        not null comment '商品数量',
    goods_price decimal(10, 2) not null comment '商品价格',
    primary key (id)
) engine = InnoDB
  character set = utf8
  collate = utf8mb3_general_ci comment = '订单明细表';

