package model;

import java.time.Duration;
import java.time.LocalDateTime;

public class MergeRunnable extends Observable implements Runnable {
    long operation;
    int[] array;
    Duration duration;
    LocalDateTime begin;
    LocalDateTime end;

    public MergeRunnable(int[] array) {
        operation = 0;
        this.array = array;
    }

    private void mergeSort(int[] a, int[] l, int[] r, int left, int right) {
        operation += 3;
        int i = 0, j = 0, k = 0;
        while (i < left && j < right) {
            operation += 1;
            if (l[i] <= r[j]) {
                operation += 1;
                a[k++] = l[i++];
            } else {
                operation += 1;
                a[k++] = r[j++];
            }
        }
        while (i < left) {
            operation += 1;
            a[k++] = l[i++];
        }
        while (j < right) {
            operation += 1;
            a[k++] = r[j++];
        }
    }

    public void sort(int[] a, int n) {
        operation += 1;
        if (n < 2) {
            return;
        }
        operation += 3;
        int mid = n / 2;
        int[] l = new int[mid];
        int[] r = new int[n - mid];

        for (int i = 0; i < mid; i++) {
            operation += 1;
            l[i] = a[i];
        }
        for (int i = mid; i < n; i++) {
            operation += 1;
            r[i - mid] = a[i];
        }
        sort(l, mid);
        sort(r, n - mid);

        mergeSort(a, l, r, mid, n - mid);
    }

    @Override
    public synchronized void run() {
        begin = LocalDateTime.now();
        sort(this.array, this.array.length);
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
