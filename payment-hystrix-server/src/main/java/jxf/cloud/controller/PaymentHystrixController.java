package jxf.cloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import jxf.cloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/hystrix/payment")
public class PaymentHystrixController {

    @Autowired
    PaymentService paymentService;

    @GetMapping("paymentTimeOut")
    public String paymentTimeOut(){
        return paymentService.paymentTimeOut();
    }


    @GetMapping("/payment")
    public String payment(){
        return "payment 成功";
    }


    public String paymentFallback(){
        return "paymentFallback 失败";
    }
}
