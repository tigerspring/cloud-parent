package jxf.cloud.moni.service;

import com.netflix.hystrix.HystrixCommand;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.concurrent.TimeUnit;

public interface InvokService {
    String timeOutTest( TimeUnit timeUnit,  Long outTime) throws InterruptedException ;

    String errorBusinesss();

    String normalBusiness();

    void setCommand(HystrixCommand command);
}
