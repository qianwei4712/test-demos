package stackandqueue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author shiva   2020/3/31 21:12
 */
public class Test {
    public static void main(String[] args) {
        Test.test2();
    }

    private static void test2(){
        ArrayDeque<Integer> a = new ArrayDeque<>(11);
        a.addFirst(9);
        a.addFirst(0);
        a.add(-1);
        a.add(222);
        System.out.println(a);
    }

    private static void test1(){
        System.out.println((Integer.MAX_VALUE + 1) >>> 1);
        System.out.println(Math.pow(4,15));
    }



}
