package demo6;

/**
 * @author chendong
 * @date 2019/4/14 14:53
 */
public class Demo {

    public synchronized void a(){
        System.out.println("a");
        //b();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void b(){
        System.out.println("b");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        Demo d = new Demo();
        Demo d2 = new Demo();
        new Thread(new Runnable() {
            @Override
            public void run() {

                d.a();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {

                d2.b();
            }
        }).start();
    }

}
