package jxf.cloud.moni.hystrix;

import com.netflix.hystrix.HystrixCommand;
import jxf.cloud.moni.feign.PaymentFeignInerface;

import java.util.concurrent.TimeUnit;

public class MyCommand extends HystrixCommand {

    private PaymentFeignInerface paymentFeignInerface;

    private String threadName ;

    public MyCommand(Setter setter, PaymentFeignInerface paymentFeignInerface, String threadName) {
        super(setter);
        this.paymentFeignInerface=paymentFeignInerface;
        this.threadName=threadName;

    }

    @Override
    protected Object run() throws Exception {
        String hystrixThreadName = Thread.currentThread().getName();
        System.out.println("request threadName:"+threadName);
        System.out.println("hystrixThreadName:"+hystrixThreadName );

        String result  = null;
        try {
            result = paymentFeignInerface.timeOutTest(TimeUnit.SECONDS,3l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("result: " + result);
        return result;
    }

    @Override
    protected Object getFallback() {
        System.out.println("fall back thread "+ Thread.currentThread().getName());
        return "hystrix fall bak threadname:"+Thread.currentThread().getName();
    }
}
