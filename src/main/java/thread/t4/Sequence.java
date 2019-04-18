package thread.t4;

/**
 * @author chendong
 * @date 2019/4/11 22:27
 */
public class Sequence {

    private static int value;

    public synchronized int getNext(){
        return value++;
    }
    public static synchronized int getPrevious(){
        return value--;
    }

    public int xx(){
        synchronized (this){
            if(value > 0){
                return value;
            }else{
                return -1;
            }
        }

    }
    public static void main(String[] args) {
        /*Sequence s = new Sequence();
        while (true){
            System.out.println(s.getNext());
        }*/
        Sequence s = new Sequence();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    System.out.println(Thread.currentThread().getName() + " " + s.getNext());
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    System.out.println(Thread.currentThread().getName() + " " + s.getNext());
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    System.out.println(Thread.currentThread().getName() + " " + s.getNext());
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

}
