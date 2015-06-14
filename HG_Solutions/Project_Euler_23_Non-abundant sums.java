package com.hg;

import java.util.*;

/**
 * Project Euler #23: Non-abundant sums
 * */
public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();// # of test cases
        Integer[] input = new Integer[t];
        for (int i = 0; i < t; i++) {
            input[i] = in.nextInt();
        }
        final int limit = 28123;

        AbundantNumbers = new ArrayList<>();
        getAbundantNumbers(limit);

        // output
        for (int i= 0;i<input.length;i++){
            if (input[i]>limit || canBeSum(input[i])){
                System.out.println("YES");
            }else{
                System.out.println("NO");
            }
        }
    }
    private static List<Integer> AbundantNumbers;

    private static boolean canBeSum(int n)
    {
        boolean result = false;
        for (int i = 0;i<AbundantNumbers.size();i++){
            int res = n - AbundantNumbers.get(i);
            if (AbundantNumbers.contains(res)){
                return true;
            }
        }
        return result;
    }
    /***
     * n>=1
     */
    private static void getAbundantNumbers(int limit) {
        boolean[] isAbundant = new boolean[limit + 1];
        for (int i = 0; i <= limit; i++) {
            isAbundant[i] = false;
        }
        for (int i = 12; i<= limit; i++) {
            if (isAbundant[i]) {
                continue;
            }
            if (isAbundantNumber(i)) {
                for (int j = 1; i*j <= limit; j++) {
                    isAbundant[i*j] = true;
                }
            }
        }
        for (int i = 12; i <= limit; i++) {
            if (isAbundant[i]) AbundantNumbers.add(i);
        }
    }

    private static boolean isAbundantNumber(int number) {
        int sumOfDevisors = 0;
        List<Integer> devisors = new ArrayList<>();
        for (int i = 1 ; i <= number / 2 ; i ++) {
            if (number % i == 0) {
                sumOfDevisors += i;
                devisors.add(i);
            }
        }
        boolean abundant = (sumOfDevisors > number);
        return abundant;
    }
}