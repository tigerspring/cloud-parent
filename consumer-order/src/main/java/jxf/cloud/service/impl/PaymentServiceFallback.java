package jxf.cloud.service.impl;

import jxf.cloud.feign.PaymentHystrixInterface;
import org.springframework.stereotype.Component;

/**
 * 降级处理类需需要实现feign接口
 */
@Component
public class PaymentServiceFallback implements PaymentHystrixInterface {
    @Override
    public String payment() {
        return "payment fall back";
    }

    @Override
    public String paymentTimeOut() {
        return "paymentTimeOut fall back";
    }
}
