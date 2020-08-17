package jxf.cloud.consum;

import com.netflix.discovery.DiscoveryClient;
import jxf.cloud.balance.RestTemplateConfig;
import jxf.cloud.feign.PayMentInerface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.netflix.eureka.EurekaDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;

@RestController
@RequestMapping("/consum")
public class ConsumController {

    @Resource
    private RestTemplateConfig restTemplateConfig;

    @Autowired
    private PayMentInerface payMentInerface;

    @Resource
    private EurekaDiscoveryClient discoveryClient;

    private String pamentUrl="http://payment-server/pay/pament";

    @GetMapping("/consumPayMent")
    public String consumPayMent(){

        String res = restTemplateConfig.getRestTemplate().getForObject(pamentUrl,String.class);
        System.out.println( res );
        return res;
    }

    @GetMapping("/discoveryClientInfo")
    public List getdiscoveryClientInfo(){
        Map<String,Object> returnMap = new HashMap<>();
        List<String> list = discoveryClient.getServices();
        List<ServiceInstance> returnList = new ArrayList<>();
        for(String service : list ){
            List<ServiceInstance> serviceInstances = discoveryClient.getInstances(service);
            for(ServiceInstance serviceInstance : serviceInstances) {
                returnList.add(serviceInstance);
            }
        }

        return returnList;
    }

    @GetMapping("/feignTest")
    public Object feignTest(){
        System.out.println("执行");
        return payMentInerface.payment();
    }
}
