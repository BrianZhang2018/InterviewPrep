package microsoft.ms2020.vo;

/**
 * Created by brianzhang on 10/19/20.
 */
public class ConvertNumXToY {

    int minSteps = Integer.MIN_VALUE;

/*    public int solution(int x, int y){


    }*/


    public void dfs(int x, int y, int temp, int steps){
        if(temp == y){
            minSteps = Math.min(temp, minSteps);
        }


        dfs(x, y, temp + x*2, steps++);
        dfs(x, y, temp + x-1, steps++);
    }
}
