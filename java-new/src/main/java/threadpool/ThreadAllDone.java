package threadpool;

import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author shiva   2020/6/6 17:20
 */
class ThreadAllDone {

    /**
     * 创建一个最大线程数是20的线程池
     */
    public static ThreadPoolExecutor pool = new ThreadPoolExecutor(
                                            10, 20, 0L,
                                            TimeUnit.MILLISECONDS,
                                            new LinkedBlockingQueue<>());

    /**
     * 线程执行方法，随机等待0到10秒
     */
    private static void sleepMtehod(int index){
        try {
            long sleepTime = new Double(Math.random() * 10000).longValue();
            Thread.sleep(sleepTime);
            System.out.println("当前线程执行结束: " + index);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws Exception {
        ThreadAllDone.futureTest();
//        ThreadAllDone.staticCountTest();
//        ThreadAllDone.countDownLatchTest();
//        ThreadAllDone.taskCountTest();
//        ThreadAllDone.shutdownTest();

    }

    private static void shutdownTest() throws Exception {
        for (int i = 0; i < 30; i++) {
            int index = i;
            pool.execute(() -> sleepMtehod(index));
        }
        pool.shutdown();
        while (!pool.isTerminated()){
            Thread.sleep(1000);
            System.out.println("还没停止。。。");
        }
        System.out.println("全部执行完毕");
    }


    private static void taskCountTest() throws Exception {
        for (int i = 0; i < 30; i++) {
            int index = i;
            pool.execute(() -> sleepMtehod(index));
        }
        //当线程池完成的线程数等于线程池中的总线程数
        while (!(pool.getTaskCount() == pool.getCompletedTaskCount())) {
            System.out.println("任务总数:" + pool.getTaskCount() + "； 已经完成任务数:" + pool.getCompletedTaskCount());
            Thread.sleep(1000);
            System.out.println("还没停止。。。");
        }
        System.out.println("全部执行完毕");
    }

    private static void countDownLatchTest() throws Exception {
        //计数器，判断线程是否执行结束
        CountDownLatch taskLatch = new CountDownLatch(30);
        for (int i = 0; i < 30; i++) {
            int index = i;
            pool.execute(() -> {
                sleepMtehod(index);
                taskLatch.countDown();
                System.out.println("当前计数器数量：" + taskLatch.getCount());
            });
        }
        //当前线程阻塞，等待计数器置为0
        taskLatch.await();
        System.out.println("全部执行完毕");
    }


    private static int taskNum = 0;
    private static void staticCountTest() throws Exception {
        Lock lock = new ReentrantLock();
        for (int i = 0; i < 30; i++) {
            int index = i;
            pool.execute(() -> {
                sleepMtehod(index);
                lock.lock();
                taskNum++;
                lock.unlock();
            });
        }
        while(taskNum < 30) {
            Thread.sleep(1000);
            System.out.println("还没停止。。。当前完成任务数:" + taskNum);
        }
        System.out.println("全部执行完毕");
    }

    private static void futureTest() throws Exception {
        Future<?> future = pool.submit(() -> sleepMtehod(1));
        while (!future.isDone()){
            Thread.sleep(500);
            System.out.println("还没停止。。。");
        }
        System.out.println("全部执行完毕");
    }

}
