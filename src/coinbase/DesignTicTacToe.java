package coinbase;

/**
 * https://yeqiuquan.blogspot.com/2017/04/348-design-tic-tac-toe.html
 *
 * 这种方法的思路是，如果玩家1在第一行某一列放了一个子，那么rows[0]自增1，如果玩家2在第一行某一列放了一个子，则rows[0]自减1，
 * 那么只有当rows[0]等于n或者-n的时候，表示第一行的子都是一个玩家放的，则游戏结束返回该玩家即可，其他各行各列，对角线和逆对角线都是这种思路
 *
 * Created by brianzhang on 7/8/20.
 */
public class DesignTicTacToe {
    private int[] rows;
    private int[] cols;
    private int diagonal;
    private int antiDiagonal;

    public DesignTicTacToe(int n) {
        rows = new int[n];
        cols = new int[n];
        diagonal = 0;
        antiDiagonal = 0;
    }

    /**
     * Player {player} makes a move at ({row}, {col}).
     * @param row    The row of the board.
     * @param col    The column of the board.
     * @param player The player, can be either 1 or 2.
     * @return The current winning condition, can be either:
     * 0: No one wins.
     * 1: Player 1 wins.
     * 2: Player 2 wins.
     */
    public int move(int row, int col, int player) {
        int currPlayer = player == 1 ? 1 : -1;
        rows[row] += currPlayer;
        cols[col] += currPlayer;

        if (row == col) diagonal += currPlayer;
        if (row == cols.length - col - 1)  antiDiagonal += currPlayer;

        int n = cols.length;
        if (Math.abs(rows[row]) == n ||
                Math.abs(cols[col]) == n ||
                Math.abs(diagonal) == n ||
                Math.abs(antiDiagonal) == n) {
            return player;
        }
        return 0;
    }
}
