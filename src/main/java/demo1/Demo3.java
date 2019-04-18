package demo1;

/**
 * @author chendong
 * @date 2019/4/10 22:56
 */
public class Demo3 {
    public static void main(String[] args) {
        new Thread(){
            @Override
            public void run() {
                System.out.println("thread start...");
            }
        }.start();

        new Thread(new Runnable() {
            public void run() {
                System.out.println("thread start...");
            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                System.out.println("runnable");
            }
        }){
            @Override
            public void run() {
                System.out.println("sub");
            }
        }.start();
    }
}
