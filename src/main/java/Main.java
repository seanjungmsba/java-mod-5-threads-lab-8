import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.IntStream;

public class Main {

    /* counting number of finished futures using iterative approach */
    public static int countFinishedFuturesVersionOne(List<Future> futures) {
        int counter = 0;
        for (Future future: futures) {
            if (future.isDone()) {
                counter++;
            }
        }
        return counter;
    }

    /* counting number of finished futures using stream API */
    public static int countFinishedFuturesVersionTwo(List<Future> futures) {
        return (int)futures.stream().filter(future -> future.isDone()).count();
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
            System.out.println(Thread.currentThread().getName() + " is responsible for this call");
            int exampleNumber = 2;
            return exampleNumber;
        };
        return callableInteger;
    }

    public static Callable<Double> getCallableDouble() {
        return () -> {
            System.out.println(Thread.currentThread().getName() + " is responsible for this call");
            Double doubleNum = Math.random();
            Double roundedDoubleNum = Math.round(doubleNum * 100.0) / 100.0;
            return roundedDoubleNum;
        };
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        final int THREAD_CNT = 5;
        ExecutorService executor = Executors.newFixedThreadPool(THREAD_CNT);

        Future<String> stringFuture = executor.submit(getCallableString());
        System.out.println("RESULT 1: String future returns ===> " + stringFuture.get());
        Future<Integer> integerFuture = executor.submit(getCallableInteger());
        System.out.println("RESULT 2: Integer future returns ===> " + integerFuture.get());
        Future<Double> doubleFuture = executor.submit(getCallableDouble());
        System.out.println("RESULT 3: Double future returns ===> " + doubleFuture.get());

        List<Future> futureList = new ArrayList<>();
        IntStream.rangeClosed(1, THREAD_CNT).forEach(i -> {
            futureList.add(stringFuture);
            futureList.add(integerFuture);
            futureList.add(doubleFuture);
        });

        System.out.println("Number of finished Future object (using iterative approach) ===> " + countFinishedFuturesVersionOne(futureList));
        System.out.println("Number of finished Future object (using stream API) ===> " + countFinishedFuturesVersionTwo(futureList));
        executor.shutdown();
    }

}