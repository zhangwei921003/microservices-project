package com.purchasing.legacy;

import java.util.Observable;

/**
 * 观察者模式
 * @author zhangwei
 * @createTime 2018/9/11
 * {@link java.util.Observable}
 *{@link java.util.Observer}
 */
public class ObserverDemo {

    public static void main(String[] args) {

        /**
         * 问题：
         * 1）Vector作为底层存储（全线程安全）
         * 2）没有实现泛型
         * 3）倒叙 -> 同步执行  -> 阻塞
         *
         * for (int i = arrLocal.length-1; i>=0; i--)
         *             ((Observer)arrLocal[i]).update(this, arg);
         * 由代码可知 倒叙执行，倒叙则意味是同步执行
         *
         * 所以从Java9开始Observer不建议使用
         */
        MyObserver observable = new MyObserver();//构建可观察对象

        observable.addObserver((o,value) ->{
            System.out.printf("第一个观察者观察到的数值 :%s \n",value);
        });
        observable.addObserver((o,value) ->{
            System.out.printf("第二个观察者观察到的数值 :%s \n",value);
        });
        observable.addObserver((o,value) ->{
            System.out.printf("第三个观察者观察到的数值 :%s \n",value);
        });
        /**
         * 执行结果可知是倒叙的
         * 第三个观察者观察到的数值 :123456
         * 第二个观察者观察到的数值 :123456
         * 第一个观察者观察到的数值 :123456
         */

        //设置变化值
        observable.setChanged();
        //通知所有的观察者
        observable.notifyObservers(123456);

    }

    private static class MyObserver extends Observable{

        /**
         * 通过继承的方式设置变化值，通知到各个观察者
         * {@link Observable#setChanged()} 这个方法修饰符是protected，普通类不能直接访问
         *
         */
        public  void setChanged() {
            super.setChanged();
        }
    }
}
