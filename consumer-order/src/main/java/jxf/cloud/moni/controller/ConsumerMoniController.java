package jxf.cloud.moni.controller;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import jxf.cloud.moni.feign.PaymentFeignInerface;
import jxf.cloud.moni.hystrix.MyCommand;
import jxf.cloud.moni.hystrix.MyCommand2;
import jxf.cloud.moni.hystrix.MyNormalCommand;
import jxf.cloud.moni.service.InvokService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/comsumer/moni")
public class ConsumerMoniController{
    @Autowired
    private PaymentFeignInerface paymentFeignInerface;


    @Autowired
    InvokService invokService;

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
     * 同步执行
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @GetMapping("/timeOutSyncTestNormal")
    public String timeOutSyncTestNormal() throws ExecutionException, InterruptedException {
        String threadName = Thread.currentThread().getName();
        System.out.println("timeOut thread name:"+threadName);
        MyNormalCommand myCommand =new MyNormalCommand(
                HystrixCommand
                        .Setter
                        .withGroupKey(
                                HystrixCommandGroupKey
                                        .Factory
                                        .asKey("jxfGroup")
                        ).andCommandPropertiesDefaults(
                        HystrixCommandProperties.defaultSetter().withExecutionTimeoutInMilliseconds(1153000)
                ),
                paymentFeignInerface,"timeOutTest", TimeUnit.SECONDS,3l);
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


    /**
     * 异步执行
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @GetMapping("/timeOutTest2")
    public String timeOutTest2() throws ExecutionException, InterruptedException {
        MyCommand2 myCommand =new MyCommand2(
                HystrixCommand
                        .Setter
                        .withGroupKey(
                                HystrixCommandGroupKey
                                        .Factory
                                        .asKey("jxfGroup")
                        ).andCommandPropertiesDefaults(
                        HystrixCommandProperties.defaultSetter().withExecutionTimeoutInMilliseconds(10000)
                ),
                paymentFeignInerface,"timeOutTest", TimeUnit.SECONDS,3l);
        invokService.setCommand(myCommand);
        return invokService.timeOutTest(TimeUnit.SECONDS,3l);
    }
}
