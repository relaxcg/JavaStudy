package com.relaxcg.multidatasource.ards.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.relaxcg.multidatasource.ards.base.DS;
import com.relaxcg.multidatasource.ards.entity.Test1DO;
import com.relaxcg.multidatasource.ards.enums.DataSources;
import com.relaxcg.multidatasource.ards.mapper.Test1Mapper;
import com.relaxcg.multidatasource.ards.service.ITest1Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author relaxcg
 * @date 2023/11/22 15:36
 */
@RequiredArgsConstructor
@Service
@DS
public class Test1ServiceImpl implements ITest1Service {
    private final Test1Mapper test1Mapper;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void save() {
        Test1DO test1DO = new Test1DO();
        test1DO.setName("test ds ards");
        test1DO.setStatus((short) 1);
    }

    @Override
    public Test1DO getFromDs1() {
        return test1Mapper.selectOne(Wrappers.emptyWrapper());
    }

    @Override
    @DS(DataSources.DS2)
    public Test1DO getFromDs2() {
        return test1Mapper.selectOne(Wrappers.emptyWrapper());
    }
}
