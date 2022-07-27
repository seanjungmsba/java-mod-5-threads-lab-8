import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    public static int countFinishedFutures(List<Future> futures) {
        // your code here
        int counter = 0;
        for (Future future: futures) {
            if (future.isDone()) {
                counter++;
            }
        }
        return counter;
    }



    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Future<Integer> future = new SquareCalculator().calculate(10);

        while(!future.isDone()) {
            System.out.println("Calculating...");
            Thread.sleep(300);
        }

        Integer result = future.get();
        System.out.println(result);

//        countFinishedFutures();
    }

}

class SquareCalculator {
    private ExecutorService executor = Executors.newSingleThreadExecutor();

    public Future calculate(Integer input) {
        return executor.submit(() -> {
            Thread.sleep(1000);
            return input * input;
        });
    }
}