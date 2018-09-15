package com.purchasing.java.before5;

/**
 * 可执行完成的{@link Runnable}
 * @author zhangwei
 * @createTime 2018/9/10
 */
public class CompletedRunnableMain {
    public static void main(String[] args) throws InterruptedException {

        CompletableRunnable runnable = new CompletableRunnable();
        Thread thread = new Thread(runnable,"completedThread");

        thread.start();
        //等待线程执行结束，串性操作 Waits for this thread to die.
        thread.join();

        System.out.printf("[Thread : %S ] Starting ...... \n",Thread.currentThread().getName());
        System.out.printf("[Thread : %S ] Thread Complete  [Status : %S] \n",Thread.currentThread().getName(),runnable.isComplete());
    }

    /**
     * 内部类必须是 static??
     * 控制并发和同步，这个是很难通过简单的代码就能够实现的
     */
    private static class CompletableRunnable implements Runnable{

        /**
         * volatile 解决可见性，主线程对子线程内存一致性保证
         */
        volatile boolean complete = false;

        @Override
        public void run() {
            System.out.printf("[ThreadSub : %S ] Starting ...... \n"+"Hello,World" ,Thread.currentThread().getName());
            complete = true;
        }

        public boolean isComplete() {
            return complete;
        }
    }
}
