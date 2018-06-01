package executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorServiceDemo {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(() -> System.out.println("Hello from " + Thread.currentThread().getName()));
        executorService.submit(() -> System.out.println("2 + 2 - 5 [" + Thread.currentThread().getName() + "]"));
        executorService.shutdown();
        try {
            executorService.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            System.out.println("Start to terminating");
        }
        finally {
            if(!executorService.isTerminated()){
                System.out.println("Shutdown all tasks now");
                executorService.shutdownNow();
            }
            System.out.println("All tasks terminated and also some...");
        }
        System.out.println("All tasks terminated");
    }
}
