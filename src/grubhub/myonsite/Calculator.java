package grubhub.myonsite;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/spiral-matrix/
 *
 * Created by brianzhang on 5/14/20.
 */
public class Calculator {
    public static void main(String[] args) {
        Calculator sm = new Calculator();
        for(int i : sm.spiralOrder(new int[][]{{2,5,8}, {4,0,-1}})){
            System.out.println(i);
        }
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();

        if(matrix.length == 0) return res;

        int topRow = 0, bottomRow=matrix.length-1;
        int colBegin = 0, colEnd = matrix[0].length-1;

        while(true){
            for(int i=colBegin; i<=colEnd; i++) res.add(matrix[topRow][i]);
            topRow++;
            if(topRow > bottomRow) break;

            for(int i=topRow; i<=bottomRow; i++) res.add(matrix[i][colEnd]);
            colEnd--;
            if(colBegin > colEnd) break;

            for(int i=colEnd; i>=colBegin; i--) res.add(matrix[bottomRow][i]);
            bottomRow--;
            if(topRow > bottomRow) break;

            for(int i=bottomRow; i>=topRow; i--) res.add(matrix[i][colBegin]);
            colBegin++;
            if(colBegin > colEnd) break;
        }

        return res;
    }
}
