package concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * Desc: 该工具在同一时刻，只允许至多两个线程同时访问，超过两个线程的访问将被阻塞
 * Creator: pengweixiang
 * Date: 2019-04-14
 */
public class TwinsLock implements Lock
{
    private static final int THREAD_COUNT = 2;

    private final Sync sync = new Sync(THREAD_COUNT);

    private static final class Sync extends AbstractQueuedSynchronizer
    {
        Sync(int count)
        {
            if (count <= 0)
            {
                throw new IllegalArgumentException("Count must large than zero");
            }
            setState(count);
        }

        @Override
        protected int tryAcquireShared(int reduceCount)
        {
            for (;;)
            {
                int current = getState();
                int newCount = current - reduceCount;
                if (newCount < 0 || compareAndSetState(current, newCount))
                {
                    return newCount;
                }
            }
        }

        @Override
        protected boolean tryReleaseShared(int returnCount)
        {
            for (;;)
            {
                int current = getState();
                int newCount = current + returnCount;
                if (compareAndSetState(current, newCount))
                {
                    return true;
                }
            }
        }

        final ConditionObject newCondition()
        {
            return new ConditionObject();
        }
    }

    /**
     * 获取锁
     */
    @Override
    public void lock()
    {
        sync.acquireShared(1);
    }

    @Override
    public void unlock()
    {
        sync.releaseShared(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException
    {
        sync.acquireSharedInterruptibly(1);
    }

    @Override
    public boolean tryLock()
    {
        return sync.tryAcquireShared(1) >= 0;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException
    {
        return sync.tryAcquireSharedNanos(1, unit.toNanos(time));
    }

    @Override
    public Condition newCondition()
    {
        return sync.newCondition();
    }
}
