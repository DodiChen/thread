package ta6;

/**
 * @author chendong
 * @date 2019/4/22 20:38
 */
public class Demo {

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
                System.out.println("修改状态的线程执行...");
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                demo.set(1);
                notify();
                System.out.println("状态值修改成功");
            }
        }).start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                // 等待signal 为1， 开始执行，否则不能执行

                while(demo.get() != 1){
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                // 当信号为1 时，执行代码

                System.out.println("模拟代码的执行...");
            }
        }).start();
    }

}
