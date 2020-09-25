package jxf.cloud.moni.controller;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import jxf.cloud.moni.feign.PaymentFeignInerface;
import jxf.cloud.moni.hystrix.MyCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RestController
@RequestMapping("/comsumer/moni")
public class ConsumerMoniController{
    @Autowired
    private PaymentFeignInerface paymentFeignInerface;

    /**
     * 同步执行
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @GetMapping("/timeOutSyncTest")
    public String timeOutSyncTest() throws ExecutionException, InterruptedException {
        String threadName = Thread.currentThread().getName();
        System.out.println("timeOut thread name:"+threadName);
        MyCommand myCommand =new MyCommand(
                HystrixCommand
                        .Setter
                        .withGroupKey(
                                HystrixCommandGroupKey
                                        .Factory
                                        .asKey("jxfGroup")
                        ).andCommandPropertiesDefaults(
                                HystrixCommandProperties.defaultSetter().withExecutionTimeoutInMilliseconds(1153000)
                        ),
                paymentFeignInerface, threadName);
        long start = System.currentTimeMillis();
        String s  = (String)myCommand.execute();
        long end = System.currentTimeMillis();
        System.out.println( (end - start)+ "ms" );
        return s;
    }

    /**
     * 异步执行
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @GetMapping("/timeOutTest")
    public String timeOutTest() throws ExecutionException, InterruptedException {
        String threadName = Thread.currentThread().getName();
        System.out.println("timeOut thread name:"+threadName);
        MyCommand myCommand =new MyCommand(
                HystrixCommand
                        .Setter
                        .withGroupKey(
                                HystrixCommandGroupKey
                                        .Factory
                                        .asKey("jxfGroup")
                        ).andCommandPropertiesDefaults(
                        HystrixCommandProperties.defaultSetter().withExecutionTimeoutInMilliseconds(10000)
                ),
                paymentFeignInerface, threadName);
        long start = System.currentTimeMillis();
        Future<String> future  = (Future)myCommand.queue();
        long end = System.currentTimeMillis();
        System.out.println( (end - start)+ "ms" );
        return future.get();
    }
}
