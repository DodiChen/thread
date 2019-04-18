package demo1;

/**
 * @author chendong
 * @date 2019/4/8 21:45
 */
public class Demo1 extends Thread {

    public Demo1(String name){
        super(name);
    }

    @Override
    public void run() {
        while(!interrupted()){
            System.out.println(getName() + "线程执行了...");
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) throws InterruptedException {
        Demo1 demo1 = new Demo1("first");
        Demo1 demo2 = new Demo1("second");
        /*demo1.setDaemon(true);
        demo2.setDaemon(true);*/
        demo1.start();
        demo2.start();

        demo1.interrupt();
    }
}
