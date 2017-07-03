package com.code;

import java.util.Arrays;

public class MergeSortedArrays {

    public static void main(String[] args) {
	    int[] nums1 = new int[6];
        nums1[0]=1;
        nums1[1]=2;
        nums1[2]=3;
        nums1[3]=4;
	    int[] nums2 = {1,2};

	    merge(nums1,4,nums2,2);
    }
    static void merge(int[] nums1, int m, int[] nums2, int n) {

        if(m == 0 && n != 0) {
            nums1 = nums2;
        } else {
            int[] newNums = new int[m+n];
            int i=0,j=0,k=0;

            while(k!=m+n) {

                if(j==n) {
                    for(int l=i;l<m;l++,k++) {
                        newNums[k] = nums1[l];
                    }
                    break;
                }
                if(i==m) {
                    for(int l=j;l<n;l++,k++) {
                        newNums[k] = nums2[l];
                    }
                    break;
                }

                if(nums1[i] == nums2[j]) {
                    newNums[k] = nums1[i]; k++;
                    newNums[k] = nums1[i]; k++;
                    i++;
                    j++;
                } else if(nums1[i]<nums2[j]) {
                    newNums[k] = nums1[i];
                    k++;
                    i++;
                } else {
                    newNums[k] = nums2[j];
                    k++;
                    j++;
                }

            }

            nums1 = newNums;
        }




    }


}
