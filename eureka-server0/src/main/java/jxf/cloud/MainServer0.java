package jxf.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class MainServer0 {
    public static void main(String[] args) {
        SpringApplication.run(MainServer0.class,args);
    }
}
