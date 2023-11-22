package com.relaxcg.multidatasource.ards.enums;

import lombok.Getter;

/**
 * @author relaxcg
 * @date 2023/11/22 14:09
 */
public enum DataSources {

    DS1("ds1"),
    DS2("ds2");
    @Getter
    private final String dsName;

    DataSources(String name) {
        this.dsName = name;
    }
}
