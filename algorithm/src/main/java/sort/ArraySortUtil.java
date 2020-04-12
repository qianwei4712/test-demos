package sort;

import com.sun.scenario.effect.Merge;
import com.sun.xml.internal.bind.v2.model.annotation.Quick;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;

/**
 * 常见数组排序算法工具类
 * @author shiva   2020/4/10 21:34
 */
public class ArraySortUtil{

    /**
     * 冒泡排序，增序
     * 平均时间复杂度为O(n²)，最好时间复杂度为O(n)，最坏时间复杂度为O(n²)
     */
    public static double[] bubbleSort(double[] source) {
        // 对 arr 进行拷贝，不改变参数内容
        double[] arr = Arrays.copyOf(source, source.length);
        for (int i = 1; i < arr.length; i++) {
            // 设定一个标记，若为true，则表示此次循环没有进行交换，也就是待排序列已经有序，排序已经完成。
            boolean flag = true;
            for (int j = 0; j < arr.length - i; j++) {
                if (arr[j] > arr[j + 1]){
                    double temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    flag = false;
                }
            }
            if (flag) {
                break;
            }
        }
        return arr;
    }

    /**
     * 选择排序，增序
     * 简单选择排序平均时间复杂度为O(n²)，最好时间复杂度为O(n²)，最坏时间复杂度为O(n²)
     */
    public static double[] selectionSort(double[] source){
        // 对 arr 进行拷贝，不改变参数内容
        double[] arr = Arrays.copyOf(source, source.length);

        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            // 每轮需要比较的次数 N-i
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    // 记录目前能找到的最小值元素的下标
                    minIndex = j;
                }
            }
            // 将找到的最小值和i位置所在的值进行交换
            if (i != minIndex) {
                double tmp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = tmp;
            }
        }
        return arr;
    }

    /**
     * 直接插入排序
     * 直接插入排序平均时间复杂度为O(n²)，最好时间复杂度为O(n)，最坏时间复杂度为O(n²)。
     */
    public static double[] insertionSort(double[] source){
        // 对 arr 进行拷贝，不改变参数内容
        double[] arr = Arrays.copyOf(source, source.length);
        // 从下标为1的元素开始选择合适的位置插入，因为下标为0的只有一个元素，默认是有序的
        for (int i = 1; i < arr.length - 1; i++) {
            // 记录要插入的数据
            double tmp = arr[i];
            // 从已经排序的序列最右边的开始比较，找到比其小的数
            int j = i;
            while (j > 0 && tmp < arr[j - 1]) {
                arr[j] = arr[j - 1];
                j--;
            }
            // 存在比其小的数，插入
            if (j != i) {
                arr[j] = tmp;
            }
        }
        return arr;
    }

    /**
     * 希尔排序
     * 平均时间复杂度为O(nlogn)，最好时间复杂度为O(nlog²n)，最坏时间复杂度为O(nlog²n)。希尔排序的时间复杂度与增量序列的选取有关
     */
    public static double[] shellSort(double[] source){
        // 对 arr 进行拷贝，不改变参数内容
        double[] arr = Arrays.copyOf(source, source.length);
        int gap = 1;
        while (gap < arr.length/3) {
            gap = gap * 3 + 1;
        }
        while (gap > 0) {
            for (int i = gap; i < arr.length; i++) {
                double tmp = arr[i];
                int j = i - gap;
                while (j >= 0 && arr[j] > tmp) {
                    arr[j + gap] = arr[j];
                    j -= gap;
                }
                arr[j + gap] = tmp;
            }
            gap = (int) Math.floor(gap / 3);
        }
        return arr;
    }

    /**
     * 归并排序
     * 平均时间复杂度为O(nlogn)，最好时间复杂度为O(nlogn)，最坏时间复杂度为O(nlogn)。
     * 归并排序的形式就是一棵二叉树，它需要遍历的次数就是二叉树的深度，而根据完全二叉树的可以得出它在任何情况下时间复杂度均是O(nlogn)
     */
    public static double[] mergeSort(double[] source){
        // 对 arr 进行拷贝，不改变参数内容
        double[] arr = Arrays.copyOf(source, source.length);
        if (arr.length < 2) {
            return arr;
        }
        int middle = (int) Math.floor(arr.length / 2);
        double[] left = Arrays.copyOfRange(arr, 0, middle);
        double[] right = Arrays.copyOfRange(arr, middle, arr.length);

        return merge(mergeSort(left), mergeSort(right));
    }
    protected static double[] merge(double[] left, double[] right) {
        double[] result = new double[left.length + right.length];
        int i = 0;
        while (left.length > 0 && right.length > 0) {
            if (left[0] <= right[0]) {
                result[i++] = left[0];
                left = Arrays.copyOfRange(left, 1, left.length);
            } else {
                result[i++] = right[0];
                right = Arrays.copyOfRange(right, 1, right.length);
            }
        }
        while (left.length > 0) {
            result[i++] = left[0];
            left = Arrays.copyOfRange(left, 1, left.length);
        }

        while (right.length > 0) {
            result[i++] = right[0];
            right = Arrays.copyOfRange(right, 1, right.length);
        }

        return result;
    }

    /**
     * 快速排序
     */
    public static double[] quickSort(double[] source){
        // 对 arr 进行拷贝，不改变参数内容
        double[] arr = Arrays.copyOf(source, source.length);
        return quickSort(arr, 0, arr.length - 1);
    }
    private static double[] quickSort(double[] arr, int left, int right) {
        if (left < right) {
            int partitionIndex = partition(arr, left, right);
            quickSort(arr, left, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, right);
        }
        return arr;
    }
    private static int partition(double[] arr, int left, int right) {
        // 设定基准值（pivot）
        int pivot = left;
        int index = pivot + 1;
        for (int i = index; i <= right; i++) {
            if (arr[i] < arr[pivot]) {
                swap(arr, i, index);
                index++;
            }
        }
        swap(arr, pivot, index - 1);
        return index - 1;
    }
    private static void swap(double[] arr, int i, int j) {
        double temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    /**
     * 堆排序
     * 时间复杂度为O(nlogn)，最好时间复杂度为O(nlogn)，最坏时间复杂度为O(nlogn)。
     */
    public static double[] heapSort(double[] source){
        // 对 arr 进行拷贝，不改变参数内容
        double[] arr = Arrays.copyOf(source, source.length);
        int len = arr.length;
        buildMaxHeap(arr, len);
        for (int i = len - 1; i > 0; i--) {
            swap(arr, 0, i);
            len--;
            heapify(arr, 0, len);
        }
        return arr;
    }
    private static void buildMaxHeap(double[] arr, int len) {
        for (int i = (int) Math.floor(len / 2); i >= 0; i--) {
            heapify(arr, i, len);
        }
    }
    private static void heapify(double[] arr, int i, int len) {
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int largest = i;
        if (left < len && arr[left] > arr[largest]) {
            largest = left;
        }
        if (right < len && arr[right] > arr[largest]) {
            largest = right;
        }
        if (largest != i) {
            swap(arr, i, largest);
            heapify(arr, largest, len);
        }
    }

    /**
     * 计数排序
     */
    public static int[] countingSort(int[] source) {
        // 对 arr 进行拷贝，不改变参数内容
        int[] arr = Arrays.copyOf(source, source.length);
        int maxValue = getMaxValue(arr);
        return countingSort(arr, maxValue);
    }
    private static int[] countingSort(int[] arr, int maxValue) {
        int bucketLen = maxValue + 1;
        int[] bucket = new int[bucketLen];
        for (int value : arr) {
            bucket[value]++;
        }
        int sortedIndex = 0;
        for (int j = 0; j < bucketLen; j++) {
            while (bucket[j] > 0) {
                arr[sortedIndex++] = j;
                bucket[j]--;
            }
        }
        return arr;
    }
    private static int getMaxValue(int[] arr) {
        int maxValue = arr[0];
        for (int value : arr) {
            if (maxValue < value) {
                maxValue = value;
            }
        }
        return maxValue;
    }


    /**
     * 桶排序
     */
    public static int[] bucketSort(int[] array){
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < array.length; i++){
            max = Math.max(max, array[i]);
            min = Math.min(min, array[i]);
        }

        /*桶映射函数：自己设计，要保证桶 i 的数均小于桶 j （i < j）的数，
         即必须桶间必须有序，桶内可以无序。这里桶映射函数为：(i - min) / arr.length*/
        int bucketNum = (max - min) / array.length + 1;
        ArrayList<ArrayList<Integer>> bucketArr = new ArrayList<>(bucketNum);
        for(int i = 0; i < bucketNum; i++){
            bucketArr.add(new ArrayList<Integer>());
        }
        //将每个元素放入桶
        for(int i = 0; i < array.length; i++){
            int num = (array[i] - min) / (array.length);
            bucketArr.get(num).add(array[i]);
        }

        //对每个桶进行排序
        for(int i = 0; i < bucketArr.size(); i++){
            Collections.sort(bucketArr.get(i));
        }

        int k = 0;
        for(int i = 0; i < bucketArr.size(); i++){
            for(int j = 0;j < bucketArr.get(i).size();j++) {
                array[k++] = bucketArr.get(i).get(j);
            }
        }
        return array;
    }

    /**
     * 基数排序
     */
    public static int[] radixSort(int[] sourceArray){
        // 对 arr 进行拷贝，不改变参数内容
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);

        int maxDigit = getMaxDigit(arr);
        return radixSort(arr, maxDigit);
    }
    //获取最高位数
    private static int getMaxDigit(int[] arr) {
        int maxValue = getMaxValue(arr);
        return getNumLenght(maxValue);
    }
    protected static int getNumLenght(long num) {
        if (num == 0) {
            return 1;
        }
        int lenght = 0;
        for (long temp = num; temp != 0; temp /= 10) {
            lenght++;
        }
        return lenght;
    }
    private static int[] radixSort(int[] arr, int maxDigit) {
        int mod = 10;
        int dev = 1;

        for (int i = 0; i < maxDigit; i++, dev *= 10, mod *= 10) {
            // 考虑负数的情况，这里扩展一倍队列数，其中 [0-9]对应负数，[10-19]对应正数 (bucket + 10)
            int[][] counter = new int[mod * 2][0];
            for (int j = 0; j < arr.length; j++) {
                int bucket = ((arr[j] % mod) / dev) + mod;
                counter[bucket] = arrayAppend(counter[bucket], arr[j]);
            }

            int pos = 0;
            for (int[] bucket : counter) {
                for (int value : bucket) {
                    arr[pos++] = value;
                }
            }
        }
        return arr;
    }

    /**
     * 自动扩容，并保存数据
     */
    private static int[] arrayAppend(int[] arr, int value) {
        arr = Arrays.copyOf(arr, arr.length + 1);
        arr[arr.length - 1] = value;
        return arr;
    }
}
