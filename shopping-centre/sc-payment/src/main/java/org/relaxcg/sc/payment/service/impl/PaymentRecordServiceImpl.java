package org.relaxcg.sc.payment.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.relaxcg.sc.payment.entity.PaymentRecord;
import org.relaxcg.sc.payment.mapper.PaymentRecordMapper;
import org.relaxcg.sc.payment.service.IPaymentRecordService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品表 服务实现类
 * </p>
 *
 * @author relaxcg
 * @since 2023-12-08
 */
@Service
public class PaymentRecordServiceImpl extends ServiceImpl<PaymentRecordMapper, PaymentRecord> implements IPaymentRecordService {

    @Override
    public Long addPaymentRecord(PaymentRecord req) {
        save(req);
        return req.getId();
    }
}
