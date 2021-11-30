package linkedin.phoneScreening;

/**
 * https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/
 *
 * binary search
 * Created by brianzhang on 11/1/20.
 */
public class CapacityToShipPackagesWithNDays {

    public int shipWithinDays(int[] weights, int D) {

        int left=0, right=0;
        for(int w: weights){
            left = Math.max(w, left);
            right += w;
        }

        while(left < right) {
            int mid = left + (right-left) /2, need=1, curr=0;

            for(int w: weights){
                if(curr+w>mid){
                    need++;
                    curr=0;
                }
                curr+=w;
            }

            if(need > D){
                left = mid+1;
            }else{
                right = mid;
            }
        }

        return left;
    }
}
