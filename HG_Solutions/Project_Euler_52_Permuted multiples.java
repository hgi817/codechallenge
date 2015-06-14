package com.hg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Project Euler #52: Permuted multiples
 * */
public class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        String strToCmp;;
        List<ArrayList<String>> allResults = new ArrayList<>();
        ArrayList<String> results = new ArrayList<>();
        boolean isOk;
        for (int x= 1;x<=n;x++) {
            isOk = true;
            results.clear();
            strToCmp = convertToString(x);
            results.add(Integer.toString(x));

            for (int i =2;i<=k; i++) {
                int t = x*i;
                String result = convertToString(t);
                if (strToCmp.equals(result)) {
                    results.add(Integer.toString(t));
                }
                else{
                    isOk = false;
                    break;
                }
            }
            if (!isOk){
                continue;
            }
            if (results.size()>1){
                allResults.add((ArrayList<String>) results.clone());
            }
        }

        for(ArrayList<String> output: allResults) {
            String line = "";
            for (String s: output){
                line = line + " " + s;
            }
            System.out.println(line.trim());
        }
    }

    private static String convertToString(int n) {
        String strToCmp;
        String nStr = Integer.toString(n);
        char[] charArray = nStr.toCharArray();
        Arrays.sort(charArray);
        strToCmp = new String(charArray);
        return strToCmp;
    }
}
