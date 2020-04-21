package java8;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author shiva   2020/4/21 21:30
 */
public class StreamTest {
    public static void main(String[] args) {
        StreamTest.test2();
    }

    public static void test3(){
    }

    public static void test2(){
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        // ��ȡ��Ӧ��ƽ����
        List<Integer> squaresList = numbers.stream().map( i -> i*i).distinct().collect(Collectors.toList());
        squaresList.forEach(System.out::println);
    }

    public static void test1(){
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);

        IntSummaryStatistics stats = numbers.stream().mapToInt((x) -> x).summaryStatistics();

        System.out.println("�б��������� : " + stats.getMax());
        System.out.println("�б�����С���� : " + stats.getMin());
        System.out.println("������֮�� : " + stats.getSum());
        System.out.println("ƽ���� : " + stats.getAverage());
    }
}
