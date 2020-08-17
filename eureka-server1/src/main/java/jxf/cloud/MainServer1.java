package jxf.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class MainServer1 {
    public static void main(String[] args) {
        SpringApplication.run(MainServer1.class,args);
    }
}
