package lacework;

import java.util.*;

public class BoldWords {
    public static void main(String[] args) {
        // words whether it has the same length
        output("abc123x123abc", Arrays.asList("abc","x"));
        System.out.println("abc".substring(1,1));
    }

    public static void output(String str, List<String> words) {
        List<String> list = new ArrayList<>();
        for(int i=0; i<str.length();) {
            if(Character.isDigit(str.charAt(i))){
                int start = i;
                while(i< str.length() && Character.isDigit(str.charAt(i))) {
                    i++;
                }
                if(start != i) list.add(str.substring(start, i));
            }
            else if(!Character.isDigit(str.charAt(i))){
                int start = i;
                while(i < str.length() && !Character.isDigit(str.charAt(i))) {
                    if(words.contains(str.substring(start, i+1))) {
                        list.add(str.substring(start, i+1));
                        start = i+1;
                    }
                    i++;
                }
                if(start != i) list.add(str.substring(start, i));
            }
        }

        for(String s : list) {
            if(words.contains(s)) {
                System.out.print("<b>" + s + "</b>");
            } else
                System.out.print(s);
        }
    }
}
