package com.hg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Project Euler #42: Coded triangle numbers
 * */
public class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();// # of test cases
        long[] input = new long[t];
        int[] result = new int[t];
        for (int i = 0; i<t;i++) {
            input[i] = in.nextLong();
            double d=(Math.sqrt(8*input[i] +1)-1)/2;
            int intResult = (int) Math.floor(d);
            if ((d == intResult) && !Double.isInfinite(d)) {
                // int
                result[i] =intResult;
            }
            else{
                result[i] = -1;
            }
            System.out.println(result[i]);
        }
    }
}