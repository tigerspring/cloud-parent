package jxf.cloud.code;

import jxf.cloud.code.bean.My2Bean;
import jxf.cloud.code.bean.MyBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:application.xml","classpath:application2.xml");

        MyBean myBean = (MyBean) applicationContext.getBean("mybean");
        My2Bean myBean2 =  (My2Bean) applicationContext.getBean("mybean2");
        myBean.doprint();
        myBean2.doprint();
    }
}
