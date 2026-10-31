import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class ThreadPoolRunner {

    public static void main(String[] args) {

        
        ExecutorService pool = Executors.newFixedThreadPool(3);

        for (int i = 1; i <= 10; i++) {

            final int taskId = i;

            pool.submit(() -> {
                System.out.println(
                    "Task " + taskId +
                    " is running on " +
                    Thread.currentThread().getName()
                );

                try {
                    
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                System.out.println(
                    "Task " + taskId +
                    " completed by " +
                    Thread.currentThread().getName()
                );
            });
        }

        pool.shutdown();

        try {
            if (pool.awaitTermination(30, java.util.concurrent.TimeUnit.SECONDS)) {
                System.out.println("All tasks completed.");
            } else {
                System.out.println("Timeout! Some tasks are still running.");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}-