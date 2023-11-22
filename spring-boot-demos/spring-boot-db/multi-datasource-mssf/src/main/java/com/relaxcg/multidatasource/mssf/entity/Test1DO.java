package com.relaxcg.multidatasource.mssf.entity;

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
    private LocalDateTime createTime;
    private boolean deleted;
    private String name;
    private Short status;
}
