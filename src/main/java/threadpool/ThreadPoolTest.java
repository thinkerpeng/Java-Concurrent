package threadpool;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Desc: ThreadPoolExecutor测试类
 * Creator: pengweixiang
 * Date: 2019-05-28
 */
public class ThreadPoolTest
{
    private static ThreadPoolExecutor computers = new ThreadPoolExecutor(
            10,
            10 * 10,
            60,
            TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>());

    private static CountDownLatch count = new CountDownLatch(10);

    public static void main(String[] args)
    {
        for (int i = 0; i < 10; i++)
        {
            int finalI = i;
            computers.execute(() ->
            {
                try
                {
                    System.out.println("task " + finalI);
                    TimeUnit.SECONDS.sleep(3);
                    count.countDown();
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            });
        }

        try
        {
            System.out.println("Waiting ......");
            count.await();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        computers.shutdown();
    }
}
