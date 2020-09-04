package jxf.cloud;

import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

public class TestMain {


    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();

        for(int i = 0 ; i< 5 ; i++){
            final int finalI = i;
            new Thread(()->{
                Map map = restTemplate.getForObject("http://localhost:9700/test1/"+finalI, Map.class);
                System.out.println("param:"+finalI + "   resutrn map:"+map );
            }).start();
        }



    }

}
