package concurrent;

import java.util.concurrent.TimeUnit;

/**
 * Desc: 线程状态
 * 在linux环境，使用jstack工具：jstack <pid>
 * 在window环境，使用jstack工具：jps查看ThreadState的pid进程，然后执行jstack <pid>
 * jstack工具示例：
    "BlockedThread-2" #14 prio=5 os_prio=0 tid=0x0000000018e63000 nid=0xc18 waiting for monitor entry [0x000000001a35f000]
        java.lang.Thread.State: BLOCKED (on object monitor)

    "BlockedThread-1" #13 prio=5 os_prio=0 tid=0x0000000018eba000 nid=0x4ec waiting on condition [0x000000001a25f000]
        java.lang.Thread.State: TIMED_WAITING (sleeping)

    "WaitingThread" #12 prio=5 os_prio=0 tid=0x0000000018eb9000 nid=0x1d88 in Object.wait() [0x000000001a15f000]
        java.lang.Thread.State: WAITING (on object monitor)

    "TimeWaitingThread" #11 prio=5 os_prio=0 tid=0x0000000018eb8800 nid=0x2f88 waiting on condition [0x000000001a05f000]
        java.lang.Thread.State: TIMED_WAITING (sleeping)
 *
 * Creator: pengweixiang
 * Date: 2019-03-23
 */
public class ThreadState
{
    private static Object A = new Object();

    public static void main(String[] args)
    {
        //TIMED_WAITING状态
        new Thread(() ->
        {
            while (true)
            {
                try
                {
                    TimeUnit.SECONDS.sleep(5);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        }, "TimeWaitingThread").start();

        //WAITING状态
        new Thread(() ->
        {
            while (true)
            {
                synchronized (ThreadState.class)
                {
                    try
                    {
                        ThreadState.class.wait();
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }, "WaitingThread").start();

        //TIMED_WAITING
        new Thread(new Blocked(), "BlockedThread-1").start();
        //BLOCKED状态
        new Thread(new Blocked(), "BlockedThread-2").start();
    }

    static class Blocked implements Runnable
    {
        @Override
        public void run()
        {
            synchronized (Blocked.class)
            {
                while (true)
                {
                    try
                    {
                        TimeUnit.SECONDS.sleep(100);
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
