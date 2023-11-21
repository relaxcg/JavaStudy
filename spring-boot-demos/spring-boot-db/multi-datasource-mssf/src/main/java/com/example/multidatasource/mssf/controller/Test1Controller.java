package com.example.multidatasource.mssf.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.multidatasource.mssf.entity.Test1DO;
import com.example.multidatasource.mssf.mapper.ds1.Ds1Test1Mapper;
import com.example.multidatasource.mssf.mapper.ds2.Ds2Test1Mapper;
import com.relaxcg.common.web.Result;
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
public class Test1Controller {
    @Resource
    private Ds1Test1Mapper ds1Test1Mapper;
    @Resource
    private Ds2Test1Mapper ds2Test1Mapper;

    @GetMapping("/ds1")
    public Result<Test1DO> get1() {
        Test1DO test1DO = ds1Test1Mapper.selectOne(Wrappers.query(Test1DO.class).last("limit 1"));
        return Result.ok(test1DO);
    }

    @GetMapping("/ds2")
    public Result<Test1DO> get2() {
        Test1DO test1DO = ds2Test1Mapper.selectOne(Wrappers.query(Test1DO.class).last("limit 1"));
        return Result.ok(test1DO);
    }
}
