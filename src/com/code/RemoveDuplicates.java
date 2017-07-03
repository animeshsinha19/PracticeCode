package com.code;

import java.util.Arrays;

/**
 * Created by Animesh on 7/1/17.
 */
public class RemoveDuplicates {

    public static void main(String[] args) {
        int[] arr = {1,1,1,2,2,3,3};
        int len = removeDuplicates(arr);
        System.out.print(len+"\n");
        for(int i = 0;i<len;i++) {
            System.out.print(arr[i] + " ");
        }
    }

    static int removeDuplicates(int[] nums) {
        if(nums.length == 0 || nums.length == 1)
            return nums.length;

        int len = 1, i = 1, newIndex = 0, elem = nums[0];

        while(i<nums.length) {
            if(elem != nums[i]) {
                len++;
                nums[++newIndex] = nums[i];
                elem = nums[i];
            }
            i++;
        }


        return len;

    }

}
