package com.purchasing.java.before5;

/**
 * Java  Thread / Runnable 编程模式（Java5之前）
 * 实现的局限性
 * 1）缺少线程管理的原生支持（线程池）
 * 2）缺少“锁” API 例如synchronized关键字
 * 3）缺少执行完成的原生支持  需要知道线程是否执行完成，{@link CompletedRunnableMain}
 * 4）执行结果获取困难
 * 5）Double Check  Locking 不确定性
 * @author zhangwei
 * @createTime 2018/9/10
 */
public class ThreadMain {
    public static void main(String[] args) {

        boolean finished  = false;//通过标记无法实现

        /**
         * 子线程
         */
        Thread thread = new Thread(new Runnable() {
            @Override
            public synchronized void run() {
                System.out.printf("[ThreadSub : %S ] Starting ...... \n"+"Hello,World" ,Thread.currentThread().getName());

            }
        },"threadDemo");

        thread.start();
        //这行代码可能会先执行，
        //重排序？？
        System.out.printf("[Thread : %S ] Starting ...... \n",Thread.currentThread().getName());
    }
}
