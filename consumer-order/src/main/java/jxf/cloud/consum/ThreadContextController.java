package jxf.cloud.consum;

import jxf.cloud.hystrix.HystrixRequestThreadLocal;
import jxf.cloud.service.ThreadContextPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.Map;

@RestController
public class ThreadContextController {

    @Autowired
    ThreadContextPaymentService threadContextPaymentService;

    @GetMapping("/threadContext/test/{id}")
    public Map test(@PathVariable Integer id){
        HystrixRequestThreadLocal.treahdLocal.set("id:"+id);

        RequestContextHolder.currentRequestAttributes().setAttribute("id","id:"+id, RequestAttributes.SCOPE_REQUEST);

        System.out.println("Thread name: "+Thread.currentThread().getName());
        System.out.println("Thread local: "+HystrixRequestThreadLocal.treahdLocal.get());
        System.out.println("RequestContextHolder: "+RequestContextHolder.currentRequestAttributes().getAttribute("id",RequestAttributes.SCOPE_REQUEST));

        return threadContextPaymentService.getPerson(id);
    }

}
