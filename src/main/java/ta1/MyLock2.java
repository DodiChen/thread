package ta1;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author chendong
 * @date 2019/4/18 23:07
 */
public class MyLock2 implements Lock {

    private Sync sync = new Sync();

    @Override
    public void lock() {
        sync.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1, unit.toNanos(time));
    }

    @Override
    public void unlock() {
        sync.release(1);
    }

    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }

    private class Sync extends AbstractQueuedSynchronizer{
        /*
        锁不可重入
         */
        /*
        @Override
        protected boolean tryAcquire(int arg) {
            // 如果第一个线程进来，可以拿到锁，因此可以返回true
            // 如果第二个线程进来，拿不到锁，返回false
            // 如何判断是第一个线程进来还是其他线程进来？
            int state = getState();
            if(state == 0){
                if(compareAndSetState(0, arg)){
                    // 将当前线程设置进来
                   setExclusiveOwnerThread(Thread.currentThread());
                    return true;
                }
            }
            return false;
        }

        @Override
        protected boolean tryRelease(int arg) {
            // 锁的获取和释放肯定是一一对应的，那么调用和此方法的线程，一定是当前线程
            // 所以如果当前线程，就可以直接抛出异常
            if(Thread.currentThread() != getExclusiveOwnerThread()){
                throw new RuntimeException();
            }

            int state = getState() - arg;
            boolean flag = false;
            if(state == 0){
                setExclusiveOwnerThread(null); // 将当前线程置为空
                flag = true;
            }
            setState(state);
            return flag;
        }
        */

        /*
        锁可重入
         */
        // 如果第一个线程进来，可以拿到锁，因此我们可以返回true
        // 如果第二个线程进来，则拿不到锁，返回false，但有种特例，如果当前进来的线程和当前保存的线程是同一个线程
        // 则可以拿到锁，但是有代价，要更新状态值
        @Override
        protected boolean tryAcquire(int arg) {
            int state = getState();
            Thread t = Thread.currentThread();
            if(state == 0){
                if(compareAndSetState(0, arg)){
                    setExclusiveOwnerThread(t);
                    return true;
                }
            }else if(getExclusiveOwnerThread() == t){
                // 只有当前线程可以进来，所以此处没有线程安全性问题
                setState(state + 1);
                return true;
            }
            return false;
        }

        // 锁的获取和释放肯定是一一对应的，那么调用和此方法的线程，一定是当前线程
        // 所以如果当前线程，就可以直接抛出异常
        @Override
        protected boolean tryRelease(int arg) {


            if(Thread.currentThread() != getExclusiveOwnerThread()){
                throw new RuntimeException();
            }

            int state = getState() - arg;
            boolean flag = false;
            if(state == 0){
                setExclusiveOwnerThread(null);
                flag = true;
            }
            setState(state);

            return flag;
        }

        Condition newCondition(){
            return new ConditionObject();
        }
    }

}
