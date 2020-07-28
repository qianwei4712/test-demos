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
     * ����һ������߳�����20���̳߳�
     */
    public static ThreadPoolExecutor pool = new ThreadPoolExecutor(
                                            10, 20, 0L,
                                            TimeUnit.MILLISECONDS,
                                            new LinkedBlockingQueue<>());

    /**
     * �߳�ִ�з���������ȴ�0��10��
     */
    private static void sleepMtehod(int index){
        try {
            long sleepTime = new Double(Math.random() * 10000).longValue();
            Thread.sleep(sleepTime);
            System.out.println("��ǰ�߳�ִ�н���: " + index);
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
            System.out.println("��ûֹͣ������");
        }
        System.out.println("ȫ��ִ�����");
    }


    private static void taskCountTest() throws Exception {
        for (int i = 0; i < 30; i++) {
            int index = i;
            pool.execute(() -> sleepMtehod(index));
        }
        //���̳߳���ɵ��߳��������̳߳��е����߳���
        while (!(pool.getTaskCount() == pool.getCompletedTaskCount())) {
            System.out.println("��������:" + pool.getTaskCount() + "�� �Ѿ����������:" + pool.getCompletedTaskCount());
            Thread.sleep(1000);
            System.out.println("��ûֹͣ������");
        }
        System.out.println("ȫ��ִ�����");
    }

    private static void countDownLatchTest() throws Exception {
        //���������ж��߳��Ƿ�ִ�н���
        CountDownLatch taskLatch = new CountDownLatch(30);
        for (int i = 0; i < 30; i++) {
            int index = i;
            pool.execute(() -> {
                sleepMtehod(index);
                taskLatch.countDown();
                System.out.println("��ǰ������������" + taskLatch.getCount());
            });
        }
        //��ǰ�߳��������ȴ���������Ϊ0
        taskLatch.await();
        System.out.println("ȫ��ִ�����");
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
            System.out.println("��ûֹͣ��������ǰ���������:" + taskNum);
        }
        System.out.println("ȫ��ִ�����");
    }

    private static void futureTest() throws Exception {
        Future<?> future = pool.submit(() -> sleepMtehod(1));
        while (!future.isDone()){
            Thread.sleep(500);
            System.out.println("��ûֹͣ������");
        }
        System.out.println("ȫ��ִ�����");
    }

}
