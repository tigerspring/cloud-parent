package jxf.cloud.consum;

import jxf.cloud.balance.RestTemplateConfig;
import jxf.cloud.feign.PaymentHystrixInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsumHystrixController {

    @Autowired
    private PaymentHystrixInterface paymentHystrixInterface;

    @Autowired
    private RestTemplateConfig restTemplateConfig;

    @GetMapping("hystrix/payment")
    public String payment(){
        return paymentHystrixInterface.payment();
    }

    @GetMapping("hystrix/paymentTimeOut")
    public String paymentTimeOut(){
        return paymentHystrixInterface.paymentTimeOut();
    }

    /**
     * 使用resttemplate访问
     * @return
     */
    @GetMapping("hystrix/restTimeOut")
    public String restTimeOut(){
        return restTemplateConfig.getRestTemplate().getForObject("http://PAYMENT-HYSTRIX-SERVER/hystrix/payment/paymentTimeOut",String.class);
    }
}
