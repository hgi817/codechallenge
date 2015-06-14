package com.hg;

import java.util.*;

/**
 * Project Euler #10: Summation of primes
 * */
public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();// # of test cases
        Integer[] input = new Integer[t];
        for (int i = 0; i < t; i++) {
            input[i] = in.nextInt();
            //System.out.println(GetPrimeSum(in.nextInt()));
        }

        int maxInput = Collections.max(Arrays.asList(input));
        Primes = new LinkedList<>();
        GetPrimes(maxInput);

        for (int i= 0;i<input.length;i++){
            System.out.println(CalcSum(input[i]));
        }

    }
    private static List<Integer> Primes;

    private static long CalcSum(int n)
    {
        long sum = 0;
        for (Integer i: Primes){
            if (i<=n){
                sum += i;
            }else{
                return sum;
            }
        }
        return sum;
    }
    /***
     * n>=1
     */
    private static void GetPrimes(int n) {
        if (n == 1) return;
        if (n == 2) {
            Primes.add(2);
            return;
        }
        // initially assume all integers are prime
        boolean[] isPrime = new boolean[n + 1];
        for (int i = 2; i <= n; i++) {
            isPrime[i] = true;
        }
        // mark non-primes <= N using Sieve of Eratosthenes
        for (int i = 2; i*i <= n; i++) {
            // if i is prime, then mark multiples of i as nonprime
            // suffices to consider mutiples i, i+1, ..., N/i
            if (isPrime[i]) {
                for (int j = i; i*j <= n; j++) {
                    isPrime[i*j] = false;
                }
            }
        }
        for (int i = 2; i <= n; i++) {
            if (isPrime[i]) Primes.add(i);
        }
    }
    /***
     * n>=1
     */
    private static long GetPrimeSum(int n) {
        if (n == 1) return 0;
        if (n == 2) return 2;
        long sum = 0;
        // initially assume all integers are prime
        boolean[] isPrime = new boolean[n + 1];
        for (int i = 2; i <= n; i++) {
            isPrime[i] = true;
        }
        // mark non-primes <= N using Sieve of Eratosthenes
        for (int i = 2; i*i <= n; i++) {
            // if i is prime, then mark multiples of i as nonprime
            // suffices to consider mutiples i, i+1, ..., N/i
            if (isPrime[i]) {
                for (int j = i; i*j <= n; j++) {
                    isPrime[i*j] = false;
                }
            }
        }

        for (int i = 2; i <= n; i++) {
            if (isPrime[i]) sum+=i;
        }
        return sum;
    }
}