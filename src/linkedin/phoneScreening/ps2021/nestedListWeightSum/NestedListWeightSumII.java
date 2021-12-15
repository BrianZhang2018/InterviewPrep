package linkedin.phoneScreening.ps2021.nestedListWeightSum;

import category.model.NestedInteger;
import java.util.*;

/**
 * Let NN be the total number of nested elements in the input list.
 * For example, the list [ [[[[1]]]], 2 ] contains 4 nested lists and 2 nested integers (1 and 2), so N=6 for that particular case.
 *
 * Time complexity : O(N).
 */
public class NestedListWeightSumII {
    public int depthSumInverse(List<NestedInteger> nestedList) {
        int sum=0, cumulative=0;

        while(nestedList.size() !=0) {
            List<NestedInteger> next = new ArrayList();

            for(NestedInteger n : nestedList) {
                if(n.isInteger()) {
                    cumulative += n.getInteger();
                }else{
                    next.addAll(n.getList());
                }
            }
            sum+=cumulative;
            nestedList = next;
        }

        return sum;
    }
}