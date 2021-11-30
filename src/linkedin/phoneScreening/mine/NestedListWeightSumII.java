package linkedin.phoneScreening.mine;

import category.model.NestedInteger;
import java.util.*;

/**
 * https://www.lintcode.com/problem/nested-list-weight-sum-ii/description
 *
 * Created by brianzhang on 10/25/20.
 */
public class NestedListWeightSumII {

    public int depthSumInverse(List<NestedInteger> nestedList) {

        int sum = 0, count = 0;

        while(nestedList != null) {
            List<NestedInteger> next = new ArrayList<>();

            for(NestedInteger ni : nestedList){
                if(ni.isInteger()){
                    count+= ni.getInteger(); // accumulate the root value from bottom to up
                }else{
                    next.addAll(ni.getList());
                }
            }

            sum+=count;
            nestedList = next;
        }

        return sum;
    }
}
