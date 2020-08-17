package jxf.cloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {


    @Value("${thread.sleep.time}")
    private Long sleepTime;

    /*@HystrixCommand(fallbackMethod = "paymentFallback",commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "3000")
    })*/
    public String paymentTimeOut() {
        try {
            TimeUnit.SECONDS.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("执行超时方法");
        return "paymentTimeOut_3s 成功";
    }
    public String paymentFallback(){
        return "paymentFallback 失败";
    }
}
