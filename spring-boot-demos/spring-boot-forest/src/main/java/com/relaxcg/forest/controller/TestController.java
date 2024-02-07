package com.relaxcg.forest.controller;

import com.relaxcg.common.web.Result;
import com.relaxcg.forest.controller.req.TestReq;
import com.relaxcg.forest.ext.WebTestApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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

    @GetMapping("/testGzip")
    public Result<Void> testGzip() {
        // Map<String, Object> data = webTestApi.testGzip("gzip").toResult();
        // log.info("data:{}", JSON.toJSONString(data));
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 300; i++) {
            sb.append(i);
        }
        String res = webTestApi.testGzip1(sb.toString());
        log.info("res:{}", res);
        return Result.ok();
    }

    @PostMapping("/image")
    public Result<String> sendImage(@RequestParam("file") MultipartFile file) throws IOException {
        log.info("name:{}", file.getName());
        log.info("size:{}", file.getSize());
        return webTestApi.postBytes(file.getBytes(), file.getName());
        // return Result.ok();
        // return webTestApi.sendImage(file);
    }


}
