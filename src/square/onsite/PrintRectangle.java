package square.onsite;

import java.util.*;

/**
 * https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=715429&page=1&extra=#pid15258707
 * https://www.1point3acres.com/bbs/thread-739421-1-1.html
 *
 * freq: 2
 *
 * Created by brianzhang on 4/1/21.
 */
public class PrintRectangle {

    public static void main(String[] args) {
        printout(4, 5);
        printoutWithShift(4, 5,3, 3);
        char[][] res = printoutMultipleRectangleWithIntersected(Arrays.asList(new int[]{6,8,3,3}, new int[]{4,5,2,2}));
        for(char[] matrix : res) System.out.println(Arrays.toString(matrix));

        System.out.println();
        printInMatrixWithShift(4, 5,3, 3);
    }

    // 比如说一个长方形的长和另一个长方形的竖重叠了，你就要换成’+‘十字符号
    // Solution: draw the squares into the same matrix
    public static char[][] printoutMultipleRectangleWithIntersected(List<int[]> squares) {
        char[][] canvas = new char[20][20];

        for(int[] s : squares) {
            int height = s[0], width = s[1], offsetDown = s[2], offsetRight = s[3];
            for (int i = offsetDown; i < height+2+offsetDown; i++) {  // additional '⌜' increase the (height + 2)
                for (int j = offsetRight; j < width+2+offsetRight; j++) {
                    if ((i == offsetDown || i == height+offsetDown + 1) && (j == offsetRight || j == width+offsetRight + 1)) {
                        canvas[i][j] = '⌜';
                        continue;
                    }

                    if (i == offsetDown || i == offsetDown + height + 1) {
                        if(canvas[i][j] == '|') canvas[i][j] = '+';
                        else canvas[i][j] = '-';
                    }
                    else if (j == offsetRight || j == width + offsetRight + 1)  {
                        if(canvas[i][j] == '-') canvas[i][j] = '+';
                        else canvas[i][j] = '|';
                    }
                    else if (canvas[i][j] != 0) { // 0 means empty cell in the "char" array
                        continue;
                    }
                    else {
                        canvas[i][j] = ' ';
                    }
                }
            }
            System.out.println();
        }

        return canvas;
    }

    public static void printout(int height, int width) {
        System.out.println("height: " + height + "   " + "width: " + width);
        String l = "-", w = "|", corner = "⌜";

        for(int i=0; i<height+2; i++) { // cause the additional "⌜", we need "height+2"
            for (int j=0; j<width+2; j++) {
                if ((i == 0 || i == height+1) && (j == 0 || j == width+1)) {
                    System.out.print(corner);
                    continue;
                }

                if(i == 0 || i == height+1) System.out.print(l);

                else if(j == 0 || j == width+1) System.out.print(w);

                else System.out.print(" ");
            }

            System.out.println();
        }
    }

    public static void printoutWithShift(int height, int width, int offsetDown, int offsetRight){
        String l = "-", w = "|", corner = "⌜";

        // shift down
        while(offsetDown-- > 1) System.out.println();

        // shift right
        for(int i=0; i<height+2; i++) {
            for (int j=0; j<width+2+offsetRight; j++) {
                if(j<offsetRight){
                    System.out.print(" ");
                    continue;
                }
                // only column need shift
                if ((i == 0 || i == height+1) && (j == offsetRight || j == width+offsetRight+1)) {
                    System.out.print(corner);
                    continue;
                }

                if(i == 0 || i == height+1) System.out.print(l);

                else if(j == offsetRight || j == width+offsetRight+1) System.out.print(w);

                else System.out.print(" ");
            }

            System.out.println();
        }
    }


    public static void printInMatrixWithShift(int height, int width, int offsetDown, int offsetRight) {

        char[][] canvas = new char[20][20];

        for(int i=offsetDown; i<offsetDown+height+2; i++){
            for(int j=offsetRight; j<offsetRight+width+2; j++){
                if((i == offsetDown || i == offsetDown+height+1) && (j == offsetRight || j == offsetRight+width+1)){
                    canvas[i][j] = '⌜';
                    continue;
                }

                if(i == offsetDown || i == offsetDown+height+1) canvas[i][j] = '-';

                else if(j == offsetRight || j == offsetRight + width + 1) canvas[i][j] = '|';

                else canvas[i][j] = ' ';
            }
        }

        for(char[] c : canvas) System.out.println(Arrays.toString(c));
    }
}
