package demo7;

import demo6.Demo;

/**
 * 保证可见性的前提
 * 多个线程必须拿到的是同一把锁
 */
public class Dmeo {

    public volatile int a = 1;

    public int getA() {
        return a;
    }

    public void setA(int a) {
        try {
            Thread.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.a = a;
    }

    public static void main(String[] args) {

        Dmeo d = new Dmeo();


        d.a = 10;


        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(d.a);
            }
        }).start();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("最终的结果为：" + d.getA());
    }

}
