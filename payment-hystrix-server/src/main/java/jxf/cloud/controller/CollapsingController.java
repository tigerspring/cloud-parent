package jxf.cloud.controller;

import jxf.cloud.service.Animal;
import jxf.cloud.service.CollapsingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class CollapsingController {

    static List<Map> userList =new ArrayList<>();

    static {
        for(int i = 0 ; i < 10; i++){
            int finalI = i;
            Map map = new HashMap(){
                {
                    put(finalI,"user"+finalI);
                }
            };
            userList.add(map);
        }
    }

    @GetMapping("/getAnimal/{userId}")
    public Map getAnimal(@PathVariable Integer userId) {
        List<Map> list = userList.stream().filter(e-> Objects.nonNull(e.get(userId))).collect(Collectors.toList());
        if(CollectionUtils.isEmpty(list)){
            return Collections.emptyMap();
        }
        return list.get(0);
    }


    @GetMapping("/getAnimalInUserIds")
    public List<Map> getAnimalGlobal(@RequestParam String userIds) {
        List<Integer> userIdList = Stream.of(userIds.split(",")).map(e->Integer.parseInt(e)).collect(Collectors.toList());
        List<Map> list = userList.stream().filter(e-> userIdList.containsAll(e.keySet())).collect(Collectors.toList());
        System.out.println(Thread.currentThread().getName()+"======>"+list);
        return list;
    }
}
