package synch;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class SimpleSynchExecutor {
    private int count = 0;

    public static void main(String[] args) {
        SimpleSynchExecutor demo = new SimpleSynchExecutor();
        demo.runTasks();
    }

    private void runTasks() {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        IntStream.range(0, 10000).forEach(i -> executorService.submit(this::increment));
        try {
            executorService.awaitTermination(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if(!executorService.isTerminated()){
                System.out.println("Shutdown all tasks now");
                executorService.shutdownNow();
            }
            System.out.println("All tasks terminated and also some...");
        }
        System.out.println("Result = " + count);
        assert count == 10000;
        System.out.println("All tasks terminated");
    }

    private synchronized void increment() {
        count = count + 1;
    }
}
