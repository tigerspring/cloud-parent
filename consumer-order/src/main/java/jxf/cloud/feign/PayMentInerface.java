package jxf.cloud.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Component
@FeignClient(value="PAYMENT-SERVER")
public interface PayMentInerface {
    @GetMapping(value = "/pay/pament")
    String payment();
}
