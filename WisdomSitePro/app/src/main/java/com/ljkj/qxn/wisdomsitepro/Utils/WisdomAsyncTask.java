package com.ljkj.qxn.wisdomsitepro.Utils;

import com.ljkj.qxn.wisdomsitepro.WApplication;
import com.ljkj.qxn.wisdomsitepro.data.AsyncResult;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 类描述：异步任务
 * 创建人：lxx
 * 创建时间：2018/4/17 16:00
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public abstract class WisdomAsyncTask<T> implements Callable<AsyncResult<T>> {

    private static final ThreadFactory threadFactory = new ThreadFactory() {
        private final AtomicInteger mCount = new AtomicInteger(1);

        public Thread newThread(Runnable r) {
            return new Thread(r, "WisdomAsyncTask #" + mCount.getAndIncrement());
        }
    };

    private static final ExecutorService THREAD_POOL_EXECUTOR;

    static {
        THREAD_POOL_EXECUTOR = Executors.newSingleThreadExecutor(threadFactory);
    }

    private AsyncResult<T> mResult = new AsyncResult<>();

    @Override
    public AsyncResult<T> call() throws Exception {
        runOnBackground(mResult);
        post();
        return mResult;
    }

    /**
     * 开始执行任务
     */
    public Future<AsyncResult<T>> execute() {
        return THREAD_POOL_EXECUTOR.submit(this);
    }

    private void post() {
        WApplication.getMainHandler().post(new Runnable() {
            @Override
            public void run() {
                runOnUIThread(mResult);
            }
        });
    }

    protected abstract void runOnBackground(AsyncResult<T> asyncResult);

    protected abstract void runOnUIThread(AsyncResult<T> asyncResult);
}
