package org.relaxcg.sc.common.web;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author relaxcg
 * @date 2023/12/8 11:28
 */
@Data
public class PageQueryParam implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    private Integer pageSize = 10;
    private Integer currentPage = 1;
}
