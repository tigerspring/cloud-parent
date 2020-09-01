package jxf.cloud.controller;

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
    public String paymentTimeOut(){
        return paymentService.paymentTimeOut();
    }


    @GetMapping("/payment")
    public String payment(){
        return "payment 成功";
    }


}
