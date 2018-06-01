package executors;

import java.util.Arrays;
import java.util.concurrent.*;

public class InvokeAnyDemo {
    private static Callable<String> getCallable(final String result, long duration) {
        return () -> {
            TimeUnit.SECONDS.sleep(duration);
            return result;
        };
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newWorkStealingPool();
        String res = service.invokeAny(Arrays.asList(
                getCallable("Task 1", 3),
                getCallable("Task 2", 1),
                getCallable("Task 3", 2)
                )
        );
        System.out.println("Result: " + res);
        service.shutdownNow();
    }
}
