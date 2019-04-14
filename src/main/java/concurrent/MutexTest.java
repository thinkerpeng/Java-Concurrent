package concurrent;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Desc:
 * Creator: pengweixiang
 * Date: 2019-04-14
 */
public class MutexTest
{
    private static Mutex lock = new Mutex();

    public static void main(String[] args)
    {
        MutexTest mutexTest = new MutexTest();
        ThreadTask threadTask1 = mutexTest.new ThreadTask("Task 1");
        ThreadTask threadTask2 = mutexTest.new ThreadTask("Task 2");
        ThreadTask threadTask3 = mutexTest.new ThreadTask("Task 3");
        threadTask1.start();
        threadTask2.start();
        threadTask3.start();
    }

    class ThreadTask extends Thread
    {
        private SimpleDateFormat sdf = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");

        public ThreadTask(String name)
        {
            super(name);
        }

        @Override
        public void run()
        {
            lock.lock();
            try
            {
                System.out.println(Thread.currentThread().getName() + ", Time: " + sdf.format(new Date()));
                TimeUtils.sleep(5);
            }
            finally
            {
                lock.unlock();
            }
        }
    }
}
