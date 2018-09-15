package com.purchasing.java9;

import java.util.concurrent.*;
import java.util.function.Supplier;

/**
 * @author zhangwei
 * @createTime 2018/9/11
 */
public class PeriodicPublisher<T> extends SubmissionPublisher<T> {

    final ScheduledFuture<?> periodicTask;
    final ScheduledExecutorService scheduler;

    PeriodicPublisher(Executor executor, int maxBufferCapacity,
                      Supplier<? extends T> supplier,
                      long period, TimeUnit unit) {
        super(executor, maxBufferCapacity);
        scheduler = new ScheduledThreadPoolExecutor(1);
        periodicTask = scheduler.scheduleAtFixedRate(
                () -> submit(supplier.get()), 0, period, unit);
    }

    public void close() {
        periodicTask.cancel(false);
        scheduler.shutdown();
        super.close();
    }

    public static void main(String[] args) {

    }
}
