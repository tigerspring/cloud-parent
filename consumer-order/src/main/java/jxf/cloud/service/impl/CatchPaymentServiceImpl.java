package jxf.cloud.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheKey;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheRemove;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import jxf.cloud.feign.PaymentHystrixInterface;
import jxf.cloud.service.CatchPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CatchPaymentServiceImpl implements CatchPaymentService {

    @Autowired
    PaymentHystrixInterface paymentHystrixInterface;

    @CacheResult
    @HystrixCommand(groupKey = "catchGroup")
    @Override
    public Map getPerson(Integer id) {
        Map map = paymentHystrixInterface.getAnimal(id);
        System.out.println(map);
        return map;
    }


    @CacheResult
    @HystrixCommand(commandKey = "getUser")
    public Map getPersonToCommandKey(@CacheKey Integer id){
        Map map = paymentHystrixInterface.getAnimal(id);
        System.out.println("getPersonToCommandKey: " + map);
        return map;
    }

    @CacheRemove(commandKey = "getUser")
    @HystrixCommand
    public String updatePerson(@CacheKey Integer id){
        return "删除缓存";
    }
}
