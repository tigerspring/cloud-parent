package jxf.cloud.feign;

import jxf.cloud.service.impl.PaymentServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Component
@FeignClient(value = "PAYMENT-HYSTRIX-SERVER",fallback = PaymentServiceFallback.class)
public interface PaymentHystrixInterface {
    @GetMapping(value = "/hystrix/payment/payment")
    String payment();

    @GetMapping(value = "/hystrix/payment/paymentTimeOut")
    String paymentTimeOut();
}
