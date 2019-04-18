package demo1;

/**
 * @author chendong
 * @date 2019/4/10 22:50
 */
public class Demo2 implements Runnable {
    public void run() {
        while (true){
            System.out.println("thread running");
        }
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new Demo2());
        thread.start();
    }
}
