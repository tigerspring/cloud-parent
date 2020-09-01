package jxf.cloud.consum;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import jxf.cloud.balance.RestTemplateConfig;
import jxf.cloud.feign.PaymentHystrixInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

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

    //HystrixCommand使用时关闭和feign同时存在时 hystrix.enabled: false,HystrixCommand才生效
    @HystrixCommand(commandKey = "paymentTimeOut",fallbackMethod = "fallback")
    @GetMapping("hystrix/paymentTimeOut")
    public String paymentTimeOut(){
        String str = paymentHystrixInterface.paymentTimeOut();
        return str;
    }

    public String fallback(){
        return "服务器忙";
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
