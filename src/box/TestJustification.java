package box;

import java.util.*;

/**
 * https://leetcode.com/problems/text-justification/
 * https://www.youtube.com/watch?v=qrZLQmL6fyI
 *
 * Created by brianzhang on 9/28/20.
 */
public class TestJustification {

    public static void main(String[] args) {
        List<String> res = fullJustify(new String[]{"This", "is", "an", "example", "of", "text", "justification."}, 16);
        for(String str : res) System.out.println(str);
    }

    public static List<String> fullJustify(String[] words, int maxWidth){
        List<String> res = new ArrayList<>();
        int index = 0;
        int n = words.length;

        while(index < n){
            int last = index + 1;
            int totalChars = words[index].length();
            while(last < n) {
                if(totalChars + words[last].length() + 1 > maxWidth)
                    break;

                totalChars += words[last].length() + 1;
                last++;
            }

            int gaps = last - index - 1;
            StringBuilder sb = new StringBuilder();
            if(last == n || gaps == 0) {    // handle the last line
                for(int i=index; i<last; i++){
                    sb.append(words[i]);
                    sb.append(" ");
                }

                sb.deleteCharAt(sb.length()-1);
                while(sb.length() < maxWidth)
                    sb.append(" ");
            } else {                        // handle the middle line

                int spaces = (maxWidth - totalChars) / gaps; // average spaces for all gaps
                int rest = (maxWidth - totalChars) % gaps;  // extra spaces need to be added from left

                for(int i= index; i<last-1; i++){  // last-1
                    sb.append(words[i]);
                    sb.append(" ");

                    for(int j=0; j<spaces + (i-index<rest? 1 : 0); j++){ // (i-index<rest? 1 : 0) means whether this gap need add extra space
                        sb.append(" ");
                    }
                }   // process words before the last one as they have the gaps which spaces need be taken care

                sb.append(words[last-1]);   // add last word for this line
            }

            index = last;
            res.add(sb.toString());
        }

        return res;
    }

}
