package com.sashafilth.counting_sort;

import java.util.Arrays;

public class CountingSort {

    private int[] arr;

    public CountingSort(int[] arr) {
        this.arr = arr;
    }

    public void sort() {
        int min = Arrays.stream(arr).min().orElse(0);
        int max = Arrays.stream(arr).max().orElse(Integer.MAX_VALUE);
        int arrayLength = max - min + 1;
        int[] countArray = new int[arrayLength];
        for (int value: arr) {
            countArray[value - min]++;
        }
        int arrayIndex = 0;
        for (int i = 0; i < arrayLength; i++) {
            while (countArray[i] > 0) {
                arr[arrayIndex] = i + min;
                countArray[i]--;
                arrayIndex++;
            }

        }
        System.out.println(Arrays.toString(arr));
    }
}
