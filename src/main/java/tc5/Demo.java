package tc5;

/**
 * 程序次序规则
 *  1 happens-before 2 , 2 happens-before 3, 3 happens-before 4, 4 happens-before 5
 * volatile 规则
 *  2 happens-before 3
 * 传递性
 *  1 happens-before 2, 2 happens-before 3, 3 happens-before 4, 4 happens-before 5
 */
public class Demo {

    private volatile boolean flag;
    private volatile int a;

    public void writer(){
        a = 1; // 1
        flag = true; //2
    }

    public void reader(){
        if(flag){// 3
            int b = a + 1;//4
            System.out.println(b);//5
        }

    }

}
