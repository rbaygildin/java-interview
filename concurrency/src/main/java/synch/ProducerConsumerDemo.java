package synch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ProducerConsumerDemo {
    public static void main(String[] args) throws InterruptedException {
        Object lock = new Object();
        SharedVar var = new SharedVar();
        Producer producer = new Producer(lock, var);
        Consumer consumer = new Consumer(lock, var);
        consumer.start();
        producer.start();
        consumer.join();
        producer.join();
    }
}

class SharedVar {
    public List<Integer> q = new ArrayList<>();
}

class Producer extends Thread {
    private final Object lock;
    private SharedVar sharedVar;
    boolean isSet = false;

    public Producer(Object lock, SharedVar sharedVar) {
        this.lock = lock;
        this.sharedVar = sharedVar;
    }

    public void run() {
        for (int i = 0; i < 5; i++) {
            while (isSet) {
                synchronized (lock) {
                    try {
                        lock.wait();
                        isSet = false;
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            sharedVar.q.clear();
            for (int j = 0; j <= i; j++) {
                System.out.println(String.format("Producer: set at iter %d %d", i, j));
                sharedVar.q.add(i);
            }
            try {
                TimeUnit.MILLISECONDS.sleep(800);
                synchronized (lock) {
                    lock.notifyAll();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            isSet = true;
        }
    }
}

class Consumer extends Thread {
    private final Object lock;
    private SharedVar var;
    boolean isSet = false;

    public Consumer(Object lock, SharedVar var) {
        this.lock = lock;
        this.var = var;
    }

    public void run() {
        for (int i = 0; i < 5; i++) {
            while (!isSet) {
                synchronized (lock) {
                    try {
                        lock.wait();
                        isSet = true;
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            for (int j = 0; j <= i; j++) {
                System.out.println(String.format("Consumer: get at iter %d %d", i, var.q.get(j)));

            }
            try {
                TimeUnit.MILLISECONDS.sleep(500);
                synchronized (lock) {
                    lock.notifyAll();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            isSet = false;
        }
    }
}
