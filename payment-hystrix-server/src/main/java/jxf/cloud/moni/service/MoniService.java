package jxf.cloud.moni.service;

import java.util.concurrent.TimeUnit;

public interface MoniService {


    String timeOutBusiness(TimeUnit timeUnit,Long t) throws InterruptedException;

    String errorBusinesss();


    String normalBusiness();



}
