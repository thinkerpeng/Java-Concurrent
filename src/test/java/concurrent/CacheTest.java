package concurrent;

import org.junit.Test;

import java.sql.Time;

/**
 * Desc: 读写锁Cache测试类
 * Creator: pengweixiang
 * Date: 2019-04-21
 */
public class CacheTest
{
    @Test
    public void test()
    {
        for (int i = 0; i < 2; i++)
        {
            String threadName = "thread" + i;
            ReadTask readTask = new ReadTask(threadName);
            WriteTask writeTask = new WriteTask(threadName);
            readTask.setDaemon(true);
            writeTask.setDaemon(true);
            writeTask.start();
            readTask.start();
        }

        TimeUtils.sleep(20);
    }

    private static class ReadTask extends Thread
    {
        private String threadName;

        public ReadTask(String name)
        {
            super(name);
            threadName = name;
        }

        @Override
        public void run()
        {
            while (true)
            {
                String value = (String) Cache.get(threadName);
                System.out.println(Thread.currentThread().getName() + ", read value: " + value);
                TimeUtils.sleep(1);
            }
        }
    }

    private static class WriteTask extends Thread
    {
        private String threadName;

        public WriteTask(String name)
        {
            super(name);
            threadName = name;
        }

        @Override
        public void run()
        {
            while (true)
            {
                String value = (String) Cache.put(threadName, TimeUtils.getCurrentTime());
                System.out.println(Thread.currentThread().getName() + ", write value: " + value);
                TimeUtils.sleep(1);
            }
        }
    }
}
