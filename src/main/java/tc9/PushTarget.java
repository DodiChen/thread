package tc9;

/**
 * @author chendong
 * @date 2019/4/22 21:22
 */
public class PushTarget implements Runnable{
    private Shop tmall;
    public PushTarget(Shop tmall){
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
