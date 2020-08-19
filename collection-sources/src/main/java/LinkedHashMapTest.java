import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiConsumer;

/**
 * @author shiva   2020/8/16 12:38
 */
public class LinkedHashMapTest<K, V> extends LinkedHashMap<K, V>{

    @Test
    public void test1(){
        LinkedHashMap<Integer, Integer> map = new LinkedHashMap<>(16, 0.75f, true);
        for (int i = 0; i < 5; i++) {
            map.put(i, i);
        }
        map.get(3);
        map.put(7, 7);
        map.get(2);
        map.forEach((s, s2) -> System.out.print(s + ", "));
    }

    @Test
    public void test2(){
        int[] datas = {10, 2, 24, 18, 34};
        LinkedHashMap<Integer, Integer> map = new LinkedHashMap<>(16, 0.75f, true);
        for (int data : datas) {
            map.put(data, data);
        }
        map.forEach((s, s2) -> System.out.print(s + ", "));
    }



    private static final int MAX_ENTRIES = 100;

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > MAX_ENTRIES;
    }
}
