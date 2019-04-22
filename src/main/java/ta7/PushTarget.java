package ta7;

/**
 * @author chendong
 * @date 2019/4/22 21:22
 */
public class PushTarget implements Runnable{
    private Tmall tmall;
    public PushTarget(Tmall tmall){
        this.tmall = tmall;
    }
    @Override
    public void run() {
        while (true){
            tmall.push();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
