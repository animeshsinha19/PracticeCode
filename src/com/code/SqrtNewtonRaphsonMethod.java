package com.code;

/**
 * Created by Animesh on 7/1/17.
 */
public class SqrtNewtonRaphsonMethod {

    public static void main(String[] args) {
        int input = 2147483647;
//        int input = 1625;

        System.out.print(sqrt(input));


    }

    static int sqrt(int x) {
        if(x==0 || x==1)
            return x;

        long r = x;

        while(r*r > x) {
            r = (r + x/r)/2;
        }
        return (int)r;

    }

}
