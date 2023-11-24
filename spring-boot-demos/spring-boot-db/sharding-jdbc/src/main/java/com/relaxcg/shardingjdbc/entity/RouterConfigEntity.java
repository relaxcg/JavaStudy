package com.relaxcg.shardingjdbc.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serial;

/**
 * @author relaxcg
 * @date 2023/11/24 15:57
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
@TableName(value = "router_config")
public class RouterConfigEntity extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 设备 ID
     */
    private Long deviceId;
    /**
     * wifi 名称
     */
    private String wifiName;
    /**
     * wifi 密码
     */
    private String wifiPassword;
    /**
     * 加密类型,0-不加密,1-WPA-PSK,2-WPA2-PSK,3-WPA/WPA2-PSK
     */
    private Integer encryptType;
    /**
     * 管理员密码
     */
    private String adminPassword;
    /**
     * wifi开关,0-关闭,1-开启
     */
    private Integer wifiSwitch;
    /**
     * 是否隐藏 wifi,0-不隐藏,1-隐藏
     */
    private Integer hideSwitch;


}
