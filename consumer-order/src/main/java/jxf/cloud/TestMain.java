package jxf.cloud;

import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class TestMain {


    public static void main(String[] args) throws InterruptedException, IOException {
//        System.out.println(TestMain.test().toString());

        List<String> list = new LinkedList<>();


        list.add("a");
        TimeUnit.SECONDS.sleep(1);
        System.out.println(list);
        list.add("b");
        TimeUnit.SECONDS.sleep(1);
        System.out.println(list);
        list.add("d");
        TimeUnit.SECONDS.sleep(1);
        System.out.println(list);



        ListIterator iterator = list.listIterator();
        iterator.next();
        iterator.next();
        TimeUnit.SECONDS.sleep(1);
        iterator.add("c");
        System.out.println(list);
        TimeUnit.SECONDS.sleep(1);
        iterator.add("e");
        TimeUnit.SECONDS.sleep(1);
        System.out.println(list);
        iterator.add("f");
        System.out.println(list);



    }

    public static A test(){
        A a = new A();
        try{
            a.setI(1);
            return a;
        }finally {
            a.setI(2);
            return a;
        }
    }

}

class A extends Object{

    private int i=0;

    public A(int i) {
        this.i = i;
    }

    public A() {
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    @Override
    public String toString() {
        return "A{" +
                "i=" + i +
                '}';
    }

    @Override
    protected void finalize(){
        System.out.println("销毁A");
    }

}