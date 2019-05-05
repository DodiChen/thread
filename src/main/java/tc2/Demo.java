package tc2;

public class Demo {

    private int a ;

    private int b ;

    private int c ;

    public void a(){
        //读后写
        //写后写
        //写后读
        a = 1;
        b = 2; // 写操作
        c = a; // 读操作
        b = c + a;
        System.out.println(b);
    }

    public static void main(String[] args) {
        new Demo().a();
    }

}
