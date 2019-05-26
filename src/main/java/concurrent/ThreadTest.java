package concurrent;

/**
 * Desc:
 * Creator: pengweixiang
 * Date: 2019-05-26
 */
public class ThreadTest
{
    public static void main(String[] args)
    {
        Thread task = new Thread()
        {
            @Override
            public void run()
            {
                System.out.println("task start ... time: " + TimeUtils.getCurrentTime());
            }
        };

        task.start();
    }
}
