package concurrent;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Desc: 时间工具类
 * Creator: pengweixiang
 * Date: 2019-03-24
 */
public class TimeUtils
{
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");

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

    /**
     * 打印当前时间
     */
    public static void printCurrentTime()
    {
        System.out.println("Current time: " + sdf.format(new Date()));
    }
}
