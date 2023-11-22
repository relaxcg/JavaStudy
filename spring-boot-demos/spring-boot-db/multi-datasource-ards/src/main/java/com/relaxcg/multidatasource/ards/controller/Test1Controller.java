package com.relaxcg.multidatasource.ards.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.relaxcg.common.web.Result;
import com.relaxcg.multidatasource.ards.base.DS;
import com.relaxcg.multidatasource.ards.entity.Test1DO;
import com.relaxcg.multidatasource.ards.enums.DataSources;
import com.relaxcg.multidatasource.ards.mapper.Ds1Test1Mapper;
import com.relaxcg.multidatasource.ards.mapper.Ds2Test1Mapper;
import com.relaxcg.multidatasource.ards.service.ITest1Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author relaxcg
 * @date 2023/11/21 17:37
 */
@RestController
@RequestMapping("/test1")
@DS
public class Test1Controller {
    @Resource
    private Ds1Test1Mapper ds1Test1Mapper;
    @Resource
    private Ds2Test1Mapper ds2Test1Mapper;
    @Resource
    private ITest1Service test1Service;

    @GetMapping("/ds1")
    public Result<Test1DO> get1() {
        Test1DO test1DO = ds1Test1Mapper.selectOne(Wrappers.query(Test1DO.class).last("limit 1"));
        return Result.ok(test1DO);
    }

    @GetMapping("/ds2")
    @DS(DataSources.DS2)
    public Result<Integer> get2() {
        return Result.ok(ds2Test1Mapper.selectCount(Wrappers.emptyWrapper()).intValue());
    }

    @GetMapping()
    public Result<Void> save() {
        test1Service.save();
        return Result.ok();
    }
}
