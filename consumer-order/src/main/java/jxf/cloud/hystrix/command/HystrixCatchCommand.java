package jxf.cloud.hystrix.command;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixRequestCache;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategyDefault;
import jxf.cloud.feign.PaymentHystrixInterface;

import java.util.Map;

public class HystrixCatchCommand extends HystrixCommand {

    private PaymentHystrixInterface paymentHystrixInterface;

    private Integer id ;


    public HystrixCatchCommand(PaymentHystrixInterface paymentHystrixInterface, Integer id) {
        super(HystrixCommandGroupKey.Factory.asKey("catchGroup"));
        this.paymentHystrixInterface = paymentHystrixInterface;
        this.id = id;
    }

    @Override
    protected String getFallback() {
        return "fallback";
    }

    @Override
    protected Map run() throws Exception {
        return paymentHystrixInterface.getAnimal(id);
    }

    @Override
    protected String getCacheKey() {
        return String.valueOf(id);
    }

    public static void cleanCatch(Integer id){
        HystrixRequestCache
                .getInstance(
                        HystrixCommandKey.Factory.asKey("catchGroup"),
                        HystrixConcurrencyStrategyDefault.getInstance()
                )
                .clear(String.valueOf(id)
        );
    }
}
