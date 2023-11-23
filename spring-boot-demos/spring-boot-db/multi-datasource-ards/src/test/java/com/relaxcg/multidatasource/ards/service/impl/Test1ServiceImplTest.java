package com.relaxcg.multidatasource.ards.service.impl;

import com.relaxcg.multidatasource.ards.MultiDatasourceArdsApplicationTests;
import com.relaxcg.multidatasource.ards.service.ITest1Service;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author relaxcg
 * @date 2023/11/23 9:35
 */
class Test1ServiceImplTest extends MultiDatasourceArdsApplicationTests {
    @Autowired
    private ITest1Service test1Service;

    @Test
    void test() {
        var ds1 = test1Service.getFromDs1();
        var ds2 = test1Service.getFromDs2();
        System.out.println(ds1);
        System.out.println(ds2);
    }
}