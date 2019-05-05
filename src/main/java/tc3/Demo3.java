package tc3;

public class Demo3 {

    public void a(){
        System.out.println("a"); // 1 启动另外一个线程的线程方法
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("b"); // 2
            }
        }).start();
    }

    public static void main(String[] args) {
        new Demo3().a();
    }

}
