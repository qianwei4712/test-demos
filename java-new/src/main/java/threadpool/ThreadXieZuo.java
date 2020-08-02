package threadpool;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.jupiter.api.Test;

/**
 * @author shiva   2020/7/28 18:48
 */
class ThreadXieZuo {

    @Test
    public void test1() throws InterruptedException {
        WaitTest obj = new WaitTest();
        obj.wh();
        synchronized (obj){
            obj.wait();
        }
        Thread.sleep(2000);
        synchronized (obj){
            obj.notify();
        }

    }

    public void threadNotify(Object object){
        new Thread(() -> {
            try {

            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    class WaitTest{
        public void wh() throws InterruptedException {
            while (true){
                Thread.sleep(500);
                System.out.println(System.currentTimeMillis() + ": 测试类循环体中。。。。。");
            }
        }
    }

}
