package ta1;

/**
 * @author chendong
 * @date 2019/4/18 23:27
 */
public class Main {

    private int value;

    private MyLock2 lock = new MyLock2();

    public int next(){
        lock.lock();
        try {
            Thread.sleep(30);
            return value++;
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }finally {
            lock.unlock();
        }
    }

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
        Main main = new Main();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    main.a();
                }
            }
        }).start();
    }

}
