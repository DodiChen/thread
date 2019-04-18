package demo5;

/**
 * @author chendong
 * @date 2019/4/14 14:13
 */
public class Singleton2{

    private Singleton2(){}

    private static volatile Singleton2 instance;

    /**
     * 偏向锁
     *
     * @return
     */
    public static synchronized Singleton2 getInstance(){
        // 轻量级锁，自旋，是会浪费CPU资源的
        // 所以在这边加了 synchronized 其实无法提高性能
        if(instance == null){
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            instance = new Singleton2();
        }
        return instance;
    }

    public static synchronized Singleton2 getInstance2(){
        // 双重检查加锁制
        if(instance == null){
          synchronized (Singleton2.class){
              if(instance == null){
                  instance = new Singleton2(); // 指令重排序，为了提高执行的性能，
                                                // 会对指令进行优化，可能会把后面的代码放到前面执行，
                                                // 因此这代码其实还是线程不安全的
                  // 首先申请一片内存空间 1
                  // 在这块空间里面实例化对象 2
                  // instance 的引用指向这一块空间地址 3
                  // 可能执行的顺序是 1 2 3， 也可能是 1 3 2
                  // 因此 instance 不一定会是null
                  // 因此，需要使用volatile，可以减少虚拟机优化，可以减少之类重排序
                  // 避免出现指令重排序
              }
          }
        }
        return instance;
    }
}
