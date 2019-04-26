package tb5;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Demo {


    Random random = new Random();

    public void meeting(CyclicBarrier barrier){
        try {
            Thread.sleep(random.nextInt(4000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " 到达会议室， 等待开会...");
        if(Thread.currentThread().getName().equalsIgnoreCase("Thread-7")){
            //Thread.currentThread().interrupt();
            barrier.reset();

        }

        try {
            barrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "发言");
    }

    public static void main(String[] args) {
        Demo demo = new Demo();
        CyclicBarrier barrier = new CyclicBarrier(10, new Runnable() {
            @Override
            public void run() {
                System.out.println("好，我们开始开户...");
            }
        });

        for(int i = 0; i < 10; i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    demo.meeting(barrier);
                }
            }).start();
        }

        // 监控等待线程数
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("等待的线程数 " + barrier.getNumberWaiting());
                    System.out.println("is broken " + barrier.isBroken());
                }
            }
        }).start();
    }

}
