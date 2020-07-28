package threadpool;

import org.junit.jupiter.api.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * @author shiva   2020/7/18 14:24
 */
public class ThreadTest {

    @Test
    public void test1(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Runnable");
            }
        }).start();
    }

    @Test
    public void test2(){
        new Thread(() -> System.out.println("Runnable")).start();
    }

    @Test
    public void test3(){
        FutureTask<String> ft = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "";
            }
        });
        new Thread(ft).start();
    }

    @Test
    public void test4() throws Exception {
        FutureTask<String> ft = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "����";
            }
        });
        new Thread(ft).start();
        System.out.println(ft.get());
    }

    @Test
    public void test5() throws Exception {
        new Thread().start();
    }

    @Test
    public void test6() throws Exception {
        Thread thread = new Thread(() -> {
            try {
                for (int i = 0; i < 10000; i++) {
                    Thread.sleep(1000);
                    System.out.println("�ػ��߳���δֹͣ");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.setDaemon(true);
        thread.start();
        //���̵߳ȴ�3��
        Thread.sleep(3000);
        System.out.println("���̣߳����ػ��̣߳������������ػ��̼߳����ر�");
    }


    public static void main(String[] args) throws Exception {
        System.out.println(Thread.currentThread().getName());
    }




}
