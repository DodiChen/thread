package tb7;

import java.util.concurrent.Exchanger;

public class Demo {

    public void a(Exchanger<String> exchanger){
        System.out.println("a 方法执行...");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String res = "12345";

        try {
            System.out.println("等待对比结果...");
            String value = exchanger.exchange(res);
            System.out.println("开始进行比对...");
            System.out.println("对比结果为: " + value.equals(res));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void b(Exchanger<String> exchanger){
        System.out.println("b 方法开始执行...");
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String res = "12345";
        try {
            String value = exchanger.exchange(res);
            System.out.println("开始进行比对...");
            System.out.println("比对结果为: " + value.equals(res));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Demo d = new Demo();
        Exchanger<String> exch = new Exchanger<String>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                d.a(exch);
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                d.b(exch);
            }
        }).start();
    }

}
