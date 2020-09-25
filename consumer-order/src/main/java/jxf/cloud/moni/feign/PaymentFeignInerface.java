package jxf.cloud.moni.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.concurrent.TimeUnit;

@FeignClient("payment-hystrix-server")
public interface PaymentFeignInerface {
    @GetMapping("/moni/timeOutTest")
    String timeOutTest(@RequestParam TimeUnit timeUnit, @RequestParam Long outTime) throws InterruptedException ;

    @GetMapping("/moni/errorBusinesss")
    String errorBusinesss();

    @GetMapping("/moni/normalBusiness")
    String normalBusiness();

}
