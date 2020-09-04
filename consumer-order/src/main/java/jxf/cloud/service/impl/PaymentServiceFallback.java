package jxf.cloud.service.impl;

import com.netflix.hystrix.HystrixKey;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import jxf.cloud.feign.PaymentHystrixInterface;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * 降级处理类需需要实现feign接口
 */
@Component
public class PaymentServiceFallback implements PaymentHystrixInterface {
    @Override
    public String payment() {
        return "payment fall back";
    }


    @Override
    public String paymentTimeOut() {
        return "paymentTimeOut fall back";
    }

    @Override
    public Map testThreadLocal(Integer userId) {
        return null;
    }

    @Override
    public Map getAnimal(Integer userId) {
        return null;
    }

    @Override
    public List getAnimalInUserIds(String userIds) {
        return null;
    }

}
