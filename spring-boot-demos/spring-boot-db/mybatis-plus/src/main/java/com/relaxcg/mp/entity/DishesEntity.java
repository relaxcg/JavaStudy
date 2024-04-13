package com.relaxcg.mp.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.relaxcg.mp.config.JsonbTypeHandler;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author relaxcg
 * @date 2024/4/12 9:28
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "tb_test")
public class DishesEntity extends Model<DishesEntity> {

    private Long id;

    @TableField(typeHandler = JsonbTypeHandler.class)
    private Object info;
}
