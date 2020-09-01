package jxf.cloud.consum;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import jxf.cloud.feign.PaymentHystrixInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsumFeignHystrixController {
    @Autowired
    private PaymentHystrixInterface paymentHystrixInterface;

    @GetMapping("hystrix/paymentTimeOutFeign")
    public String paymentTimeOut(){
        String str = paymentHystrixInterface.paymentTimeOut();
        return str;
    }
}
