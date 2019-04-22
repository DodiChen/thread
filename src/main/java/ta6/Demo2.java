package ta6;

/**
 * @author chendong
 * @date 2019/4/22 20:53
 */
public class Demo2 {
    private volatile int signal;

    public void set(int value){
        this.signal = value;
    }

    public int get(){
        return signal;
    }

    public static void main(String[] args) {

        Demo demo = new Demo();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (demo){
                    System.out.println("修改状态的线程执行...");
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    demo.set(1);
                    demo.notify();
                    System.out.println("状态值修改成功");
                }
            }
        }).start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (demo){
                    // 等待signal 为1， 开始执行，否则不能执行
                    while(demo.get() != 1){
                        try {
                            demo.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    // 当信号为1 时，执行代码
                    System.out.println("模拟代码的执行...");
                }
            }
        }).start();
    }

}
