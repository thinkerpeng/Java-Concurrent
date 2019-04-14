package concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;

/**
 * Desc:自定义独占锁
 * Creator: pengweixiang
 * Date: 2019-04-07
 */
public class Mutex
{
    /**
     * 静态内部类，自定义同步器
     */
    private static class Sync extends AbstractQueuedSynchronizer
    {
        /**
         * 是否为占用状态
         *
         * @return boolean
         */
        @Override
        protected boolean isHeldExclusively()
        {
            return getState() == 1;
        }

        /**
         * 获取锁
         *
         * @param acquires
         * @return
         */
        @Override
        protected boolean tryAcquire(int acquires)
        {
            if (compareAndSetState(0, 1))
            {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        /**
         * 释放锁
         *
         * @param release release
         * @return
         */
        @Override
        protected boolean tryRelease(int release)
        {
            if (getState() == 0)
            {
                throw new IllegalMonitorStateException();
            }
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }

        /**
         * 返回一个Condition，每个condition都包含一个condition队列
         *
         * @return condition
         */
        Condition newCondition()
        {
            return new ConditionObject();
        }
    }

    private final Sync sync = new Sync();

    public void lock()
    {
        sync.acquire(1);
    }

    public boolean tryLock()
    {
        return sync.tryAcquire(1);
    }

    public void unlock()
    {
        sync.release(1);
    }

    public Condition newCondition()
    {
        return sync.newCondition();
    }

    public boolean isLock()
    {
        return sync.isHeldExclusively();
    }

    public boolean hasQueuedThread()
    {
        return sync.hasQueuedThreads();
    }

    public void lockInterruptibly() throws InterruptedException
    {
        sync.acquireInterruptibly(1);
    }

    public boolean tryLock(long timeout, TimeUnit unit) throws InterruptedException
    {
        return sync.tryAcquireNanos(1, unit.toNanos(timeout));
    }
}
