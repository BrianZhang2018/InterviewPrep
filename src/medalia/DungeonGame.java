package medalia;

/**
 * https://leetcode.com/problems/dungeon-game/
 *
 * https://leetcode.com/problems/dungeon-game/discuss/52805/Best-solution-I-have-found-with-explanations
 *
 * Created by brianzhang on 2/21/21.
 */
public class DungeonGame {

    public static void main(String[] args) {
        System.out.println(calculateMinimumHP(new int[][]{{-2,-3,3},{-5,-10,1},{10,30,-5}}));
    }

    public static int calculateMinimumHP(int[][] dungeon) {
        int m = dungeon.length, n = dungeon[0].length;
        int[][] miniInitHealth = new int[m][n];

        // Top-down DP - start from destination
        for(int i=m-1; i>=0; i--){
            for(int j=n-1; j>=0; j--){
                if(i == m-1 && j== n-1){
                    miniInitHealth[i][j] = Math.max(1, 1 - dungeon[i][j]);
                }else if(i == m-1){
                    miniInitHealth[i][j] = Math.max(1, miniInitHealth[i][j+1] - dungeon[i][j]);
                }else if(j== n-1){
                    miniInitHealth[i][j] = Math.max(1, miniInitHealth[i+1][j] - dungeon[i][j]);
                }else{
                    miniInitHealth[i][j] = Math.max(1, Math.min(miniInitHealth[i+1][j], miniInitHealth[i][j+1]) - dungeon[i][j]);
                }
            }
        }

        return miniInitHealth[0][0];
    }
}
