package grubhub.problems;

import java.util.HashSet;

/**
 * Created by brianzhang on 10/16/19.
 */
public class FindStringFromMatrix {

    public static void main(String[] args) {
        char[][] matrix = {
                {'b', 'a', 'h', 'd'},
                {'b', 'a', 'u', 'l'},
                {'b', 'a', 'l', 'l'},
                {'c', 'a', 'u', 'i'}};
        String[] words = new String[]{"ball", "caui", "hulu"};

        FindStringFromMatrix test = new FindStringFromMatrix();
        for(String str : test.getWordFromMatrix(matrix, words)){
            System.out.println(str);
        }
    }

    public HashSet<String> getWordFromMatrix(char[][] matrix, String[] words){
        HashSet<String> res = new HashSet<>();
        if(words == null || words.length == 0 || matrix == null || matrix.length ==0)
            return res;

        if(matrix.length < words[0].length() || matrix[0].length < words[0].length())
            return res;

        int m = matrix.length;
        int n = matrix[0].length;

        HashSet<String> ms = new HashSet<>();

        StringBuilder sb1;
        StringBuilder sb2;
        for(int i=0; i<m; i++){
            sb1 = new StringBuilder();
            sb2 = new StringBuilder();
            for(int j=0; j<n; j++){
                sb1.append(matrix[i][j]);
                sb2.append(matrix[j][i]);
            }
            ms.add(sb1.toString());
            ms.add(sb2.toString());
        }

        for(String word : words){
            if(ms.contains(word))
                res.add(word);
        }

        return res;
    }
}
