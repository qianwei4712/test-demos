package arraylistsources;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;

/**
 * @author shiva   2020/3/15 23:11
 */
class Test{


    public static void main(String[] args) {
        Test test = new Test();
        test.test4();
    }

    public void test4() {
        String s = "2";
        ArrayList<String> list = new ArrayList<String>();
        list.add(s);
        list.add(s);
        list.add(s);
        Spliterator<String> spliterator = list.spliterator();
        System.out.println(spliterator.trySplit());
    }


    public void test3() {
        String s = "2";
        ArrayList<String> list = new ArrayList<String>();
        list.add(s);
        list.add(s);
        list.add(s);
        List<String> strings = list.subList(1, 2);
        strings.size();
    }


    public void test2() {
        String s = "2";
        ArrayList<String> list = new ArrayList<String>();
        list.add(s);
        list.add(s);
        list.add(s);
        list.forEach(System.out::println);
    }


    public void test1() {
        String s = "2";
        ArrayList<String> list = new ArrayList<String>();
        list.add(s);
        list.add(s);
        list.add(s);
        list.replaceAll((UnaryOperator<String>) o -> {
            if ("2".equals(o)){
                return null;
            }
            return o;
        });
        System.out.println(list.size());
    }

}
