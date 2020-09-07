package jxf.cloud.controller;


import jxf.cloud.feign.PaymentHystrixInterface;
import jxf.cloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@RestController
public class ThreadLocalTestController {
    @Autowired
    private PaymentService paymentService;

    @GetMapping("/test}")
    public String test() throws InterruptedException, ExecutionException {
        List<Future<Map>> list = new ArrayList();

        for(int i = 0 ; i < 7 ; i++){
            list.add(paymentService.getAnimal(i));
        }
        TimeUnit.MILLISECONDS.sleep(700);
        for(int i = 7 ; i < 10 ; i++){
            list.add(paymentService.getAnimal(i));
        }

        for(int i = 0 ; i < 10 ; i++){
            Future<Map> fmap =list.get(i);
            Map map = fmap.get();
            System.out.println(map);
        }
        return "success";
    }
    @GetMapping("/test1/{userId}")
    public Map test1(@PathVariable Integer userId) throws InterruptedException, ExecutionException {
        System.out.println(Thread.currentThread().getName()+"    +    test1");
        Map map = paymentService.getAnimal(userId).get();
        System.out.println(map);
        return map;
    }


}
