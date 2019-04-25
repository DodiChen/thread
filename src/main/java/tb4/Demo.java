package tb4;

import java.util.ArrayList;
import java.util.List;

public class Demo {

    private int[] nums;

    public Demo(int line){
        nums = new int[line];
    }

    public void calc(String line, int index){

        String[] nus = line.split(","); // 切分出每个值
        int total = 0;
        for(String num : nus){
            total = total + Integer.valueOf(num);
        }
        nums[index] = total; // 把计算结果方法数组中指定的位置
        System.out.println(Thread.currentThread().getName() + " 开始执行计算任务..." + line + " 结果为 " + total);
    }

    public void sum(){
        System.out.println("汇总线程开始执行。。。");
        int total = 0;
        for(int i = 0; i < nums.length; i++){
            total += nums[i];
        }
        System.out.println("最终的结果是: " + total);
    }

    public static void main(String[] args) {
        List<String> contents = new ArrayList<>();
        contents.add("1,2,3,4,5,6,7,8,9,10");
        contents.add("11,12,13,14,15,16,17,18,19,20");
        contents.add("21,22,23,24,25,26,27,28,29,30");

        int lineCount = contents.size();
        Demo d = new Demo(lineCount);

        for(int i = 0; i < lineCount; i++){
            final int j = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    d.calc(contents.get(j), j);
                }
            }).start();
        }

        while(Thread.activeCount() > 1){
            //System.out.println(Thread.activeCount());

        }
        d.sum();
    }
}
