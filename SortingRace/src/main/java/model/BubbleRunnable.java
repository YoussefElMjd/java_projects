package model;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.stream.IntStream;

public class BubbleRunnable extends Observable implements Runnable {
    long operation;
    int[] array;
    Duration duration;
    LocalDateTime begin;
    LocalDateTime end;

    public BubbleRunnable(int[] array) {
        operation = 0;
        this.array = array;
    }

    public void sort(int[] arr) {
        operation += 1;
        int n = arr.length;
        IntStream.range(0, n - 1)
                .flatMap(i -> IntStream.range(1, n - i))
                .forEach(j -> {
                    operation += 1;
                    if (arr[j - 1] > arr[j]) {
                        operation += 1;
                        int temp = arr[j];
                        operation += 1;
                        arr[j] = arr[j - 1];
                        operation += 1;
                        arr[j - 1] = temp;
                    }
                });
    }

    @Override
    public synchronized void run() {
        begin = LocalDateTime.now();
        sort(this.array);
        end = LocalDateTime.now();
        duration = Duration.between(begin, end);
        notifyObs();
    }


    public long getOperation() {
        return operation;
    }

    public int[] getArray() {
        return array;
    }

    public Duration getDuration() {
        return duration;
    }

    public LocalDateTime getBegin() {
        return begin;
    }

    public LocalDateTime getEnd() {
        return end;
    }
}

