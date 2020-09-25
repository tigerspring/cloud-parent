package collect;

import java.io.OutputStream;
import java.util.*;
import java.util.stream.Collectors;

public class HashSetMain {
    public static void main(String[] args) {


        HashSetMain hashSetMain =new HashSetMain();
//        hashSetMain.treeSetTest();
        hashSetMain.HashMapTest();


    }

    public void hashTest(){
        HashSet set =new HashSet(10);

        set.add("1");
        set.add("a");
        set.add("3");
        set.add("b");
        set.add("4");
        set.add("d");
        set.add("6");
        set.add("7");
        set.add("8");
        set.add("9");

        System.out.println(set);
    }

    public void treeTest(){
        Set<String> set1 = new TreeSet();

        set1.add("1");
        set1.add("a");
        set1.add("3");
        set1.add("b");
        set1.add("4");
        set1.add("d");
        set1.add("6");
        set1.add("7");
        set1.add("8");
        set1.add("9");
        System.out.println(set1);

        for(String s : set1){
            System.out.print(s+",");
        }
    }

    public void treeSetTest(){
        Set<Item> sortedSet = new TreeSet<>((o1,o2)->1);
        sortedSet.add(new Item("bxf1",12));
        sortedSet.add(new Item("aa",10));
        sortedSet.add(new Item("bb",14));
        System.out.println(sortedSet);


        NavigableSet<Item> navigableSet = new TreeSet<>(Comparator.comparing(Item::getAge));
        navigableSet.addAll(sortedSet);


        System.out.println(navigableSet);

        Iterator iterator = navigableSet.descendingIterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next().toString());
        }
        navigableSet = navigableSet.descendingSet();
        System.out.println(navigableSet);


    }


    public void HashMapTest(){

        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("a");
        list.add("a");
        list.add("d");
        list.add("e");
        list.add("s");
        list.add("a");
        list.add("e");

        //使用merge统计list数据
        Map<String,Integer> map = new HashMap();
        Map<String,Integer> computemap = new HashMap();

        list.forEach(e->{
            map.merge(e,1, Integer::sum);

        });



        System.out.println(map);

        map.clear();

        list.forEach(e->{
            map.compute(e,(k,v)->{

                if(Objects.isNull(v)){
                    v = 1;
                }else{
                    v += 1;
                }
                return v;
            });

        });

        System.out.println(map);
    }
}
class Item implements Comparable{

    String name;
    Integer age;

    public Item(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }


    @Override
    public int compareTo(Object other) {
        return 1;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
