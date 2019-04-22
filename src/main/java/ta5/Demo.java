package ta5;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Demo {

    private Map<String, Object> map = new HashMap<String, Object>();

    private ReadWriteLock rwLock = new ReentrantReadWriteLock();

    private Lock r = rwLock.readLock();
    private Lock w = rwLock.writeLock();

    private volatile boolean isUpdate;

    public void readWrite(){

        // 要读isUpdate，此处要加读锁，保证isUpdate可以拿到最新的值

        r.lock();
        if(isUpdate){
            r.unlock(); // 因为与读锁互斥，所以此处释放

            w.lock();
            map.put("xxx", "xxx");
            r.lock(); // 锁的降级
            w.unlock();
        }

        Object obj = map.get("xxx");
        System.out.println(obj);
        r.unlock();
    }


    public Object get(String key){
        r.lock();
        System.out.println(Thread.currentThread().getName() + " " + "读操作在执行...");
        try{
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return map.get(key);
        }finally {
            r.unlock();
            System.out.println(Thread.currentThread().getName() + " " + "读操作执行完毕...");
        }

    }

    public void put(String key, Object value){
        w.lock();
        System.out.println(Thread.currentThread().getName() + " " + "写操作在执行...");
        try {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            map.put(key, value);
        }finally {
            w.unlock();
            System.out.println(Thread.currentThread().getName() + " " + "写操作执行完毕...");
        }
    }



}
