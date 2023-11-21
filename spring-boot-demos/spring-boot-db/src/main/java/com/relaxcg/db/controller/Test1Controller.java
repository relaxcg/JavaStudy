package com.relaxcg.db.controller;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.relaxcg.common.web.Result;
import com.relaxcg.db.repository.entity.Test1DO;
import com.relaxcg.db.repository.mapper.Test1Mapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author relaxcg
 * @date 2023/11/21 11:40
 */
@RestController
@RequestMapping("/test1")
public class Test1Controller {

    @Resource
    private Test1Mapper test1Mapper;

    @GetMapping("/create")
    public Result<Integer> create() {
        Test1DO test1DO = new Test1DO();
        test1DO.setName("name1");
        test1DO.setStatus(Short.valueOf("1"));
        test1Mapper.insert(test1DO);
        return Result.ok(test1DO.getId());
    }

    @GetMapping("/get")
    @DS("slave_1")
    public Result<Test1DO> get() {
        Test1DO test1DO = test1Mapper.selectOne(Wrappers.query(Test1DO.class).last("limit 1"));
        return Result.ok(test1DO);
    }
}
