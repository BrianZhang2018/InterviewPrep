package linkedin.vo;

/**
 * f : 3
 * Created by brianzhang on 11/20/20.
 */
public class SqrtX69 {

    public int mySqrt(int x) {
        if(x == 0) return 0;

        int left= 1, right=x;

        while(left < right){
            int mid = left + (right - left)/2;

            if(mid<=x/mid && mid+1 > x/(mid+1)) return mid;

            if(mid > x/mid){
                right = mid;
            }else{
                left = mid+1;
            }
        }

        return left;
    }
}
