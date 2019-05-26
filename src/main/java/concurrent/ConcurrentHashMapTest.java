package concurrent;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Desc:
 * Creator: pengweixiang
 * Date: 2019-05-26
 */
public class ConcurrentHashMapTest
{
    private static ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();

    public static void main(String[] args)
    {
        ExecutorService produce = Executors.newSingleThreadExecutor();
        produce.submit(() ->
        {
            for (int i = 0; i < 100; i++)
            {
                map.put(UUID.randomUUID().toString(), "");
            }
        });

        TimeUtils.sleep(1);

        map.forEach((k, v) ->
        {
            System.out.println(k + " : " + v);
        });

        System.out.println("map size: " + map.size());

        produce.shutdown();
    }
}