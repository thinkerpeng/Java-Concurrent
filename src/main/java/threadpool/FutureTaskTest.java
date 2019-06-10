package threadpool;

import java.util.concurrent.*;

/**
 * Desc:
 * Creator: pengweixiang
 * Date: 2019-06-02
 */
public class FutureTaskTest
{
    public static void main(String[] args)
    {
        ExecutorService taskService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        FutureTask<Integer> futureTask = new FutureTask<Integer>(new Task());
        taskService.submit(futureTask);
        try
        {
            Integer sum = futureTask.get();
            System.out.println("sum : " + sum);
        }
        catch (InterruptedException | ExecutionException e)
        {
            e.printStackTrace();
        }

        taskService.shutdown();
    }

    static class Task implements Callable<Integer>
    {
        @Override
        public Integer call() throws Exception
        {
            System.out.println("子线程在进行计算");
            Thread.sleep(3000);
            int sum = 0;
            for (int i = 0; i < 100; i++)
                sum += i;
            return sum;
        }
    }
}
