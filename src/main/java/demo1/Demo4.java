package demo1;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * @author chendong
 * @date 2019/4/10 23:02
 */
public class Demo4 implements Callable<Integer>{
    public Integer call() throws Exception {
        System.out.println("正在进行紧张的计算");
        Thread.sleep(3000L);
        System.out.println("计算的结果");
        return 1;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Demo4 d = new Demo4();
        FutureTask<Integer> tasks = new FutureTask<Integer>(d);
        Thread thread = new Thread(tasks);
        thread.start();

        System.out.println("我先干点别的");

        Integer result = tasks.get();
        System.out.println("线程执行的结果为: " + result);

    }
}
