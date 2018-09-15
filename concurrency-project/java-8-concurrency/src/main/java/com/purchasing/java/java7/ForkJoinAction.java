package com.purchasing.java.java7;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.atomic.LongAdder;

/**
 * Fork/Join示例
 * @author zhangwei
 * @createTime 2018/9/10
 */
public class ForkJoinAction {

    public static void main(String[] args) {


        ForkJoinPool forkJoinPool = new ForkJoinPool();

        List<Integer> numbers = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        //RecursiveAction递归操作
        /**
         * 这个方法体现的是任务拆分思想，通过递归的方式实现
         */
        LongAdder longAdder = new LongAdder();
        forkJoinPool.invoke(new AddTask(numbers,longAdder));
        forkJoinPool.shutdown();
        System.out.println(longAdder);
    }

    private static class AddTask extends RecursiveAction {

        private final   List<Integer> numbers;
        private final LongAdder longAdder;

        public AddTask(List<Integer> numbers,LongAdder longAdder) {
            this.numbers = numbers;
            this.longAdder= longAdder;
        }

        @Override
        protected void compute() {
            int size = numbers.size();
            if(size>1){
                //二分部分数
                int parts = size/2;
                //左半部
                List<Integer> leftPart = numbers.subList(0,parts);
                AddTask leftTask = new AddTask(leftPart,longAdder);
                //右半部
                List<Integer> rightPart = numbers.subList(parts,size);
                AddTask rightTask = new AddTask(rightPart,longAdder);
                System.out.println("=======");
                invokeAll(leftTask,rightTask);
            }else {
                if(size == 0){
                    return;
                }
                Integer value = numbers.get(0);
                longAdder.add(value.longValue());
            }
        }
    }
}
