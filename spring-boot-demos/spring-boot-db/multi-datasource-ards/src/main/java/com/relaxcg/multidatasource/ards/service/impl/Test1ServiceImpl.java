package com.relaxcg.multidatasource.ards.service.impl;

import com.relaxcg.multidatasource.ards.entity.Test1DO;
import com.relaxcg.multidatasource.ards.mapper.Ds1Test1Mapper;
import com.relaxcg.multidatasource.ards.mapper.Ds2Test1Mapper;
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
public class Test1ServiceImpl implements ITest1Service {
    private final Ds1Test1Mapper ds1Test1Mapper;
    private final Ds2Test1Mapper ds2Test1Mapper;

    @Override
    // @Transactional(rollbackFor = RuntimeException.class)
    public void save() {
        Test1DO test1DO = new Test1DO();
        test1DO.setName("test ds ards");
        test1DO.setStatus((short) 1);
        ds1Test1Mapper.insert(test1DO);
        ds2Test1Mapper.insert(test1DO);
    }
}
