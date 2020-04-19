package prioityqueuesorces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * @author shiva   2020/4/12 21:12
 */
class Test {
    public static void main(String[] args) {
        Test.test1();
    }

    private static void test3(){
        PriorityQueue<TestObj> queue = new PriorityQueue<>();
        queue.add(new TestObj("50"));
        queue.add(new TestObj("20"));
        queue.add(new TestObj("40"));
        queue.add(new TestObj("60"));
        System.out.println(queue);
    }

    private static void test2(){
        PriorityQueue<String> queue = new PriorityQueue<>();
        queue.add("50");
        queue.add("20");
        queue.add("40");
        queue.add("60");
        System.out.println(queue);
    }

    private static void test1(){
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        queue.add(10);
        queue.add(30);
        queue.add(20);
        queue.add(40);
        queue.add(70);
        queue.add(50);
        queue.add(25);
        queue.add(60);
        queue.poll();
        System.out.println(queue);
    }


    private static class TestObj implements Comparable<TestObj>{
        public String s;
        public TestObj(String s) {
            this.s = s;
        }

        @Override
        public int compareTo(TestObj o) {
            return 0;
        }
    }


}
