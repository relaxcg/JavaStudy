package com.relaxcg.shardingjdbc.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serial;

/**
 * @author relaxcg
 * @date 2023/11/24 15:58
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
@TableName(value = "device_info")
public class DeviceInfoEntity extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 设备类型,1-路由器,2-音响,3-摄像头
     */
    private Integer type;
    /**
     * 设备品牌
     */
    private String brand;
    /**
     * 设备型号
     */
    private String model;
    /**
     * 设备名称
     */
    private String name;


}
