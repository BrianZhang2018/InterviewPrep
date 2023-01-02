package linkedin.phoneScreening;

/**
 * https://www.lintcode.com/problem/find-the-celebrity/description
 * Created by brianzhang on 11/3/20.
 */
public class FindCelebrity {
    // easy group in-outdegree solution
    public int findCelebrity(int n) {
        int[] indegree = new int[n];
        int[] outdegree = new int[n];
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                if(i!= j && knows(i, j)) {
                    indegree[j]++;
                    outdegree[i]++;
                }
            }
        }

        for(int i=0; i<n; i++) {
            if(indegree[i] == n-1 && outdegree[i] == 0) {
                return i;
            }
        }

        return -1;
    }
    // logical solution
    public int findCelebrity1(int n) {
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


