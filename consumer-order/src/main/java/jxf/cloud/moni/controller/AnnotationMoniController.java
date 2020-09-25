package jxf.cloud.moni.controller;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import jxf.cloud.moni.feign.PaymentFeignInerface;
import jxf.cloud.moni.hystrix.MyCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/annotaion/moni")
public class AnnotationMoniController {

    @Autowired
    private PaymentFeignInerface paymentFeignInerface;

    @GetMapping("/timeOutSyncTest")
    @HystrixCommand(groupKey = "jxfGfoup",defaultFallback = "defaultFallBack",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")})
    public String timeOutSyncTest() throws InterruptedException {
        System.out.println("annotaion timeOutSyncTest "+ Thread.currentThread().getName());
        return paymentFeignInerface.timeOutTest(TimeUnit.SECONDS,2l);
    }


    @GetMapping("/timeOutTest")
    @HystrixCommand(groupKey = "jxfGfoup",defaultFallback = "defaultFallBack",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")})
    public String timeOutTest() throws InterruptedException {
        System.out.println("annotaion timeOutSyncTest "+ Thread.currentThread().getName());
        return paymentFeignInerface.timeOutTest(TimeUnit.SECONDS,2l);
    }

    public String defaultFallBack(){
        System.out.println("fall back thread "+ Thread.currentThread().getName());
        return "hystrix fall bak threadname:"+Thread.currentThread().getName();
    }
}
