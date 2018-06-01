package executors;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceBatchDemo {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newWorkStealingPool();
        List<Callable<String>> callables = Arrays.asList(
                () -> "Task 1: " + Thread.currentThread().getName(),
                () -> "Task 2: " + Thread.currentThread().getName(),
                () -> "Task 3: " + Thread.currentThread().getName()
        );
        service.invokeAll(callables).stream().map(future -> {
            try{
                return future.get();
            }
            catch (Exception e){
                throw new RuntimeException(e);
            }
        }).forEach(System.out::println);
        service.shutdownNow();
    }
}
