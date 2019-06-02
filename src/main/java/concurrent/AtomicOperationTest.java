package concurrent;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Desc:
 * Creator: pengweixiang
 * Date: 2019-05-27
 */
public class AtomicOperationTest
{
    private static AtomicInteger age = new AtomicInteger(25);
    private static AtomicBoolean init = new AtomicBoolean(false);
    private static AtomicLong distance = new AtomicLong(1024);

    public static void main(String[] args)
    {
        System.out.println("25 : " + age.getAndAdd(5));
        System.out.println("30 : " + age.get());
        System.out.println("35 : " + age.addAndGet(5));
        age.compareAndSet(35, 100);
        System.out.println("age : " + age.get());

        if (init.compareAndSet(false, true))
        {
            System.out.println("Init something successful.");
        }
        if (init.compareAndSet(false, true))
        {
            System.out.println("Init something again!!!");
        }

        System.out.println("distance : " + distance.get());
        System.out.println("distance : " + distance.addAndGet(2048L));
    }
}
