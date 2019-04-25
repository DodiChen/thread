package tb2;

/**
 * @author chendong
 * @date 2019/4/23 21:06
 */
public class Demo {

    private Thread thread;

    public void a(Thread joinThread){
        System.out.println("方法a执行了...");
        joinThread.start();
        try {
            joinThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("a方法执行完毕...");
    }

    public void b(){
        System.out.println("加塞线程开始执行...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("加载线程执行完毕...");
    }

    public static void main(String[] args) {
        Demo d = new Demo();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                d.b();
            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                d.a(t1);
            }
        }).start();
    }
}
