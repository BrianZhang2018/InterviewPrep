package companies.amazon;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * https://leetcode.com/problems/partition-labels/
 *
 * Created by brianzhang on 3/31/20.
 */
public class PartitionLabels {

    public static void main(String[] args) {

        HashSet<Integer> set = new HashSet<>();
        set.add(1);
        System.out.println(set.add(2));

        PartitionLabels pl = new PartitionLabels();
        System.out.println(pl.partitionLabels("ababcbacadefegdehijhklij"));
    }

    public List<Integer> partitionLabels(String S) {
        List<Integer> res = new ArrayList<>();
        int[] lastIndex = new int[26];

        for(int i=0; i<S.length(); i++){
            lastIndex[S.charAt(i) - 'a'] = i;
        }

        int i=0;
        while(i<S.length()){

            int end = lastIndex[S.charAt(i) - 'a'];
            int j=i;

            while(j != end){
                end = Math.max(end, lastIndex[S.charAt(j++) - 'a']);
            }

            res.add(j-i+1);
            i = j+1;

        }

        return res;
    }
}
