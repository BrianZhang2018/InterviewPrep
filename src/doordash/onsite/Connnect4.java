package doordash.onsite;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by brianzhang on 9/3/20.
 */
public class Connnect4 {

    static char[][] board = new char[6][7];  // game board
    static char player = 'X'; // start with player X

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(true){
           int col =  scanner.nextInt();
           if(dropPiece(board, col, player)){
               if(checkWin()){
                   System.out.println(player);
                   return;
               }
               switchPlayer(player);
           }
        }
    }

    public static boolean dropPiece(char board[][], int col, char player){

        if(board[0][col] != 'o') return false;

        for(int row=board.length-1; row>=0; row--){
            if(board[row][col] == 'o'){
                board[row][col] = player;
                return true;
            }
        }

        return false;
    }

    public static void fillBoard(char[][] board, char fill){
        for(char[] b : board){
            Arrays.fill(board, 0, board.length, fill);
        }
    }

    public static boolean checkWin() {

        int m = board.length, n = board[0].length;

        for(int row=0; row<m; row++)
            for(int col=0; col<n;col++){
                if(board[row][col] != 'o' && board[row][col] == board[row][col+1] && board[row][col] == board[row][col+2] && board[row][col] == board[row][col+3])
                    return true;
            }

        for(int col=0; col<n; col++)
            for(int row=0; row<m;row++){
                if(board[row][col] != 'o' && board[row][col] == board[row+1][col] && board[row][col] == board[row+2][col] && board[row][col] == board[row+3][col])
                    return true;
            }


        for(int row=0; row<m-3; row++)
            for(int col=0; col<n-3;col++){
                if(board[row][col] != 'o' && board[row][col] == board[row+1][col+1] && board[row][col] == board[row+2][col+2] && board[row][col] == board[row+3][col+3])
                    return true;
            }


        for(int row=0; row<m-3; row++)
            for(int col=0; col<n;col++){
                if(board[row][col] != 'o' && board[row][col] == board[row+1][col-1] && board[row][col] == board[row+2][col-2] && board[row][col] == board[row+3][col-3])
                    return true;
            }

        return false;
    }

    public static char switchPlayer(char currentPlayer) {
        if (currentPlayer == 'X') {
            return 'Y';
        } else {
            return 'X';
        }
    }
}