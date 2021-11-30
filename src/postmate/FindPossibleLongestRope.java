package postmate;

/**
 * https://www.1point3acres.com/bbs/thread-641612-1-1.html
 *
 * Created by brianzhang on 6/18/20.
 */
public class FindPossibleLongestRope {

    public static void main(String[] args) {
        FindPossibleLongestRope test = new FindPossibleLongestRope();
        System.out.println(test.helper(new int[]{7, 2, 3, 4, 12}, 5));
    }

    public int helper(int ropes[], int k){

        int l = 0, r = ropes.length -1;
        int res = 0;

        while(l<=r){
            int mid = l + (r-l)/2;
            if(verify(ropes, ropes[mid], k)){
                res = ropes[mid];
                l = mid+1;
            }else{
                r = mid-1;
            }
        }

        return res;
    }

    public boolean verify(int[] ropes, int pl, int k){
        int count = 0;

        for(int rope : ropes){
            count+= rope/pl;
        }

        if(count >=k)
            return true;

        return false;
    }
}
