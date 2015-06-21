package com.hg.codeeval;

/**
 * Sum of Primes

 Challenge Description:

 Write a program which determines the sum of the first 1000 prime numbers.
 Input sample:

 There is no input for this program.
 Output sample:

 Print to stdout the sum of the first 1000 prime numbers.

 3682913
 */
public class Sum_Of_Primes {
    private static final int max_counter = 1000;
    public static void main (String[] args){
        int approxN = getMaxNum(max_counter);
        int total = calcPrimeSum(approxN, max_counter);
        System.out.println(total);
    }

    /**
     * nln(n)+nln(ln(n))
     */
    private static int getMaxNum(int n) {
        if (n < 6){
            return 13;
        }
        double lnn = Math.log(n);
        double result = n * lnn + n * Math.log(lnn);
        return (int)Math.ceil(result);
    }

    private static int calcPrimeSum(int n, int maxCounter) {
        int total = 0;
        int counter = 0;
        boolean [] isPrime = new boolean[n];
        for (int i = 2; i < n; i ++) {
            isPrime[i] = true;
        }

        for (int i = 2; i * i <= n; i ++){
            if (isPrime[i]) {
                for (int j = 2 ; j * i < n; j ++) {
                    isPrime[i*j] = false;
                }
            }
        }

        for (int i = 0; i < n; i ++) {
            if (isPrime[i]) {
                total += i;
                counter ++;
                if (counter == maxCounter) {
                    return total;
                }
            }
        }
        return total;
    }
}
