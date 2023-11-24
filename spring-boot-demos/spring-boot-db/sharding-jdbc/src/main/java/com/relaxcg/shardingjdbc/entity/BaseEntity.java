package com.relaxcg.shardingjdbc.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * @author relaxcg
 * @date 2023/11/24 15:55
 */
@Data
public class BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    /**
     * 创建时间
     **/
    @TableField(value = "CREATE_TIME")
    private Date createTime;
    /**
     * 更新时间
     **/
    @TableField(value = "UPDATE_TIME")
    private Date updateTime;

    @TableField("DELETED")
    @TableLogic
    private Boolean deleted;
}
