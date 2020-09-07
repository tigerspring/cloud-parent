package jxf.cloud.consum;

import jxf.cloud.feign.PaymentHystrixInterface;
import jxf.cloud.hystrix.command.HystrixCatchCommand;
import jxf.cloud.service.CatchPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class CatchHystrixController {

    @Autowired
    PaymentHystrixInterface paymentHystrixInterface;

    @Autowired
    private CatchPaymentService catchPaymentService;

    @GetMapping("/catch/test/{id}")
    public void test(@PathVariable Integer id){
        HystrixCatchCommand command = new HystrixCatchCommand(paymentHystrixInterface,id);
        Map map = (Map)command.execute();

        System.out.println(Thread.currentThread().getName()+"   "+map+"  "+command.isResponseFromCache());

        HystrixCatchCommand command1 = new HystrixCatchCommand(paymentHystrixInterface,id);
        Map map1 = (Map)command1.execute();
        System.out.println(Thread.currentThread().getName()+"   "+map1+"  "+command1.isResponseFromCache());
    }

    @GetMapping("/anotation/test/{id}")
    public Map testAnotation(@PathVariable Integer id){
        Map map = catchPaymentService.getPerson(id);
        Map map1 = catchPaymentService.getPerson(id);
        return map1;
    }

    @GetMapping("/update/test/{id}")
    public Map testUpdate(@PathVariable Integer id){
        Map map = catchPaymentService.getPersonToCommandKey(id);
        System.out.println(map);
        map = catchPaymentService.getPersonToCommandKey(3);
        System.out.println(map);
        map = catchPaymentService.getPersonToCommandKey(3);
        System.out.println(map);
        String ss = catchPaymentService.updatePerson(3);
        System.out.println(ss);
        map = catchPaymentService.getPersonToCommandKey(3);
        System.out.println(map);
        return map;
    }


}
