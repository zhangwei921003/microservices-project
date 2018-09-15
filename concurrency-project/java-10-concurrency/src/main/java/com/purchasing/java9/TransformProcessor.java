package com.purchasing.java9;

import java.util.concurrent.Executor;
import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;
import java.util.function.Function;

/**
 * @author zhangwei
 * @createTime 2018/9/11
 */
public class TransformProcessor <S,T> extends SubmissionPublisher<T> implements Flow.Processor<S,T> {

    final Function<? super S, ? extends T> function;
    Flow.Subscription subscription;

    TransformProcessor(Executor executor, int maxBufferCapacity,
                       Function<? super S, ? extends T> function) {
      super(executor, maxBufferCapacity);
     this.function = function;
    }
    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        (this.subscription = subscription).request(1);
    }

    @Override
    public void onNext(S item) {
        subscription.request(1);
        submit(function.apply(item));
    }

    @Override
    public void onError(Throwable throwable) {
        closeExceptionally(throwable);
    }

    @Override
    public void onComplete() {

    }
}
