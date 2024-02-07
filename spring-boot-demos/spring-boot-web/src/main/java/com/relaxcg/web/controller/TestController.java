package com.relaxcg.web.controller;

import com.alibaba.fastjson2.JSON;
import com.google.common.collect.Maps;
import com.relaxcg.common.web.Result;
import com.relaxcg.web.controller.req.TestReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author relaxcg
 * @date 2023/11/17 11:36
 */
@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping
    public String get() {
        return "SUCCESS";
    }

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<TestReq> create(@RequestBody TestReq req) {
        req.setId(new Random().nextInt(1000));
        return Result.ok(req);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<Integer> delete(@PathVariable("id") Integer id) {
        return Result.ok(id);
    }

    @PatchMapping(value = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<TestReq> update(@PathVariable("id") Integer id, @RequestBody TestReq req) {
        req.setId(id);
        return Result.ok(req);
    }

    @GetMapping(value = "/getById", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<TestReq> getById(@RequestParam("id") Integer id) {
        return Result.ok(new TestReq(id, "name"));
    }

    @GetMapping(value = "/query", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<TestReq> query(TestReq queryReq) {
        log.info("query:{}", JSON.toJSONString(queryReq));
        return Result.ok(queryReq);
    }

    @GetMapping(value = "/testGzip")
    public Result<Map<String, Object>> testGzip() {
        HashMap<String, Object> map = Maps.newHashMap();

        for (int i = 0; i < 10000; i++) {
            map.put(String.valueOf(i), i);
        }
        return Result.ok(map);
    }

    @PostMapping("/testGzip1")
    public String testGzip1(@RequestBody String body) {
        log.info("body:{}", body);
        return body;
    }

    @PostMapping("/image")
    public Result<String> sendImage(@RequestPart("image") MultipartFile image) {
        return Result.ok(image.getOriginalFilename());
    }

    @PostMapping("/postBytes")
    Result<String> postBytes(@RequestBody byte[] body, @RequestParam("fileName") String fileName) {
        return Result.ok(fileName);
    }

}
