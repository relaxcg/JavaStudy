create table if not exists sc_payment_record
(
    id          bigint         not null comment '支付id',
    create_time timestamp(3)   not null comment '创建时间',
    create_user bigint         not null comment '创建人',
    modify_time timestamp(3)   null comment '修改时间',
    modify_user bigint         null comment '修改人',
    order_id    bigint         not null comment '订单id',
    user_id     bigint         not null comment '用户id',
    amount      decimal(10, 2) not null comment '支付金额',
    pay_type    tinyint(4)     not null comment '支付方式',
    status      tinyint(4)     not null default 1 comment '支付状态',
    primary key (id)
) engine = InnoDB
  character set = utf8
  collate = utf8mb3_general_ci comment = '支付记录表';



