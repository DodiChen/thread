package demo1;

import java.util.Arrays;
import java.util.List;

/**
 * @author chendong
 * @date 2019/4/11 20:24
 */
public class Demo7 {

    public int add(List<Integer> values){
        values.parallelStream().forEach(System.out :: println);
        return values.parallelStream().mapToInt(a -> a).sum();
    }

    public static void main(String[] args) {
        List<Integer> values = Arrays.asList(10,20,30,40);
        int res = new Demo7().add(values);
        System.out.println("计算的结果为： " + res);
    }

}
