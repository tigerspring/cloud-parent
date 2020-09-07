package jxf.cloud.service;

import java.util.Map;

public interface CatchPaymentService {

    Map getPerson(Integer id);

    Map getPersonToCommandKey(Integer id);

    String updatePerson(Integer id);
}
