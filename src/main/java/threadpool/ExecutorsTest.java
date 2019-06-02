package threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Desc:
 * Creator: pengweixiang
 * Date: 2019-06-01
 */
public class ExecutorsTest
{
    public static void main(String[] args)
    {
        Executors.callable(()->
        {
            System.out.println("hello world.");
        });

        try
        {
            TimeUnit.SECONDS.sleep(10);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
