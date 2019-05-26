package concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * Desc: CountDownLatch使用
 * Creator: pengweixiang
 * Date: 2019-05-26
 */
public class CountDownLatchTest
{
    private static CountDownLatch count = new CountDownLatch(5);

    public static void main(String[] args)
    {
        new Thread(() ->
        {
            count.countDown();
            //task1 cost 10 seconds
            TimeUtils.sleep(10);
        }).start();

        for (int i = 0; i < 4; i++)
        {
            new Thread(() ->
            {
                count.countDown();
                //other task cost 1 second.
                TimeUtils.sleep(1);
            }).start();
        }

        try
        {
            count.await();
        }
        catch (InterruptedException e)
        {
            System.out.println("waiting interrupt");
        }
    }
}
