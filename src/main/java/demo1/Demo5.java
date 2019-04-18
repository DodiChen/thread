package demo1;

import javax.jws.soap.SOAPBinding;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author chendong
 * @date 2019/4/10 23:09
 */
public class Demo5 {

    public static void main(String[] args) {
        Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("timetask is run");
            }
        }, 0, 1000);

    }
}
