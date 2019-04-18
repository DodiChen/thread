/**
 * @author chendong
 * @date 2019/4/8 21:17
 */
public class NewThread implements Runnable {
    public synchronized void run() {
        while(true){
            System.out.println("自定义的线程启动了");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            /*try {
                // 超时等待状态，时间到了会自动唤醒
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
        }

    }

    public static void main(String[] args) {

        NewThread n = new NewThread();

        // 创建线程，并制定线程任务
        // 初始化状态
        Thread thread = new Thread(n);

        // 就绪状态，竞争cpu资源
        thread.start(); // 启动线程
        while(true){
            synchronized (n){
                System.out.println("主线程执行了");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                n.notifyAll();
            }

        }
    }
}
