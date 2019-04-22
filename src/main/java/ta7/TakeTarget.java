package ta7;

/**
 * @author chendong
 * @date 2019/4/22 21:23
 */
public class TakeTarget implements Runnable {
    private Tmall tmall;
    public TakeTarget(Tmall tmall){
        this.tmall = tmall;
    }
    @Override
    public void run() {
        while (true){
            tmall.take();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
