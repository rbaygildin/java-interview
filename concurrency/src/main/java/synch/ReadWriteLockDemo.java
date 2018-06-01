package synch;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockDemo {
    public static void main(String[] args) throws InterruptedException {
        ReadWriteLock lock = new ReentrantReadWriteLock();
        ExecutorService service = Executors.newFixedThreadPool(2);
        Map<String, String> map = new HashMap<>();
        Runnable writer = () -> {
            lock.writeLock().lock();
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println("Write value");
                map.put("1", "one");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.writeLock().unlock();
            }
        };
        service.submit(writer);
        Runnable reader = () -> {
            lock.readLock().lock();
            try {
                System.out.println("Value = " + map.get("1"));
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.readLock().unlock();
            }
        };
        service.submit(reader);
        service.submit(reader);
        service.awaitTermination(5, TimeUnit.SECONDS);
        service.shutdownNow();
    }
}
