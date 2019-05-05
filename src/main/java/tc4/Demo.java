package tc4;

/**
 * 程序次序规则
 *  1 happens-before 2 , 2 happens-before 3, 4 happens-before 5, 5 happens-before 6
 * 监视器规则
 *  3 happens-before 4
 * 传递性
 *  1 happens-before 2, 2 happens-before 3, 3 happens-before 4, 4 happens-before 5, 5 happens-before 6
 */
public class Demo {

    private int value;

    public synchronized void a(){ // 1 获取锁
        value++; // 2
    } // 3 释放锁

    public synchronized void b(){ // 4 获取锁
        int a = value; // 5
        // 处理其他操作
    } // 6 释放锁

}
