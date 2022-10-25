package companies.wayfair;

import java.util.*;
/**
 * 给你两个user的在某个网站进行的action list, 例如 ["start","/account","/notification","/a","/b"], ["start","/a","/b"], 
 * 找两个user最长共同连续action list. 如前例应该返回["/a","/b"]
 */
public class LongestConsecutiveSubList {
    public static void main(String[] args){
        List<String> bl = new ArrayList<>();
        bl.add("Start");
        bl.add("haha");
        bl.add("aa");
        bl.add("bb");

        List<String> sl = new ArrayList<String>();
        sl.add("aa");
        sl.add("bb");
        System.out.println(findConsecutiveSubList(sl, bl));
    }

    public static List findConsecutiveSubList(List<String> l1, List<String> l2){
        List<String> sl;
        List<String> bl;
        if(l1.size() > l2.size()){
            sl = l2;
            bl = l1;
        }else{
            sl = l1;
            bl = l2;
        }

        int index=0;
        int max = 0;
        int left = 0, right=0;
        while (index < sl.size()) {
            if (bl.indexOf(sl.get(index)) != -1) {
                int slIndex = index + 1;
                int start = bl.indexOf(sl.get(index));
                for (int i = start + 1; i < bl.size(); i++) {
                    if (bl.get(i).equals(sl.get(slIndex))) {
                        if (i - start > max) {
                            left = start;
                            right = i;
                            max = i - start;
                        }
                        slIndex++;
                    } else {
                        break;
                    }
                }
            }
            index++;
        }
        
        return bl.subList(left, right+1);
    }
    
}