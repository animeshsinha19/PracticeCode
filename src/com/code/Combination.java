package com.code;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Animesh on 7/1/17.
 */
public class Combination {



    static void combinationUtil(int arr[], int data[], int start, int end, int index, int r, List<int[]> combinations) {
        if (index == r) {
            int[] newData = new int[r];
            for (int j=0; j<r; j++) {
                newData[j] = data[j];
            }
            combinations.add(newData);
            return;
        }

        for (int i=start; i<=end && end-i+1 >= r-index; i++) {
            data[index] = arr[i];
            combinationUtil(arr, data, i+1, end, index+1, r, combinations);
        }


    }

    static void printCombination(int arr[], int n, int r, List<int[]> combinations) {
        int data[]=new int[r];
        combinationUtil(arr, data, 0, n-1, 0, r, combinations);
    }

    public static void main (String[] args) {
        int arr[] = {1, 2, 3,4,5};
        int r = 2;
        int n = arr.length;
        List<int[]> combinations = new ArrayList<>();

        printCombination(arr, n, r, combinations);

        for(int[] data : combinations) {
            System.out.println(Arrays.toString(data));
        }

    }
}
