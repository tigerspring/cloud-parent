package jxf.cloud.moni.service.impl;

import jxf.cloud.moni.service.MoniService;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class MoniServiceImpl implements MoniService {
    @Override
    public String timeOutBusiness(TimeUnit timeUnit, Long t) throws InterruptedException {
        switch (timeUnit){
            case MICROSECONDS: TimeUnit.MILLISECONDS.sleep(t);
            case SECONDS: TimeUnit.SECONDS.sleep(t);
        }
        return "success";
    }

    @Override
    public String errorBusinesss() {
        String ss = null;
        ss.toLowerCase();
        return "success";
    }

    @Override
    public String normalBusiness() {
        return "success";
    }
}
