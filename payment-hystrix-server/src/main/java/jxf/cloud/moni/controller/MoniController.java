package jxf.cloud.moni.controller;

import jxf.cloud.moni.service.MoniService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/moni")
public class MoniController {

    @Autowired
    private MoniService moniService;

    @GetMapping("/timeOutTest")
    public String timeOutTest(@RequestParam TimeUnit timeUnit,@RequestParam Long outTime) throws InterruptedException {
        return moniService.timeOutBusiness(timeUnit,outTime);
    }

    @GetMapping("/errorBusinesss")
    public String errorBusinesss() {
        return moniService.errorBusinesss();
    }

    @GetMapping("/normalBusiness")
    public String normalBusiness() {
        return moniService.normalBusiness();
    }


}
