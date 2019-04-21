package concurrent;

import org.junit.Test;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Desc:
 * Creator: pengweixiang
 * Date: 2019-04-21
 */
public class ReentrantLockTest
{
    //重入锁，提供无参的构造函数，默认是非公平锁。还有个有参的构造函数，选择是否公平
    private ReentrantLock lock = new ReentrantLock(false);

    @Test
    public void test()
    {
        System.out.println("ReentrantLockTest start ...");
        if (lock.tryLock())
        {
            try
            {
                MyTask task = new MyTask("MyTask");
                //task.setDaemon(true);
                task.start();
            }
            finally
            {
                lock.unlock();
            }
        }

        TimeUtils.sleep(5);
    }

    private class MyTask extends Thread
    {
        public MyTask(String name)
        {
            super(name);
        }

        @Override
        public void run()
        {
            System.out.println("Task running. Thread name: " + Thread.currentThread().getName());
            TimeUtils.sleep(1);
            //该线程再次获取锁
            if (lock.tryLock())
            {
                try
                {
                    System.out.println("Reentrant lock ......");
                }
                finally
                {
                    lock.unlock();
                }
            }
        }
    }
}
