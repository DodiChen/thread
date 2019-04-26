package tc9;

/**
 * @author chendong
 * @date 2019/4/22 21:23
 */
public class TakeTarget implements Runnable {
    private Shop tmall;
    public TakeTarget(Shop tmall){
        this.tmall = tmall;
    }
    @Override
    public void run() {
        while (true){
            tmall.take();
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
