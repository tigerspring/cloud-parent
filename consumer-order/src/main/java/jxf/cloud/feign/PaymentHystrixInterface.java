package jxf.cloud.feign;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import jxf.cloud.service.impl.PaymentServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 指定降级处理类
 */
@Component
@FeignClient(value = "PAYMENT-HYSTRIX-SERVER",fallback = PaymentServiceFallback.class)
//@FeignClient(value = "PAYMENT-HYSTRIX-SERVER")
public interface PaymentHystrixInterface {
    @GetMapping(value = "/hystrix/payment/payment")
    String payment();


    @GetMapping(value = "/hystrix/payment/paymentTimeOut")
    String paymentTimeOut();


    @RequestMapping("/threadLocal/test")
    Map testThreadLocal(@RequestParam Integer userId);


    @GetMapping("/getAnimal/{userId}")
    Map getAnimal(@PathVariable Integer userId) ;


    @GetMapping("/getAnimalInUserIds")
    List getAnimalInUserIds(@RequestParam String userIds);

}
