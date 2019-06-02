package threadpool;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Desc:
 * Creator: pengweixiang
 * Date: 2019-06-02
 */
public class ScheduledExecutorServiceTest
{
    private static SimpleDateFormat sdf = new SimpleDateFormat("ss");

    public static void main(String[] args)
    {
        Runnable task = new ScheduledExecutorServiceTest.Task();
        Runnable task1 = new ScheduledExecutorServiceTest.Task1();

        System.out.println("current time " + sdf.format(new Date()));

        //单线程的定时任务
        ScheduledExecutorService scheduleTask = Executors.newSingleThreadScheduledExecutor();
        //任务开始时，则开始计时等待周期的
        //周期=period
        scheduleTask.scheduleAtFixedRate(task, 0, 3, TimeUnit.SECONDS);
        //任务结束时，才开始计算等待周期的
        //周期=period+任务消耗时间
        scheduleTask.scheduleWithFixedDelay(task1, 0, 3, TimeUnit.SECONDS);
    }

    private static class Task implements Runnable
    {
        @Override
        public void run()
        {
            System.out.println("task is running..." + sdf.format(new Date()));
            try
            {
                TimeUnit.SECONDS.sleep(1);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }

    private static class Task1 implements Runnable
    {
        @Override
        public void run()
        {
            System.out.println("task1 is running..."+ sdf.format(new Date()));
            try
            {
                TimeUnit.SECONDS.sleep(5);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
}
