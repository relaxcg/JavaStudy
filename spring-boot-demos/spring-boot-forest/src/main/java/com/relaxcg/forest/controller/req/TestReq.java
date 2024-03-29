package com.relaxcg.forest.controller.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author relaxcg
 * @date 2023/11/17 16:38
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestReq implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Integer id = 1;
    private String name = "hello";
}
