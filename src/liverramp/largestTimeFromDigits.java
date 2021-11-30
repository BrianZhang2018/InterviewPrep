package liverramp;

/**
 * https://leetcode.com/problems/largest-time-for-given-digits/
 *
 * Created by brianzhang on 8/16/20.
 */
public class largestTimeFromDigits {

    public static void main(String[] args) {
        System.out.println(solution(new int[]{1,2,3,4}));
    }

    public static String solution(int[] A) {
        String res = "";
        for(int i=0; i<4; i++){
            for(int j=0; j<4; j++){
                for(int k=0; k<4;k++){

                    if(i==j || i == k || k == j) continue;

                    String h = "" + A[i] + A[j];
                    String m = "" + A[k] + A[6-i-j-k];
                    String t = h + ":" + m;
                    if(h.compareTo("24") <0 && m.compareTo("60")<0 && res.compareTo(t)<0){
                        res = t;
                    }
                }
            }
        }

        return res;
    }
}
