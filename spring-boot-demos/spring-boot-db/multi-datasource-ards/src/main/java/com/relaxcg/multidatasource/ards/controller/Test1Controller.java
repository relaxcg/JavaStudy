package com.relaxcg.multidatasource.ards.controller;

import com.alibaba.druid.support.json.JSONUtils;
import com.relaxcg.common.web.Result;
import com.relaxcg.multidatasource.ards.entity.Test1DO;
import com.relaxcg.multidatasource.ards.service.ITest1Service;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.sql.ResultSet;

/**
 * @author relaxcg
 * @date 2023/11/21 17:37
 */
@RestController
@RequestMapping("/test1")
public class Test1Controller {
    @Resource
    private ITest1Service test1Service;

    @GetMapping("/ds1")
    public Result<Test1DO> get1() {
        return Result.ok();
    }

    @GetMapping("/ds2")
    public Result<Integer> get2() {
        return Result.ok();
    }

    @Resource
    private JdbcTemplate jdbcTemplate1;
    @Resource
    private JdbcTemplate jdbcTemplate2;
    @GetMapping()
    public Result<Void> test() {
        Object q1 = jdbcTemplate1.queryForMap("select * from tb_test1 limit 1");
        // Object q2 = jdbcTemplate2.queryForMap("select * from tb_test1 limit 1");
        System.out.println("q1:"+ JSONUtils.toJSONString(q1));
        // System.out.println("q2:"+ JSONUtils.toJSONString(q2));
        return Result.ok();
    }
}
