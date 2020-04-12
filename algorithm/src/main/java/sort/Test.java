package sort;

import java.util.Arrays;

/**
 * @author shiva   2020/4/11 23:05
 */
class Test {
    public static void main(String[] args) {
        double[] array = {1, 3, 95, 21};
        double[] result = ArraySortUtil.bubbleSort(array);
        System.out.println(Arrays.toString(result));
    }
}
