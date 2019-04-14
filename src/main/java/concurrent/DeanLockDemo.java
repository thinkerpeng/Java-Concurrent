package concurrent;

/**
 * Desc:死锁Demo
 * Creator: pengweixiang
 * Date: 2019-03-23
 */
public class DeanLockDemo
{
    private static Object A = new Object();
    private static Object B = new Object();

    public static void main(String[] args)
    {
        new Thread(() ->
        {
            synchronized (A)
            {
                try
                {
                    System.out.println("Run here A ...");
                    Thread.currentThread().sleep(100);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }

                synchronized (B)
                {
                    System.out.println("Run here B ...");
                }
            }
        }).start();

        new Thread(() ->
        {
            synchronized (B)
            {
                synchronized (A)
                {
                    System.out.println("Run here C ...");
                }
            }
        }).start();
    }
}
