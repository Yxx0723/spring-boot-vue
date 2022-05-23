package cn.yyx.demo01;

/**
 * @author 杨亚轩
 * @version 1.0
 * @date 2022/5/23 10:51
 */
public class demo03 implements Runnable {

    private String name;

    private demo03(String name){
        this.name = name;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(name+"在运行,i="+i);
        }
    }


    public static void main(String[] args) {
        demo03 a = new demo03("线程A");
        demo03 b = new demo03("线程B");

        Thread threadA = new Thread(a);
        Thread threadB = new Thread(b);

        threadA.start();
        threadB.start();
    }
}
