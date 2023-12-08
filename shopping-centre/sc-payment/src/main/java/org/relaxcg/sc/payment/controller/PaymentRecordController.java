package org.relaxcg.sc.payment.controller;

import org.relaxcg.sc.common.web.Result;
import org.relaxcg.sc.payment.entity.PaymentRecord;
import org.relaxcg.sc.payment.service.IPaymentRecordService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 商品表 前端控制器
 * </p>
 *
 * @author relaxcg
 * @since 2023-12-08
 */
@RestController
@RequestMapping("/paymentRecord")
public class PaymentRecordController {

    @Resource
    private IPaymentRecordService paymentRecordService;

    @PostMapping("")
    public Result<Long> addPaymentRecord(@RequestBody PaymentRecord req) {
        return Result.ok(paymentRecordService.addPaymentRecord(req));
    }
}
