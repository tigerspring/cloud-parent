package jxf.cloud.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import jxf.cloud.feign.PaymentHystrixInterface;
import jxf.cloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    PaymentHystrixInterface paymentHystrixInterface;

    @Autowired
    RestTemplate restTemplate;


    @HystrixCollapser(batchMethod = "getAnimalInUserIds1",scope = com.netflix.hystrix.HystrixCollapser.Scope.GLOBAL,collapserProperties = {
            @HystrixProperty(name="timerDelayInMilliseconds",value="200")
    })
    @Override
    public Future<Map> getAnimal(Integer userId) {
        throw new RuntimeException("This method body should not be executed");
    }

    @HystrixCommand
    @Override
    public List<Map> getAnimalInUserIds(List<Integer> userIdList) {
        System.out.println("finaAll request:---------" + userIdList + "Thread.currentThread().getName():-------" + Thread.currentThread().getName());
        String userids = userIdList.stream().map(e->e.toString()).collect(Collectors.joining(","));
        List  list = paymentHystrixInterface.getAnimalInUserIds(userids);
        System.out.println(list);
        return list;
    }

    @HystrixCommand
    @Override
    public List<Map> getAnimalInUserIds1(List<Integer> userIdList) {
        System.out.println("finaAll1 request:---------" + userIdList + "Thread.currentThread().getName():-------" + Thread.currentThread().getName());
        String userids = userIdList.stream().map(e->e.toString()).collect(Collectors.joining(","));
        List  list = restTemplate.getForObject("http://PAYMENT-HYSTRIX-SERVER/getAnimalInUserIds?userIds={1}",List.class,userids);
        System.out.println(list);
        return list;
    }
}
