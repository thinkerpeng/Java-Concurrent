package concurrent;

import java.util.concurrent.TimeUnit;

/**
 * Desc: 时间工具类
 * Creator: pengweixiang
 * Date: 2019-03-24
 */
public class TimeUtils
{
    /**
     * 睡眠接口
     * @param seconds seconds
     */
    public static void sleep(int seconds)
    {
        try
        {
            TimeUnit.SECONDS.sleep(seconds);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
