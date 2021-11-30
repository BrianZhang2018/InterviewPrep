package linkedin.phoneScreening.mine;

import category.model.NestedInteger;
import java.util.List;

/**
 * https://www.lintcode.com/problem/nested-list-weight-sum/description
 * DFS
 * Created by brianzhang on 10/28/20.
 */
public class NestedListWeightSumI {

    public int depthSum(List<NestedInteger> nestedList) {
        return dfs(nestedList, 1);
    }

    public int dfs(List<NestedInteger> nestedIntegers, int depth) {
        if(nestedIntegers == null || nestedIntegers.size() == 0) return 0;

        int sum = 0;
        for(NestedInteger ni : nestedIntegers){
            if(ni.isInteger()){
                sum+= ni.getInteger() * depth;
            }else{
                sum+= dfs(ni.getList(), depth+1);
            }
        }

        return sum;
    }
}
