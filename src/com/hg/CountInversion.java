package com.hg;

/**
 * Created by hg on 6/2/2015.
 */
public class CountInversion {

    // count number of inversions in the array a[] - do not overwrite a[]
    public static int count(Comparable a[]){
        Comparable[] dest = new Comparable[a.length];
        System.arraycopy( a, 0, dest, 0, a.length );
        Comparable[] tmp = new Comparable[a.length];
        int result = countInversion(a, dest, tmp, 0, dest.length -1);
        return result;
    }

    public static int countInversion(Comparable a[], Comparable[] b, Comparable tmp[], int left, int right){
        if (left >= right) return 0;
        int center = left + (right - left)/2;
        int leftTotal = countInversion(a, b, tmp, left, center);
        int rightTotal = countInversion(a, b, tmp,center +1,right);
        int crossTotal = countInv(b, tmp, left,center+1,right);
        int total = leftTotal + rightTotal + crossTotal;
        // check
        assert total == brute(a, left, right);
        return total;
    }

    public static int countInv(Comparable a[], Comparable tmp[], int left, int right,int rightEnd){
        int leftEnd = right - 1;
        int k = left;
        int num = rightEnd - left + 1;
        int counter = 0;

        while(left <= leftEnd && right <= rightEnd) {
            if (a[left].compareTo(a[right]) <= 0)
                tmp[k++] = a[left++];
            else {
                tmp[k++] = a[right++];
                counter += leftEnd - left + 1;
            }
        }

        while(left <= leftEnd)    // Copy rest of first half
            tmp[k++] = a[left++];

        while(right <= rightEnd)  // Copy rest of right half
            tmp[k++] = a[right++];

        // Copy tmp back
        for(int i = 0; i < num; i++, rightEnd--)
            a[rightEnd] = tmp[rightEnd];

        return counter;
    }

    // count number of inversions in a[lo..hi] via brute force (for debugging only)
    public static int brute(Comparable[] a, int lo, int hi) {
        int inversions = 0;
        for (int i = lo; i <= hi; i++)
            for (int j = i + 1; j <= hi; j++)
                if (a[j].compareTo(a[i])<0) inversions++;
        return inversions;
    }
}



/*************************************************************************
 *  Compilation:  javac Inversions.java
 *  Execution:    java Inversions < input.txt
 *
 *  Read in N integers and count number of inversions in N log N time.
 *
 *************************************************************************/

/*
public class Inversions {

    // merge and count
    private static long merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        long inversions = 0;

        // copy to aux[]
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }

        // merge back to a[]
        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++) {
            if      (i > mid)                a[k] = aux[j++];
            else if (j > hi)                 a[k] = aux[i++];
            else if (less(aux[j], aux[i])) { a[k] = aux[j++]; inversions += (mid - i + 1); }
            else                             a[k] = aux[i++];
        }
        return inversions;
    }

    // return the number of inversions in the subarray b[lo..hi]
    // side effect b[lo..hi] is rearranged in ascending order
    private static long count(Comparable[] a, Comparable[] b, Comparable[] aux, int lo, int hi) {
        long inversions = 0;
        if (hi <= lo) return 0;
        int mid = lo + (hi - lo) / 2;
        inversions += count(a, b, aux, lo, mid);
        inversions += count(a, b, aux, mid+1, hi);
        inversions += merge(b, aux, lo, mid, hi);
        assert inversions == brute(a, lo, hi);
        return inversions;
    }


    // count number of inversions in the array a[] - do not overwrite a[]
    public static long count(Comparable[] a) {
        Comparable[] b   = new Comparable[a.length];
        Comparable[] aux = new Comparable[a.length];
        for (int i = 0; i < a.length; i++) b[i] = a[i];
        long inversions = count(a, b, aux, 0, a.length - 1);
        return inversions;
    }


    // is v < w ?
    private static boolean less(Comparable v, Comparable w) {
        return (v.compareTo(w) < 0);
    }

    // count number of inversions in a[lo..hi] via brute force (for debugging only)
    private static long brute(Comparable[] a, int lo, int hi) {
        long inversions = 0;
        for (int i = lo; i <= hi; i++)
            for (int j = i + 1; j <= hi; j++)
                if (less(a[j], a[i])) inversions++;
        return inversions;
    }


    // count number of inversions via brute force
    public static long brute(Comparable[] a) {
        return brute(a, 0, a.length - 1);
    }

    // generate N real numbers between 0 and 1, and mergesort them
//    public static void main(String[] args) {
//        int[] a = StdIn.readAllInts();
//        int N = a.length;
//        Integer[] b = new Integer[N];
//        for (int i = 0; i < N; i++)
//            b[i] = a[i];
//        StdOut.println(Inversions.count(b));
//        StdOut.println(Inversions.brute(b));
//    }
}*/
