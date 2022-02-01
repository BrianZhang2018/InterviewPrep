package linkedin.vo.my2022VO;

import java.util.*;

public class FindReplaceString {
    public static void main(String[] args) {
         System.out.println(solution("abbcbbf", "bb", "tttt"));
        System.out.println(findReplaceString("abcd", new int[]{0,2}, new String[]{"a", "cb"}, new String[]{"eee","ffff"}));
    }

    public static String solution(String s1, String s2, String s3) {
        List<Integer> list = new ArrayList();
        for(int i=0; i<s1.length(); i++) {
            if(s1.startsWith(s2, i)) {
                list.add(i);
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<s1.length();) {
            if(list.contains(i)) {
                sb.append(s3);
                i+=s2.length();
            }else{
                sb.append(s1.charAt(i));
                i++;
            }
        }

        return sb.toString();
    }

    public static String findReplaceString(String S, int[] indexes, String[] sources, String[] targets) {
        Map<Integer, Integer> table = new HashMap<>();
        for (int i=0; i<indexes.length; i++) {
            // if a match is found in the original string, record it
            if (S.startsWith(sources[i], indexes[i])) {
                table.put(indexes[i], i);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<S.length(); ) {
            if (table.containsKey(i)) {
                // if a replacement was recorded before
                sb.append(targets[table.get(i)]);
                i+=sources[table.get(i)].length();
            } else {
                // if no replacement happened at this index
                sb.append(S.charAt(i));
                i++;
            }
        }
        return sb.toString();
    }

}
