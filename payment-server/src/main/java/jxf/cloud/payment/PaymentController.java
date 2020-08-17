package jxf.cloud.payment;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pay")
public class PaymentController {

    @Value("${spring.profiles.active}")
    String active;

    @GetMapping("/pament")
    public Object payment(){
        return "payment-server"+active;
    }
}
