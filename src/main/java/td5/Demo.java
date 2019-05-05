package td5;

import java.util.concurrent.*;

public class Demo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //ThreadPoolExecutor threadPool = new ThreadPoolExecutor(10, 20, 0, TimeUnit.MICROSECONDS, new LinkedBlockingDeque<>());

        //ExecutorService threadPool = Executors.newFixedThreadPool(10);

        //ExecutorService threadPool = Executors.newCachedThreadPool();

        ExecutorService threadPool = Executors.newScheduledThreadPool(10);


        Future<Integer> f = threadPool.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return 1;
            }
        });
        System.out.println(f.get());


        while(true){
            /*threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName());
                }
            });*/
            ((ScheduledExecutorService) threadPool).schedule(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName());
                }
            },5, TimeUnit.SECONDS);
        }
    }
}
