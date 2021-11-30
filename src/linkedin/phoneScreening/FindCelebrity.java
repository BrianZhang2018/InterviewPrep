package linkedin.phoneScreening;

/**
 * https://www.lintcode.com/problem/find-the-celebrity/description
 *
 * Created by brianzhang on 11/3/20.
 */
public class FindCelebrity {

    public int findCelebrity(int n) {

        int candidate = 0;
        for(int i=1; i<n; i++){
            if(knows(candidate, i)){
                candidate = i;
            }
        }

        for(int i=0; i<n; i++){
            if(i!=candidate){
                if(knows(candidate, i) || !knows(i, candidate)){
                    return -1;
                }
            }
        }

        return candidate;
    }

    // provided mocked method
    public boolean knows(int i, int j){
        return true;
    }
}


