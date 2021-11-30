package square.onsite;

import java.util.*;
/**
 * Created by brianzhang on 4/6/21.
 */
public class CipherAndDecipher {

    public static void main(String[] args) {
        String[][] matrix = new String[][]{{"a", "b", "c", "d", "e", "f"},
                                            {"1", "2", "3", "4", "5", "6"}};
        List<int[]> encoded = cipher(matrix, "ab34");
        for(int[] i : encoded) System.out.println(i[0] + "," + i[1]);
        System.out.println(decipher(matrix, encoded));
    }

    public static List<int[]> cipher(String[][] matrix, String word){
        List<int[]> res = new ArrayList<>();
        for (char c : word.toCharArray()){
           for(int i=0; i< matrix.length; i++){
                int index = Arrays.asList(matrix[i]).indexOf(String.valueOf(c));
                if(index != -1){
                    res.add(new int[]{i, index});
                    break;
                }
           }
        }

        return res;
    }

    public static String decipher(String[][] matrix, List<int[]> encoded){
        StringBuilder sb = new StringBuilder();
        for(int[] i : encoded){
            sb.append(matrix[i[0]][i[1]]);
        }
        return sb.toString();
    }
}
