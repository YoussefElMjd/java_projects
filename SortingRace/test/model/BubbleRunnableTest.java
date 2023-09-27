package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BubbleRunnableTest {

    @Test
    void testBubbleSort() {
        int[] arr = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        BubbleRunnable r = new BubbleRunnable(arr);
        int[] result = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        r.run();
        assertArrayEquals(arr, result);
    }
}