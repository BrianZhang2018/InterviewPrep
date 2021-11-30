package doordash.my2021;

import java.util.Stack;

/**
 * https://leetcode.com/problems/asteroid-collision/
 *
 * Created by brianzhang on 11/27/21.
 */
public class AsteroidCollision {

    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> s = new Stack<>();
        for(int i: asteroids){
            if(i > 0){
                s.push(i);
            }else{// i is negative
                while(!s.isEmpty() && s.peek() > 0 && s.peek() < Math.abs(i)){
                    s.pop();
                }

                if(s.isEmpty() || s.peek() < 0){
                    s.push(i);
                }else if(i + s.peek() == 0){  //equal
                    s.pop();
                }else if(-i < s.peek()) {
                    continue;
                }
            }
        }

        int[] res = new int[s.size()];
        for(int i = res.length - 1; i >= 0; i--){
            res[i] = s.pop();
        }
        return res;
    }
}
