package concurrent;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.locks.Lock;

/**
 * Desc: TwinsLock测试类
 * Creator: pengweixiang
 * Date: 2019-04-14
 */
public class TwinsLockTest
{
    final Lock lock = new TwinsLock();

    @Test
    public void test()
    {
        System.out.println("TwinsLock start ...");

        class Worker extends Thread
        {
            private Worker(String name)
            {
                super(name);
            }

            @Override
            public void run()
            {
                if (lock.tryLock())
                {
                    try
                    {
                        TimeUtils.sleep(1);
                        System.out.println(Thread.currentThread().getName());
                    }
                    finally
                    {
                        lock.unlock();
                    }
                }
            }
        }

        for (int i = 10; i > 0; i--)
        {
            Worker worker = new Worker("worker" + i);
            worker.setDaemon(true);
            worker.start();
        }

        TimeUtils.sleep(10);
    }
}
