package tc6;

public class Demo {

    private int a;
    private final int b;

    // 实例被初始化的过程中加载
    public Demo(){ // 1
        //b = 10;
        b = 20; // 2
        a = 10; // 3
    } // 4
    {
        //b = 10;
    }

    // 类初始化的时候加载
    // 无法初始化b
    static{
        //b = 10;
    }

    private Demo demo;

    public void w(){ // 5
        demo = new Demo(); // 6
    }

    public void r(){
        Demo d = demo; // 7
        int temp1 = d.a;
        int temp2 = d.b;
    }

}
