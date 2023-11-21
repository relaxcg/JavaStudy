package com.relaxcg.forest.ext;

import com.dtflys.forest.annotation.*;
import com.relaxcg.common.web.Result;
import com.relaxcg.forest.controller.req.TestReq;

/**
 * @author relaxcg
 * @date 2023/11/17 16:59
 */
@BaseRequest(baseURL = "http://localhost:9001/web/test")
public interface WebTestApi {

    @Post("/create")
    Result<TestReq> create(@JSONBody TestReq req);

    @Delete("/{id}")
    Result<Integer> delete(@Var("id") Integer id);

    @Patch("/update/{id}")
    Result<TestReq> update(@Var("id") Integer id, @JSONBody TestReq req);

    @Get("/getById")
    Result<TestReq> getById(@Query("id") Integer id);

    @Get("/query")
    Result<TestReq> query(@Query TestReq queryReq);
}
