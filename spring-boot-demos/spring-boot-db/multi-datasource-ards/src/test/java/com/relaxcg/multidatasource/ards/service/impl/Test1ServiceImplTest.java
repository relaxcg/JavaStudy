package com.relaxcg.multidatasource.ards.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.relaxcg.multidatasource.ards.MultiDatasourceArdsApplicationTests;
import com.relaxcg.multidatasource.ards.mapper.Ds1Test1Mapper;
import com.relaxcg.multidatasource.ards.mapper.Ds2Test1Mapper;
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
    @Autowired
    private Ds1Test1Mapper ds1Test1Mapper;
    @Autowired
    private Ds2Test1Mapper ds2Test1Mapper;

    private void clearTbs() {
        ds1Test1Mapper.delete(Wrappers.emptyWrapper());
        ds2Test1Mapper.delete(Wrappers.emptyWrapper());
    }

    @Test
    void save() {
        clearTbs();
        test1Service.save();
    }
}