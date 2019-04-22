package ta5;

public class Main {

    public static void main(String[] args) {
        Demo d = new Demo();

        // 都是写操作
        /*new Thread(new Runnable() {
            @Override
            public void run() {
                d.put("key1", "value1");
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                d.put("key2", "value2");
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                d.put("key3", "value3");
            }
        }).start();*/


        // 一个读，一个写
        /*new Thread(new Runnable() {
            @Override
            public void run() {
                d.put("key1", "value1");
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                d.get("key1");
            }
        }).start();*/

        // 多个读
        d.put("key1", "values");

        new Thread(new Runnable() {
            @Override
            public void run() {
                d.get("key1");
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                d.get("key1");
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                d.get("key1");
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                d.get("key1");
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                d.get("key1");
            }
        }).start();

    }

}
