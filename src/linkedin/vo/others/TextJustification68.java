package linkedin.vo.others;

import java.util.*;
/**
 * https://leetcode.com/problems/text-justification/
 *
 * https://www.youtube.com/watch?v=qrZLQmL6fyI
 * f : 3
 * Created by brianzhang on 11/20/20.
 */
public class TextJustification68 {

    public static void main(String[] args) {
        fullJustify(new String[]{"What","must","be","acknowledgment","shall","be"}, 16);
    }

    public static List<String> fullJustify(String[] words, int maxWidth) {
        List<String> res = new ArrayList<>();
        int index = 0, n = words.length;

        while(index < n){
            int totalChars = words[index].length();
            int last = index +1; // "last" exclusive from current line

            while(last < n){
                if(totalChars + words[last].length() + 1 > maxWidth){
                    break;
                }
                totalChars+= words[last].length() + 1; // 1 means at least one space between two words
                last++;
            }

            int gaps = last - index -1; // number of gap in current line
            StringBuilder sb = new StringBuilder();
            if(last == n || gaps == 0){ // the last line or the line only have one word
                for(int i=index; i<last; i++){
                    sb.append(words[i] + ' ');
                }

                sb.deleteCharAt(sb.length() -1);
                while(sb.length() < maxWidth) sb.append(' ');

            }else{ // middle line
                int spaces = (maxWidth-totalChars) / gaps; // evenly distributed additional spaces need to be added
                int rest = (maxWidth-totalChars) % gaps; // mod here, so the value only can be 0 or 1, that means only leftmost gap may have 1 more space.

                for(int i=index; i<last-1; i++) {
                    sb.append(words[i]);
                    sb.append(' ');

                    for(int j=0; j<spaces + (i-index<rest? 1 : 0); j++){  // i-index<rest? 1 : 0 used to determine whehter need add one more space there.
                        sb.append(' ');
                    }
                }

                sb.append(words[last-1]);
            }

            res.add(sb.toString());
            index = last;
        }

        return res;
    }
}
