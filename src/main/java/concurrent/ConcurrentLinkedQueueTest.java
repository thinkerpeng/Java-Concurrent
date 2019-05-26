package concurrent;

import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Desc: 线程安全队列
 * Creator: pengweixiang
 * Date: 2019-05-26
 */
public class ConcurrentLinkedQueueTest
{
    private static ConcurrentLinkedQueue queue = new ConcurrentLinkedQueue();

    public static void main(String[] args)
    {
        String uuid1 = UUID.randomUUID().toString();
        String uuid2 = UUID.randomUUID().toString();
        System.out.println("UUID: " + uuid1);
        System.out.println("UUID: " + uuid2);
        queue.offer(uuid1);
        queue.offer(uuid1);
        queue.offer(uuid2);

        System.out.println("Queue size: " + queue.size());
        queue.forEach((v) ->
        {
            System.out.println(v);
        });
    }
}
