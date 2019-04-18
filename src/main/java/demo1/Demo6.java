package demo1;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author chendong
 * @date 2019/4/10 23:13
 */
public class Demo6 {

    public static void main(String[] args) {
        Executor threadPool = Executors.newFixedThreadPool(10);
        //Executor threadPool = Executors.newCachedThreadPool();
        for(int i = 0; i < 100000000; i++){
            threadPool.execute(new Runnable() {
                public void run() {
                    System.out.println(Thread.currentThread().getName());
                }
            });
        }
        ((ExecutorService) threadPool).shutdown();
    }
}
