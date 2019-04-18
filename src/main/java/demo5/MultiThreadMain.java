package demo5;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author chendong
 * @date 2019/4/14 14:17
 */
public class MultiThreadMain {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(20);
        for(int i = 0; i < 20; i++){
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + "" + Singleton2.getInstance2());
                }
            });
        }
        threadPool.shutdown();
    }

}
