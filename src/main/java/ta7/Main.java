package ta7;

/**
 * @author chendong
 * @date 2019/4/22 21:25
 */
public class Main {
    public static void main(String[] args) {
        Tmall tmall = new Tmall();
        PushTarget p = new PushTarget(tmall);
        TakeTarget t = new TakeTarget(tmall);
        new Thread(p).start();
        new Thread(p).start();
        new Thread(p).start();
        new Thread(p).start();
        new Thread(p).start();
        new Thread(t).start();
        new Thread(t).start();
        new Thread(t).start();
    }
}
