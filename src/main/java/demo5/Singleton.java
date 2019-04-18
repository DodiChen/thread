package demo5;

/**
 * @author chendong
 * @date 2019/4/14 14:05
 */
public class Singleton {

    // 私有化构造方法
    private Singleton(){}

    // 饿汉式
    private static Singleton instance = new Singleton();

    public static Singleton getInstance(){
        return instance;
    }
}
