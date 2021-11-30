package square.onsite;

import java.util.*;
/**
 * Created by brianzhang on 4/2/21.
 */

public class Chess {
    public static void main(String[] args) {
        Map<Character,String> map=new HashMap<>();
        map.put('A',".*CD");
        map.put('C',"E**F");
        Chess chess=new Chess(map, ".A.B");
        chess.play();
    }

    Map<Character, String> cToChild;
    String start;
    Chess(Map<Character,String> map, String start) {
        cToChild=map;
        this.start=start;
    }
    public void play() {
        String board=start;
        while(board!=null) {
            System.out.println("Chess board: "+board);
            System.out.println("Please input a char from the board");
            Scanner scan=new Scanner(System.in);
            char c=scan.next().charAt(0);
            if(board.indexOf(c)==-1) {
                System.out.println("Invalid input, please input a char from the board");
                continue;
            }
            board=cToChild.get(c);
        }
        System.out.println("game over");
    }
}