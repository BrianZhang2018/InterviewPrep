package remitly;

import java.util.*;

/**
 * Created by brianzhang on 6/10/20.
 */
public class CurrencyStream {
    int index = 0;

    static String input = "test $10 test $20$50 test 930";

    public static void main(String[] args) {
        CurrencyStream test = new CurrencyStream();
        test.getNumbers("$", input).forEach(num -> System.out.println(num));
        // test.getNumbers(input).forEach(num -> System.out.println(num));
    }

    public List<String> getNumbers(String symbol, String input) {
        List<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        index = 0;
        while(index < input.length()){
            if(String.valueOf(input.charAt(index++)).equals(symbol)){
                while(index < input.length() && Character.isDigit(input.charAt(index))){
                    sb.append(input.charAt(index++));
                }
                res.add(sb.toString());
                sb.setLength(0);
            }
        }

        return res;
    }


   /* public List<Integer> getNumbers(String input) {

        String dollar = "$";
        StringBuilder sb = new StringBuilder();
        List<Integer> res = new ArrayList<>();

        while (hasNext()) {
            if (getNext().equals(dollar)) {
                while (hasNext()) {             // 有点sliding window的意思
                    String next = getNext();
                    if (next.trim().length() == 0)
                        continue;

                    if (Character.isDigit(next.charAt(0))) {
                        sb.append(next);
                    } else {
                        if(sb.length() > 0) res.add(Integer.valueOf(sb.toString()));

                        sb.setLength(0);

                        if (!next.equals(dollar)) {
                            break;
                        }
                    }
                }
            }
        }
        return res;
    }

    public boolean hasNext(){
        if(index < input.length()){
            index++;
            return true;
        }
        return false;
    }

    public String getNext(){
        return String.valueOf(input.charAt(index-1));
    }*/
}
