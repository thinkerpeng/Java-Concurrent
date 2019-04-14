package concurrent;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Desc: 等待通知机制
 * Creator: pengweixiang
 * Date: 2019-03-24
 */
public class WaitNotifyTest
{
    private final static Object lock = new Object();
    private static AtomicBoolean flag = new AtomicBoolean(true);

    public static void main(String[] args)
    {
        new Thread(new Wait(), "Wait thread").start();
        TimeUtils.sleep(1);
        new Thread(new Notify(), "Notify thread").start();
    }

    private static class Wait implements Runnable
    {
        @Override
        public void run()
        {
            synchronized (lock)
            {
                while (flag.get())
                {
                    try
                    {
                        System.out.println("Current Thread ID: " + Thread.currentThread().getId() + ", Wait waiting ...");
                        lock.wait();
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
                System.out.println("Current Thread ID: " + Thread.currentThread().getId() + ", Wait end ...");
            }
        }
    }

    private static class Notify implements Runnable
    {
        @Override
        public void run()
        {
            synchronized (lock)
            {
                System.out.println("Current Thread ID: " + Thread.currentThread().getId() + ", Notify running ...");
                TimeUtils.sleep(3);
                flag.compareAndSet(true, false);
                lock.notifyAll();
            }

            TimeUtils.sleep(2);

            //再次加锁
            synchronized (lock)
            {
                System.out.println("Current Thread ID: " + Thread.currentThread().getId() + ", Notify end ...");
            }
        }
    }
}
