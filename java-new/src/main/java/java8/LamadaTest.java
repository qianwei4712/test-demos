package java8;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author shiva   2020/4/20 19:54
 */
public class LamadaTest {
    public static void main(String[] args) {
        LamadaTest.test3();
    }

    public static void test3(){
        Arrays.asList( "a", "b", "d" ).forEach(System.out::println);
    }

    public static void test2(){
        Arrays.asList( "a", "b", "d" ).forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        });
    }

    public static void test1(){
        List<String> list = Arrays.asList( "a", "b", "d" );
        for(String e:list){
            System.out.println(e);
        }
    }
}
