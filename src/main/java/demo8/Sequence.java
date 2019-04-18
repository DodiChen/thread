package demo8;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author chendong
 * @date 2019/4/15 21:27
 */
public class Sequence {
    private AtomicInteger value = new AtomicInteger(0);

    public int getNext(){
        return value.getAndIncrement();
    }

    private int[] s = {2,1,4,6};
    AtomicIntegerArray a = new AtomicIntegerArray(s);
    public int getNext2(){
        a.getAndIncrement(1);
        return a.getAndAdd(2, 10);
    }

    AtomicReference<User> user = new AtomicReference<>();

    AtomicIntegerFieldUpdater<User> old = AtomicIntegerFieldUpdater.newUpdater(User.class, "old");
    public int getNext3(){
        User user = new User();
        old.getAndIncrement(user);
        System.out.println(user.getAge());
        return user.getAge();
    }

    public static void main(String[] args) {
        /*Sequence s = new Sequence();
        while (true){
            System.out.println(s.getNext());
        }*/
        Sequence s = new Sequence();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    System.out.println(Thread.currentThread().getName() + " " + s.getNext());
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    System.out.println(Thread.currentThread().getName() + " " + s.getNext());
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    System.out.println(Thread.currentThread().getName() + " " + s.getNext());
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
