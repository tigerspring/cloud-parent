package jxf.cloud.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ThreadLocalController {

    private static final ThreadLocal<Integer> integerThreadLocal = ThreadLocal.withInitial(()->null);

    @RequestMapping("/threadLocal/test")
    public Map testThreadLocal(@RequestParam Integer userId){
        String before = Thread.currentThread().getName()+"  "+integerThreadLocal.get();
        integerThreadLocal.set(userId);
        //设置用户信息之后再查询一次ThreadLocal中的用户信息
        String after = Thread.currentThread().getName() + ":" + integerThreadLocal.get();
        //汇总输出两次查询结果
        Map result = new HashMap();
        result.put("before", before);
        result.put("after", after);
        System.out.println(result);
        return result;

    }

}
