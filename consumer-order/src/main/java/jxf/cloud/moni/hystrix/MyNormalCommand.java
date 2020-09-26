package jxf.cloud.moni.hystrix;

import com.netflix.hystrix.HystrixCommand;
import jxf.cloud.moni.feign.PaymentFeignInerface;

import java.lang.reflect.Method;

public class MyNormalCommand extends HystrixCommand {
    private PaymentFeignInerface paymentFeignInerface;


    private String invokeMethod;

    private Object[] parmas;


    public MyNormalCommand(HystrixCommand.Setter setter, PaymentFeignInerface paymentFeignInerface, String invokeMethod, Object... parms) {
        super(setter);
        this.paymentFeignInerface=paymentFeignInerface;
        this.parmas=parms;
        this.invokeMethod = invokeMethod;
    }

    @Override
    protected Object run() throws Exception {
        Class<?> clazz = PaymentFeignInerface.class;

        Method[] methods = clazz.getMethods();
        for(Method method : methods){
            if(method.getName().equals(invokeMethod)){
                return method.invoke(paymentFeignInerface,parmas);
            }
        }
        return null;
    }

    @Override
    protected Object getFallback() {
        System.out.println("fall back thread "+ Thread.currentThread().getName());
        return "hystrix fall bak threadname:"+Thread.currentThread().getName();
    }
}
