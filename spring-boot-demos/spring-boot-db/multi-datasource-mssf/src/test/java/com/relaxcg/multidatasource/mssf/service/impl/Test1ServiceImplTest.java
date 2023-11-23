package com.relaxcg.multidatasource.mssf.service.impl;

import com.relaxcg.multidatasource.mssf.MultiDatasourceMssfApplicationTests;
import com.relaxcg.multidatasource.mssf.service.ITest1Service;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author relaxcg
 * @date 2023/11/23 9:16
 */
class Test1ServiceImplTest extends MultiDatasourceMssfApplicationTests {
    @Autowired
    private ITest1Service test1Service;

    @Test
    void save() {
        test1Service.save();
    }

    @Test
    void save1() {
    }
}