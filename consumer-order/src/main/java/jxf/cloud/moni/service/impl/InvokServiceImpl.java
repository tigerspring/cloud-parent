package jxf.cloud.moni.service.impl;

import com.netflix.hystrix.HystrixCommand;
import jxf.cloud.moni.hystrix.MyCommand2;
import jxf.cloud.moni.service.InvokService;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;


@Service
public class InvokServiceImpl implements InvokService {


    private MyCommand2 command2;


    @Override
    public String timeOutTest(TimeUnit timeUnit, Long outTime) throws InterruptedException {

        return (String)command2.execute();
    }

    @Override
    public String errorBusinesss() {
        return null;
    }

    @Override
    public String normalBusiness() {
        return null;
    }

    @Override
    public void setCommand(HystrixCommand command) {
        this.command2 = (MyCommand2) command;
    }
}
