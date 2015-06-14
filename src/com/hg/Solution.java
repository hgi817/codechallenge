package com.hg;
/*
*
1
2 1 9 7 5 3 4 8 6
3 5 7 8 6 4 9 1 2
8 4 6 2 9 1 3 5 7
1 9 8 6 7 5 2 4 3
5 6 4 1 3 2 7 9 8
7 2 3 4 8 9 5 6 1
4 5 7 3 1 6 8 2 9
9 8 1 5 2 7 6 3 4
6 3 2 9 4 8 1 7 5
here two possible ans is there: (2,2)<->(2,3) or (7,2)<->(7,3)
*
*
* */

import java.util.*;

public class Solution {

    private static void checkSudokuStatus(int[][] grid, List<TmpResult> tmpResultList) {
        for (int i = 0; i < 9; i++) {
            int[] column = new int[9];
            int[] square = new int[9];
            int[] row = grid[i].clone();
            for (int j = 0; j < 9; j ++) {
                column[j] = grid[j][i];
                square[j] = grid[(i / 3) * 3 + j / 3][i * 3 % 9 + j % 3];
            }
            int[] row_cp = row.clone();
            int[] square_cp = square.clone();

            List<Integer> v_nums = validate(row);
            if (v_nums.size() > 0) {
                for (int v_num: v_nums){
                    int y = 0;
                    for (y =0;y<9;y++){
                        if (row_cp[y] == v_num){
                            tmpResultList.add(new TmpResult(y, i, v_num));
                        }
                    }
                }
            }

            v_nums = validate(column);
            if (v_nums.size() > 0) {
                for (int v_num: v_nums){
                    int x = 0;
                    for (x =0;x<9;x++){
                        if (grid[x][i] == v_num){
                            TmpResult tmpResult = new TmpResult(x, i, v_num);
                            if (!tmpResultList.contains(tmpResult)) {
                                tmpResultList.add(tmpResult);
                            }
                        }
                    }
                }
            }

            v_nums = validate(square);
            if (v_nums.size() > 0) {
                for (int v_num: v_nums){
                    int z = 0;
                    for (z =0;z<9;z++){
                        if (square_cp[z] == v_num){
                            TmpResult tmpResult = new TmpResult((i / 3) * 3 + z / 3, i * 3 % 9 + z % 3, v_num);
                            if (!tmpResultList.contains(tmpResult)) {
                                tmpResultList.add(tmpResult);
                            }
                        }
                    }
                }
            }
        }
    }

    private static boolean isValid(int[][] grid) {
        for (int i = 0; i < 9; i++) {
            int[] row = new int[9];
            int[] square = new int[9];
            int[] column = grid[i].clone();

            for (int j = 0; j < 9; j ++) {
                row[j] = grid[j][i];
                square[j] = grid[(i / 3) * 3 + j / 3][i * 3 % 9 + j % 3];
            }
            if (!(validate(column).size()==0 && validate(row).size()==0 && validate(square).size()==0))
                return false;
        }
        return true;
    }

    private static boolean isSwapValid(int[][] grid, int x1, int y1, int x2, int y2){
        int temp = grid[x2][y2];
        grid[x2][y2] = grid[x1][y1];
        grid[x1][y1] = temp;
        return isValid(copy(grid));
    }
    public static int[][] copy(int[][] input) {
        int[][] target = new int[input.length][];
        for (int i=0; i <input.length; i++) {
            target[i] = Arrays.copyOf(input[i], input[i].length);
        }
        return target;
    }
    // return number
    private static List<Integer> validate(int[] check) {
        List<Integer> result = new LinkedList<>();
        int i = 0;
        Arrays.sort(check);
        for (int number : check) {
            if (number != ++i)
                result.add(number);
        }
        return result;
    }

    /* Tail starts here */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        List<int[][]> allInput = new LinkedList<>();
        for (int i =0; i<t; i++){
            //Case #:
            System.out.println("Case #" + (i +1) +":");
            int [][] input = new int[9][9];
            for (int x=0; x<9; x++){
                for (int y = 0; y<9; y++){
                    input[x][y] = in.nextInt();
                }
            }
            // input done
            List<TmpResult> tmpResultList = new LinkedList<>();
            checkSudokuStatus(copy(input),tmpResultList);
            if (tmpResultList.size()>0){
                for (int r=0;r<tmpResultList.size();r++){
                    TmpResult curResult = tmpResultList.get(r);
                    for (int r2 = r+1;r2<tmpResultList.size();r2++){
                        TmpResult curResult2 = tmpResultList.get(r2);
                        if (curResult.v!=curResult2.v){
                            int x1 = curResult.x;
                            int x2 = curResult2.x;
                            int y1 = curResult.y;
                            int y2 = curResult2.y;
                            if (isSwapValid(copy(input),x1,y1,x2,y2)){
                                x1++;x2++;y1++;y2++;
                                if (x1<x2 || ((x1==x2) && (y1<y2))){
                                    System.out.println("("+x1+","+y1+") <-> ("+x2+","+y2 +")");
                                }
                                else{
                                    System.out.println("("+x2+","+y2+") <-> ("+x1+","+y1 +")");
                                }
                            }
                        }
                    }
                }
            }
            else{
                System.out.println("Serendipity");
            }
            allInput.add(input);
        }
    }
}

class TmpResult{
    TmpResult(int ix, int iy, int iv){
        x = ix; y=iy; v =iv;
    }
    public int x;
    public int y;
    public int v;
    public boolean equals(Object obj) {
        if (obj == this) { return true; }
        if (obj == null || obj.getClass() != this.getClass()) { return false; }
        TmpResult guest = (TmpResult) obj;
        return x == guest.x &&
                y == guest.y &&
                v == guest.v;
    }

    public int hashCode() {
        return v;
    }
}