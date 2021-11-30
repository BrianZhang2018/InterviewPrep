package amazon.oa2021;

/**
 * https://leetcode.com/problems/robot-bounded-in-circle/
 *
 * Created by brianzhang on 3/7/21.
 */
public class RobotBoundedInCircle1041 {
    public boolean isRobotBounded(String instructions) {
        int x =0, y=0;
        int dir = 1;

        for(char c : instructions.toCharArray()){
            if(c == 'G'){
                if(dir == 1){
                    y++;
                }else if(dir == 2){
                    x++;
                }else if(dir == 3){
                    y--;
                }else{
                    x--;
                }

            }else if(c == 'L'){
                dir = dir == 1 ? 4 : dir -1;
            }else if(c == 'R'){
                dir = dir == 4 ? 1 : dir +1;
            }
        }

        return x==0 && y==0 || dir > 1;
    }
}
