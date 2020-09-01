package jxf.cloud.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {


    @Value("${thread.sleep.time}")
    private Long sleepTime;

    public String paymentTimeOut() {
        try {
            TimeUnit.MILLISECONDS.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(new Date()+"     执行超时方法");
        return "paymentTimeOut_3s 成功";
    }
}
