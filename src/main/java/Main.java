import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;

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

    public static Callable<String> getCallableString() {
        Callable<String> callableString = () -> {
            System.out.println(Thread.currentThread().getName() + " is responsible for this call");
            StringBuilder sb = new StringBuilder("Example-String");
            return sb.toString();
        };
        return callableString;
    }

    public static Callable<Integer> getCallableInteger() {
        // your code here
        Callable<Integer> callableInteger = () -> {
            int exampleNumber = 2;
            return exampleNumber;
        };
        return callableInteger;
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        Future<String> stringFuture = singleThreadExecutor.submit(getCallableString());
        System.out.println("RESULT: String future returns " + stringFuture.get());
        Future<Integer> integerFuture = singleThreadExecutor.submit(getCallableInteger());
        System.out.println("RESULT: Integer future returns " + integerFuture.get());
        List<Future> futureList = new ArrayList<>();
        System.out.println("STATUS: adding stringFuture to the list");
        futureList.add(stringFuture);
        System.out.println("STATUS: adding integerFuture to the list");
        futureList.add(integerFuture);
        System.out.println("Number of completed Future object ===> " + countFinishedFutures(futureList));
        singleThreadExecutor.shutdown();
    }

}


