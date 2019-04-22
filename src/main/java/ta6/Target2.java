package ta6;

/**
 * @author chendong
 * @date 2019/4/22 20:55
 */
public class Target2 implements Runnable {

    private Demo3 demo;

    public Target2(Demo3 demo3) {
        demo = demo3;
    }

    @Override
    public void run() {
        demo.get();
    }
}
