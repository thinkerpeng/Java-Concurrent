package threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Desc: ThreadPoolExecutor测试类
 * Creator: pengweixiang
 * Date: 2019-06-02
 */
public class ThreadPoolExecutorTest
{
    public static void main(String[] args)
    {
        Runnable task = new Task();

        //单个线程
        ExecutorService service = Executors.newSingleThreadExecutor();
        service.submit(task);
        service.shutdown();

        //固定线程数的线程池.
        //限制当前线程数量的场景, 适用负载均衡较重的服务器
        service = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++)
        {
            service.submit(task);
        }
        service.shutdown();

        //大小无界限的线程池
        //适用执行很多短期的异步任务
        service = Executors.newCachedThreadPool();
        for (int i = 0; i < 20; i++)
        {
            service.submit(task);
        }
        service.shutdown();
    }

    private static class Task implements Runnable
    {
        @Override
        public void run()
        {
            System.out.println("task is running..." + " thread name: " + Thread.currentThread().getName());
        }
    }
}
