package ta3;


import org.omg.PortableServer.THREAD_POLICY_ID;

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
