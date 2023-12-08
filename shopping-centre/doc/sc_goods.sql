create table if not exists sc_goods
(
    id          bigint         not null comment '商品id',
    create_time timestamp(3)   not null comment '创建时间',
    create_user bigint         not null comment '创建人',
    modify_time timestamp(3)   null comment '修改时间',
    modify_user bigint         null comment '修改人',
    goods_name  varchar(64)    not null comment '商品名称',
    price       decimal(10, 2) not null comment '价格',
    description varchar(255)   null comment '描述',
    primary key (id)
) engine = InnoDB
  character set = utf8
  collate = utf8mb3_general_ci comment = '商品表';



