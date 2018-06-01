package synch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantProducerConsumerDemo {
    static class SharedVar {
        public List<Integer> q = new ArrayList<>();
    }

    static class Producer extends Thread {
        private final ReentrantLock lock;
        private SharedVar sharedVar;
        boolean isSet = false;

        public Producer(ReentrantLock lock, SharedVar sharedVar) {
            this.lock = lock;
            this.sharedVar = sharedVar;
        }

        public void run() {
            for (int i = 0; i < 5; i++) {
                lock.lock();
                sharedVar.q.clear();
                for (int j = 0; j <= i; j++) {
                    System.out.println(String.format("Producer: set at iter %d %d", i, j));
                    sharedVar.q.add(i);
                }
                try {
                    TimeUnit.MILLISECONDS.sleep(800);
                    lock.unlock();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    static class Consumer extends Thread {
        private final ReentrantLock lock;
        private SharedVar var;
        boolean isSet = false;

        public Consumer(ReentrantLock lock, SharedVar var) {
            this.lock = lock;
            this.var = var;
        }

        public void run() {
            for (int i = 0; i < 5; i++) {
                lock.lock();
                for (int j = 0; j <= i; j++) {
                    System.out.println(String.format("Consumer: get at iter %d %d", i, var.q.get(j)));
                }
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                    lock.unlock();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        SharedVar var = new SharedVar();
        Producer producer = new Producer(lock, var);
        Consumer consumer = new Consumer(lock, var);
        consumer.start();
        producer.start();
        consumer.join();
        producer.join();
    }
}

