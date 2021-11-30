package amazon.oa2021;

import java.util.*;
/**
 * https://www.1point3acres.com/bbs/thread-716400-1-1.html
 *
 * https://www.1point3acres.com/bbs/thread-716744-1-1.html
 *
 * Created by brianzhang on 2/13/21.
 */
public class ShoppingOptions {

    public static void main(String[] args) {
        System.out.println(getNumberOfOptions(new int[]{2,3}, new int[]{4}, new int[]{2,3}, new int[]{1,2}, 10));

        ShoppingOptions so = new ShoppingOptions();
        System.out.println(so.getNumberOfOptions2(new int[]{2,3}, new int[]{4}, new int[]{2,3}, new int[]{1,2}, 10));
        for(List<Integer> l : so.res) System.out.println(Arrays.toString(l.toArray()));

        LinkedList<Integer> l = new LinkedList<>();
        l.add(10);
        l.addFirst(2);

        System.out.println(l.poll());

    }

    // Solution-1: similar with 4sumII solution
    public static int getNumberOfOptions(int[] jeans, int[] shoes, int[] skirts, int[] tops, int dollar){
        int res = 0;
        TreeMap<Integer, Integer> tm = new TreeMap<>();

        for(int i=0; i<jeans.length; i++){
            for(int j=0; j<shoes.length; j++){
                int sum = jeans[i] + shoes[j];
                tm.put(sum, tm.getOrDefault(sum, 0) + 1);
            }
        }

        for(int i=0; i<skirts.length; i++){
            for(int j=0; j<tops.length; j++){
                int sum = skirts[i] + tops[j];
                NavigableMap<Integer, Integer> sm = tm.headMap(dollar -sum, true);
                for(Map.Entry<Integer, Integer> e : sm.entrySet()){
                    res += e.getValue();
                }
            }
        }

        return res;
    }

    // Solution-2: backtracking
    List<List<Integer>> res = new ArrayList<>();
    int dollar = 0;

    public int getNumberOfOptions2(int[] jeans, int[] shoes, int[] skirts, int[] tops, int dollar){

        List<int[]> prices = Arrays.asList(jeans, shoes, skirts, tops);
        this.dollar = dollar;
        backtrack(prices, 0, 0, 0, new ArrayList<>());

        return res.size();
    }

    public void backtrack(List<int[]> prices, int start, int indexOfPrice, int spend, List<Integer> count){
        if(count.size() == prices.size() && spend <=dollar) {
            res.add(new ArrayList<>(count));
            return;
        }

        if(start >= prices.size() || spend > dollar) return;

        for(int i=start; i<prices.size();){
            int[] priceOptions = prices.get(i);
            spend += priceOptions[indexOfPrice];
            count.add(priceOptions[indexOfPrice]);

            backtrack(prices, i+1, 0, spend, count);

            spend -= priceOptions[indexOfPrice];
            count.remove(count.size()-1);

            if(indexOfPrice + 1 < priceOptions.length){
                indexOfPrice++;
            }else {
                indexOfPrice=0;
                i++;
            }
        }
    }
}
