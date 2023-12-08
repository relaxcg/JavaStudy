package org.relaxcg.sc.payment.service;

import org.relaxcg.sc.payment.entity.PaymentRecord;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 商品表 服务类
 * </p>
 *
 * @author relaxcg
 * @since 2023-12-08
 */
public interface IPaymentRecordService extends IService<PaymentRecord> {

    Long addPaymentRecord(PaymentRecord req);
}
