package com.relaxcg.forest.controller;

import com.relaxcg.common.web.Result;
import com.relaxcg.forest.controller.req.TestReq;
import com.relaxcg.forest.ext.WebTestApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @author relaxcg
 * @date 2023/11/17 11:36
 */
@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private WebTestApi webTestApi;


    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<TestReq> create(@RequestBody TestReq req) {
        return webTestApi.create(req);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<Integer> delete(@PathVariable("id") Integer id) {
        return webTestApi.delete(id);
    }

    @PatchMapping(value = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<TestReq> update(@PathVariable("id") Integer id, @RequestBody TestReq req) {
        return webTestApi.update(id, req);
    }

    @GetMapping(value = "/getById", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<TestReq> getById(@RequestParam("id") Integer id) {
        return webTestApi.getById(id);
    }

    @GetMapping(value = "/query", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<TestReq> query(TestReq queryReq) {
        return webTestApi.query(queryReq);
    }

}
