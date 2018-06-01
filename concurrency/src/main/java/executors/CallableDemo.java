package executors;

import java.util.concurrent.*;

public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable<Integer> callable = () -> {
            try {
                TimeUnit.SECONDS.sleep(3);
                return 123;
            } catch (InterruptedException e) {
                throw new IllegalStateException("task interrupted", e);
            }
        };
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Future<Integer> future = executorService.submit(callable);
        System.out.println("Task submitted successfully");
        System.out.println("Task is done? " + future.isDone());
        Integer res = future.get();
        System.out.println("Getting result = " + res);
        executorService.shutdownNow();
        System.out.println("Exit. Bye bye!");
    }
}
