package jxf.cloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import jxf.cloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hystrix/payment")
public class PaymentHystrixController {

    @Autowired
    PaymentService paymentService;

    @GetMapping("/paymentTimeOut")
//    @HystrixCommand(fallbackMethod="timeOutError")
    public String paymentTimeOut(){

        return paymentService.paymentTimeOut();
    }

    public String timeOutError(){
        return "执行超时";
    }


    @GetMapping("/payment")
//    @HystrixCommand(fallbackMethod="timeOutError")
    public String payment(){
        return "payment 成功";
    }


}
