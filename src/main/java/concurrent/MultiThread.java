package concurrent;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.Arrays;
import java.util.List;

/**
 * Desc:main()方法启动也是多线程
 * Creator: pengweixiang
 * Date: 2019-03-23
 */
public class MultiThread
{
    public static void main(String[] args)
    {
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(true, true);
        List<ThreadInfo> threadInfoList = Arrays.asList(threadInfos);
        threadInfoList.stream().forEach(threadInfo ->
        {
            System.out.println("id: " + threadInfo.getThreadId() + ", name: " + threadInfo.getThreadName());
        });
    }
}
