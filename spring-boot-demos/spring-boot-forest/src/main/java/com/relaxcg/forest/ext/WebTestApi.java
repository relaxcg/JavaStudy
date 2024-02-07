package com.relaxcg.forest.ext;

import com.dtflys.forest.annotation.*;
import com.relaxcg.common.web.Result;
import com.relaxcg.forest.controller.req.TestReq;
import com.relaxcg.forest.interceptor.SimpleInterceptor;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @author relaxcg
 * @date 2023/11/17 16:59
 */
@BaseRequest(baseURL = "http://localhost:8080/test")
public interface WebTestApi {

    @Post(value = "/create", interceptor = SimpleInterceptor.class)
    Result<TestReq> create(@JSONBody TestReq req);

    @Delete("/{id}")
    Result<Integer> delete(@Var("id") Integer id);

    @Patch("/update/{id}")
    Result<TestReq> update(@Var("id") Integer id, @JSONBody TestReq req);

    @Get("/getById")
    Result<TestReq> getById(@Query("id") Integer id);

    @Get("/query")
    Result<TestReq> query(@Query TestReq queryReq);

    @Get(value = "/testGzip", interceptor = {SimpleInterceptor.class})
    Result<Map<String, Object>> testGzip(@Header("Accept-Encoding") String ae);

    @Post(value = "/testGzip1", interceptor = SimpleInterceptor.class)
    String testGzip1(@Body String body);

    @Post(value = "/image")
    Result<String> sendImage(@DataFile("image") MultipartFile file);

    @Post(value = "/postBytes?fileName=${fileName}",
            contentType = "application/octet-stream")
    Result<String> postBytes(@Body byte[] body, @Var("fileName") String fileName);
}
