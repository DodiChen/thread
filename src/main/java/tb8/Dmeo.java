package tb8;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Dmeo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Callable<Integer> call = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println("正在计算结果....");
                Thread.sleep(3000);
                return 1;
            }
        } ;
        FutureTask<Integer> task = new FutureTask<Integer>(call);
        Thread thread = new Thread(task);
        thread.start();

        // do something
        System.out.println("干点别的...");
        Integer result = task.get();
        System.out.println("拿到的结果是....");
    }
}
