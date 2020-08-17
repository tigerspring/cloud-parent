package jxf.cloud.consum;

import jxf.cloud.feign.PaymentHystrixInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsumHystrixController {

    @Autowired
    PaymentHystrixInterface paymentHystrixInterface;

    @GetMapping("hystrix/payment")
    public String payment(){
        return paymentHystrixInterface.payment();
    }

    @GetMapping("hystrix/paymentTimeOut")
    public String paymentTimeOut(){
        return paymentHystrixInterface.paymentTimeOut();
    }
}
