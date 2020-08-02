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
                System.out.println("�߳� sleep ����");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
        thread.interrupt();
        System.out.println("main �̵߳��� interrupt ����");
        System.out.println("�߳��׳����ж��쳣����ʱ״̬��" + thread.isInterrupted());
        Thread.sleep(5000);
        System.out.println("�ȴ� 5 �룬�߳�Ӧ���Ѿ���������ʱ״̬��" + thread.isInterrupted());
    }




    public void test1() throws Exception {
        Example example = new Example();
        System.out.println("��ǰ�߳� example ��δ��������ʱ״̬��" + example.isInterrupted());
        example.start();
        System.out.println("��ǰ�߳� example ��������δ���� interrupt����ʱ״̬��" + example.isInterrupted());
        example.interrupt();
        System.out.println("��ǰ�߳� example �����ҵ��� interrupt����ʱ״̬��" + example.isInterrupted());
        Thread.sleep(5000);
        System.out.println("�ȴ� 5 �룬example Ӧ���Ѿ���������ʱ״̬��" + example.isInterrupted());
    }


    public static class Example extends Thread{
        @Override
        public void run() {
            while (!isInterrupted()){
            }
            System.out.println("�߳��ж�");
        }
    }

    public static class Example2 extends Thread{
        @Override
        public void run() {
            System.out.println("���� interrupt �������߳��ж�");
            interrupt();
            System.out.println("��δ���� interrupted ��������ʱ�߳��ж�״̬��" + isInterrupted());
            System.out.println("�̵߳� 1 �ε��� interrupted �������������أ�" + Thread.interrupted());
            System.out.println("�̵߳� 1 �ε��� interrupted ���������󣬴�ʱ�߳��ж�״̬��" + isInterrupted());
            System.out.println("�̵߳� 2 �ε��� interrupted �������������أ�" + Thread.interrupted());
            System.out.println("�̵߳� 2 �ε��� interrupted ���������󣬴�ʱ�߳��ж�״̬��" + isInterrupted());
            System.out.println("�̵߳� 3 �ε��� interrupted �������������أ�" + Thread.interrupted());
            System.out.println("�̵߳� 3 �ε��� interrupted ���������󣬴�ʱ�߳��ж�״̬��" + isInterrupted());
        }
    }

}
