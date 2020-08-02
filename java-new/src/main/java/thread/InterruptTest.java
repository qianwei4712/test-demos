package thread;

/**
 * @author shiva   2020/8/2 13:39
 */
class InterruptTest {

    public static void main(String[] args) throws Exception {
        InterruptTest example = new InterruptTest();
        example.test3();
    }

    public void test3() {
        Example2 example = new Example2();
        example.start();
    }

    public void test2() throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(2000);
                System.out.println("线程 sleep 结束");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
        thread.interrupt();
        System.out.println("main 线程调用 interrupt 结束");
        System.out.println("线程抛出了中断异常，此时状态：" + thread.isInterrupted());
        Thread.sleep(5000);
        System.out.println("等待 5 秒，线程应该已经消亡，此时状态：" + thread.isInterrupted());
    }




    public void test1() throws Exception {
        Example example = new Example();
        System.out.println("当前线程 example 尚未启动，此时状态：" + example.isInterrupted());
        example.start();
        System.out.println("当前线程 example 启动且尚未调用 interrupt，此时状态：" + example.isInterrupted());
        example.interrupt();
        System.out.println("当前线程 example 启动且调用 interrupt，此时状态：" + example.isInterrupted());
        Thread.sleep(5000);
        System.out.println("等待 5 秒，example 应该已经消亡，此时状态：" + example.isInterrupted());
    }


    public static class Example extends Thread{
        @Override
        public void run() {
            while (!isInterrupted()){
            }
            System.out.println("线程中断");
        }
    }

    public static class Example2 extends Thread{
        @Override
        public void run() {
            System.out.println("调用 interrupt 方法，线程中断");
            interrupt();
            System.out.println("尚未调用 interrupted 方法，此时线程中断状态：" + isInterrupted());
            System.out.println("线程第 1 次调用 interrupted 方法，方法返回：" + Thread.interrupted());
            System.out.println("线程第 1 次调用 interrupted 方法结束后，此时线程中断状态：" + isInterrupted());
            System.out.println("线程第 2 次调用 interrupted 方法，方法返回：" + Thread.interrupted());
            System.out.println("线程第 2 次调用 interrupted 方法结束后，此时线程中断状态：" + isInterrupted());
            System.out.println("线程第 3 次调用 interrupted 方法，方法返回：" + Thread.interrupted());
            System.out.println("线程第 3 次调用 interrupted 方法结束后，此时线程中断状态：" + isInterrupted());
        }
    }

}
