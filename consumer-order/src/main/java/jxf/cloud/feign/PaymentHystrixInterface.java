package jxf.cloud.feign;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import jxf.cloud.service.impl.PaymentServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 指定降级处理类
 */
@Component
@FeignClient(value = "PAYMENT-HYSTRIX-SERVER",fallback = PaymentServiceFallback.class)
//@FeignClient(value = "PAYMENT-HYSTRIX-SERVER")
public interface PaymentHystrixInterface {
    @GetMapping(value = "/hystrix/payment/payment")
    String payment();


    @GetMapping(value = "/hystrix/payment/paymentTimeOut")
    String paymentTimeOut();
}
