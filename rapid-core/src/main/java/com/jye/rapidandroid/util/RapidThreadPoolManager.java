package com.jye.rapidandroid.util;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 描述：线程池辅助类，整个应用程序就只有一个线程池去管理线程。
 * 可以设置核心线程数、最大线程数、额外线程空状态生存时间，阻塞队列长度来优化线程池。
 * 下面的数据都是参考Android的AsynTask里的数据。
 * <p>
 * 创建人：jye-ixiaojye@163.com
 */
public final class RapidThreadPoolManager {

    // 线程池核心线程数
    public static int DEFAULT_CORE_POOL_SIZE = 5;

    // 线程池最大线程数
    public static int DEFAULT_MAX_POOL_SIZE = 100;

    // 额外线程空状态生存时间
    public static int DEFAULT_KEEP_ALIVE_TIME = 10000;

    // 阻塞队列。当核心线程都被占用，且阻塞队列已满的情况下，才会开启额外线程。
    private static BlockingQueue<Runnable> sWorkQueue = new ArrayBlockingQueue<Runnable>(10);

    // 线程工厂
    private static ThreadFactory sThreadFactory = new ThreadFactory() {
        private final AtomicInteger integer = new AtomicInteger();

        @Override
        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, "myThreadPool thread:" + integer.getAndIncrement());
        }
    };

    // 线程池
    public static ThreadPoolExecutor sThreadPool = null;

    private RapidThreadPoolManager() {
        throw new UnsupportedOperationException("不能被实例化");
    }

    /**
     * 初始化参数
     *
     * @param corePoolSize  线程池核心线程数
     * @param maxPoolSize   线程池最大线程数
     * @param keepAliveTime 额外线程空状态生存时间
     */
    public static void init(int corePoolSize, int maxPoolSize, int keepAliveTime) {
        sThreadPool = new ThreadPoolExecutor(corePoolSize, maxPoolSize,
                keepAliveTime, TimeUnit.SECONDS, sWorkQueue, sThreadFactory);
    }

    /**
     * 从线程池中抽取线程，执行指定的Runnable对象
     *
     * @param runnable 线程实例
     */
    public static void execute(Runnable runnable) {
        if (runnable == null) {
            return;
        }
        if (sThreadPool == null) {
            init(DEFAULT_CORE_POOL_SIZE, DEFAULT_MAX_POOL_SIZE, DEFAULT_KEEP_ALIVE_TIME);
        }
        sThreadPool.execute(runnable);
    }

    /**
     * 从线程池中移除指定的线程
     *
     * @param runnable 线程实例
     */
    public static void remove(Runnable runnable) {
        if (runnable == null) {
            return;
        }
        if (sThreadPool != null) {
            sThreadPool.remove(runnable);
        }
    }

}