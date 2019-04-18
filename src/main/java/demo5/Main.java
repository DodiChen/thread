package demo5;

import com.sun.org.apache.bcel.internal.generic.SIPUSH;

import java.security.Signature;

/**
 * @author chendong
 * @date 2019/4/14 14:07
 */
public class Main {
    public static void main(String[] args) {
        Singleton2 s1 = Singleton2.getInstance();

        Singleton2 s2 = Singleton2.getInstance();

        Singleton2 s3 = Singleton2.getInstance();

        System.out.println(s1.hashCode());
        System.out.println(s2.hashCode());
        System.out.println(s3.hashCode());


    }
}
