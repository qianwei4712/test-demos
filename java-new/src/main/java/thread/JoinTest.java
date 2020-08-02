package thread;

/**
 * @author shiva   2020/8/1 16:09
 */
class JoinTest {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(2000);
                System.out.println("join �̵߳� run ����");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });
        thread.start();

        for (int i = 0; i < 10; i++) {
            if (i == 5){
                thread.join(1999);
            }else {
                System.out.println(System.currentTimeMillis() + "�� main �߳�ѭ���У�" + i);
            }
        }
    }

}
