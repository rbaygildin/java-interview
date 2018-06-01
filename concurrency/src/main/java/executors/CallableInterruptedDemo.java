package executors;

import java.util.concurrent.*;

public class CallableInterruptedDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        Callable<Integer> callable = () -> {
            try {
                TimeUnit.SECONDS.sleep(10);
                return 123;
            } catch (InterruptedException e) {
                throw new IllegalStateException("task interrupted unfortunately", e);
            }
        };
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        try {

            Future<Integer> future = executorService.submit(callable);
            System.out.println("Task submitted successfully");
            Integer res = future.get(1, TimeUnit.SECONDS);
            System.out.println("Getting result = " + res);

        }
        catch (TimeoutException e){
            System.out.println("Oops! One task is very slow");
        }
        finally {
            executorService.shutdownNow();
            System.out.println("Exit. Bye bye!");
        }
    }
}
