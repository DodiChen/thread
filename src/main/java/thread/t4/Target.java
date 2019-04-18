package thread.t4;

/**
 * @author chendong
 * @date 2019/4/11 21:08
 */
public class Target implements Runnable{
    @Override
    public void run() {
        while (true){
            System.out.println(Thread.currentThread().getName() + "...");
        }

    }
}
