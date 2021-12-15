package linkedin.phoneScreening.ps2021;

import java.util.*;

/**
 * Time complexity: O(NlogN) = (the number of nodes in the recursive tree) * (the time each node takes up)
 */
public class FactorCombination {

    public static void main(String[] args) {
        System.out.println(Math.sqrt(9));
    }

    List<List<Integer>> res = new ArrayList<List<Integer>>();
    public List<List<Integer>> getFactors(int n) {
        backtracking(n, 2, new ArrayList<Integer>());
        return res;
    }

    public void backtracking(int n, int start, List<Integer> tmp) {
        if(n == 1) {
            if(tmp.size() > 1) {
                res.add(new ArrayList(tmp));
            }
            return;
        }

        for(int i=start; i<=n; i++) { // point-2: i<=n
            if(n%i == 0) { // point-1: n%i
                tmp.add(i);
                backtracking(n/i, i, tmp); // point-2: n/i
                tmp.remove(tmp.size()-1);
            }
        }
    }
}
