package executors;

public class JoinChildrenInterruptingDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            System.out.println("Hello from thread" + Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
                System.out.println("I'm here yet");
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread " + Thread.currentThread().getName() + " closed");
        }, "child");
        t1.start();
        System.out.println("Main thread starting");
        System.out.println("Hello from main thread");
        t1.join(200);
        System.out.println("Main thread exiting");
        System.exit(0);
    }
}
