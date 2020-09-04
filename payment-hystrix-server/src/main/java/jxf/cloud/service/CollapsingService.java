package jxf.cloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

@Service
public class CollapsingService {

    @HystrixCollapser(batchMethod = "collapingList",collapserProperties = {
            @HystrixProperty(name="timerDelayInMilliseconds",value = "1000")
    })
    public Future<Animal> collaping(Integer id){
        return null;
    }


    @HystrixCollapser(batchMethod = "collapingList",scope = com.netflix.hystrix.HystrixCollapser.Scope.GLOBAL,collapserProperties = {
            @HystrixProperty(name="timerDelayInMilliseconds",value = "1000")
    })
    public Future<Animal> collapingGlobal(Integer id){
        return null;
    }

    @HystrixCommand
    public List<Animal> collapingGlobalList(List<Integer> animalParam){
        return this.collapingList(animalParam);
    }

    @HystrixCommand
    public List<Animal> collapingList(List<Integer> animalParam){
        System.out.println("当前线程collapingList"+Thread.currentThread().getName());
        List<Animal> list = new ArrayList<>();
        for(Integer num : animalParam){
            Animal animal = new Animal();
            animal.setId("cat"+num);
            list.add(animal);
        }
        return list;
    }


}
