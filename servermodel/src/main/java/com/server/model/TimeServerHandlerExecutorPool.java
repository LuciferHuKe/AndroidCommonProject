package com.server.model;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by lucifer on 17/3/9.
 *
 * I/O任务线程池
 */

public class TimeServerHandlerExecutorPool {

    private ExecutorService executor;

    public TimeServerHandlerExecutorPool(int maxPoolSize, int queueSize) {

        this.executor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(), maxPoolSize,
                120L, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(queueSize));
    }

    public void executor(Runnable task) {
        this.executor.execute(task);
    }

}
