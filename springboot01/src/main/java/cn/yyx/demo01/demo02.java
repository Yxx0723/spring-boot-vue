package cn.yyx.demo01;

/**
 * @author 杨亚轩
 * @version 1.0
 * @date 2022/5/23 10:51
 */
public class demo02 extends Thread {

    private String name;

    private demo02(String name){
        this.name = name;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(name+"在运行,i="+i);
        }
    }


    public static void main(String[] args) {
        new demo02("A").start();
        new demo02("B").start();
    }
}
