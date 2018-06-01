package executors;

public class SimpleThreadingDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
           System.out.println("Hello from thread" + Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread " + Thread.currentThread().getName() + " closed");
        }, "child");
        t1.start();
        System.out.println("Main thread starting");
        t1.join();
        System.out.println("Main thread exiting");
    }
}
