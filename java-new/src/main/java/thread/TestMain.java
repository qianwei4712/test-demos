package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author shiva   2020/7/28 21:51
 */
class TestMain {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        WaitNotify example = new WaitNotify();
        executorService.execute(example::waitObj);
        executorService.execute(example::waitObj);
        executorService.execute(example::waitObj);
        executorService.execute(example::notifyObj);
        executorService.shutdown();
        System.out.println("main ���߳̽���");
    }

    public static class WaitNotify {

        public synchronized void notifyObj() {
            try {
                Thread.sleep(2000);
                System.out.println("notity ����");
                notify();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        public synchronized void waitObj() {
            try {
                System.out.println("wait ����");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("wait ����");
        }
    }
}
