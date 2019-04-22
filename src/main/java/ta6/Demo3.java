package ta6;

import thread.t4.Target;

import java.util.concurrent.TimeUnit;

/**
 * @author chendong
 * @date 2019/4/22 20:54
 */
public class Demo3 {

    private volatile int signal;

    public synchronized void set(){
        this.signal = 1;
        //notify(); // notify方法会随机叫醒一个wait状态的线程
        notifyAll(); //notifyAll 方法会叫醒所有的处于wait状态的线程，争夺时间片的线程只有一个
        System.out.println("叫醒线程叫醒之后休眠开始...");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized int get(){
        System.out.println(Thread.currentThread().getName() + "方法执行了。。。");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(signal != 1){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + "方法执行完毕");
        return signal;
    }

    public static void main(String[] args) {
        Demo3 d = new Demo3();
        Target1 t1 = new Target1(d);
        Target2 t2 = new Target2(d);

        new Thread(t2).start();
        new Thread(t2).start();
        new Thread(t2).start();
        new Thread(t2).start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(t1).start();
    }

}
