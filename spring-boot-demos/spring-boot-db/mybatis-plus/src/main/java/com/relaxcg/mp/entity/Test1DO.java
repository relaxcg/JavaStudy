package com.relaxcg.mp.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author relaxcg
 * @date 2023/11/21 11:36
 */
@Data
@TableName("tb_test1")
public class Test1DO {

    @TableId
    private Integer id;

    @TableField("create_time")
    private LocalDateTime createTime;

    @TableField("deleted")
    private boolean deleted;

    @TableField("name")
    private String name;

    @TableField("status")
    private Short status;


}
