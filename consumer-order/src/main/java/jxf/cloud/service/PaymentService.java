package jxf.cloud.service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

public interface PaymentService {

    Future<Map> getAnimal(Integer userId);

    List<Map> getAnimalInUserIds(List<Integer> userIds) ;


    List<Map> getAnimalInUserIds1(List<Integer> userIdList);

}
