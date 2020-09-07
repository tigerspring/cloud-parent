package jxf.cloud.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import jxf.cloud.feign.PaymentHystrixInterface;
import jxf.cloud.hystrix.HystrixRequestThreadLocal;
import jxf.cloud.service.ThreadContextPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.Map;

@Service
public class ThreadContextPaymentServiceImpl implements ThreadContextPaymentService {

    @Autowired
    PaymentHystrixInterface paymentHystrixInterface;


    @HystrixCommand
    @Override
    public Map getPerson(Integer id) {
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("Thread name: "+Thread.currentThread().getName());
        System.out.println("Thread local: "+HystrixRequestThreadLocal.treahdLocal.get());
        System.out.println("RequestContextHolder: "+ RequestContextHolder.currentRequestAttributes().getAttribute("id", RequestAttributes.SCOPE_REQUEST));

        return paymentHystrixInterface.getAnimal(id);
    }
}
