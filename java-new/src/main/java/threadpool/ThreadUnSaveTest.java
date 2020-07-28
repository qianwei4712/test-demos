package threadpool;

import java.util.concurrent.CountDownLatch;

/**
 * @author shiva   2020/6/25 18:58
 */
public class ThreadUnSaveTest {

    // 购买车票数量
    public static int sum = 0;

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(2000);
        for (int i = 0; i < 2000; i++) {
            Thread thread = new Thread(() -> {
                sum = sum + 1;
                countDownLatch.countDown();
            });
            thread.start();
        }
        countDownLatch.await();
        System.out.println("全部车票买完了，一共：" + sum + " 张");

    }
}
