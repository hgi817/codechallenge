package com.hg.codeeval;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Created by hg on 6/14/2015.
 */
public class String_List {
    public static void main (String[] args) throws IOException {
        File file = new File(args[0]);
        BufferedReader buffer = new BufferedReader(new FileReader(file));
        String line;
        List<String> results;
        while ((line = buffer.readLine()) != null) {
            line = line.trim();
            // Process line of input Here
            String[] parts = line.split(",");
            int n = Integer.parseInt(parts[0]);
            Character[] chars = toUniqueChars(parts[1]);
            results = process(n, chars);
            printResult(results);
        }
    }

    private static Character[] toUniqueChars(String input){
        char[] chars = input.toCharArray();
        Set<Character> set = new HashSet<>();
        for (char c : chars) {
            set.add(c);
        }
        Character[] result = new Character[set.size()];
        result = set.toArray(result);
        Arrays.sort(result);
        return result;
    }

    private static List<String> process(int n, Character[] chars){
        List<String> results = new LinkedList<>();
        String t = "";
        addChar(t, n, chars, results);
        return results;
    }

    private static void addChar(String input, int n, Character[] chars, List<String> results){
        if (input.length() == n) {
            results.add(input);
            return;
        }
        for (char c : chars){
            addChar(input + c, n, chars, results);
        }
    }

    private static void printResult(List<String> results){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < results.size(); i++) {
            sb.append(results.get(i));
            if (i != results.size() - 1){
                sb.append(",");
            }
        }
        System.out.println(sb.toString());
    }
}