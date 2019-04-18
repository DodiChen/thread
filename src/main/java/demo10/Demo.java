package demo10;

/**
 * @author chendong
 * @date 2019/4/17 22:33
 */
public class Demo {

    MyLock lock = new MyLock();

    public void a(){
        lock.lock();
        System.out.println("a");
        b();
        lock.unlock();
    }

    public void b(){
        lock.lock();
        System.out.println("b");
        lock.unlock();
    }

    public static void main(String[] args) {
        Demo d = new Demo();

        new Thread(new Runnable() {
            @Override
            public void run() {
                d.a();
            }
        }).start();


    }

}
