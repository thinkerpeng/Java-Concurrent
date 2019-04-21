package concurrent;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Desc: 缓存示例，读写锁实现
 * Creator: pengweixiang
 * Date: 2019-04-21
 */
public class Cache
{
    private static Map<String, String> map = new HashMap<>();

    private static ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
    private static Lock r = rwl.readLock();
    private static Lock w = rwl.writeLock();

    /**
     * 获取对应key的值
     * @param key key
     * @return value
     */
    public static String get(String key)
    {
        r.lock();
        try
        {
            return map.get(key);
        }
        finally
        {
            r.unlock();
        }
    }

    /**
     * 设置key对应的新值，并返回旧的值
     * @param key key
     * @param value newValue
     * @return oldValue
     */
    public static String put(String key, String value)
    {
        w.lock();
        try
        {
            return map.put(key, value);
        }
        finally
        {
            w.unlock();
        }
    }

    /**
     * 清空缓存的所有值
     */
    public static void clearCache()
    {
        w.lock();
        try
        {
            map.clear();
        }
        finally
        {
            w.unlock();
        }
    }
}
