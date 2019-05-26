package concurrent;

/**
 * Desc:
 * Creator: pengweixiang
 * Date: 2019-05-26
 */
public class ThreadLocalTest
{

    private static final ThreadLocal<Long> TIME_THREADLOCAL = ThreadLocal.withInitial(() -> System.currentTimeMillis());

    public static final void begin()
    {
        TIME_THREADLOCAL.set(System.currentTimeMillis());
    }

    public static final long end()
    {
        return System.currentTimeMillis() - TIME_THREADLOCAL.get();
    }

    public static void main(String[] args)
    {
        ThreadLocalTest.begin();
        TimeUtils.sleep(1);
        System.out.println("Cost: " + ThreadLocalTest.end() + " mills");
    }
}
