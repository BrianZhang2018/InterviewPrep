package linkedin.phoneScreening.ps2021.nestedListWeightSum;

import category.model.NestedInteger;

import java.util.List;

public class NestedListWeightSumI {

    public int depthSum(List<NestedInteger> nestedList) {
        return helper(nestedList, 1);
    }

    public int helper(List<NestedInteger> list, int depth) {
        int res = 0;
        for(NestedInteger ni : list) {
            if(!ni.isInteger()) {
                res+=helper(ni.getList(), depth +1);
            }else{
                res+= depth * ni.getInteger();
            }
        }

        return res;
    }
}
