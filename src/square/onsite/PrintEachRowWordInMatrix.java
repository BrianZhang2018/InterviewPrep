package square.onsite;

/**
 * https://www.1point3acres.com/bbs/thread-668159-1-1.html
 * https://www.1point3acres.com/bbs/thread-560912-1-1.html
 *
 * freq: 2
 *
 * Created by brianzhang on 4/8/21.
 */
public class PrintEachRowWordInMatrix {

    public static void main(String[] args) {
        char[][] matrix = new char[][]{ {' ', 'b',' ','d'},
                                        {' ', ' ', ' ', ' '},
                                        {' ', 't', 'o', 'y'}};

        String[][] matrix1 = new String[][]{{"", "b","c","d"}, {"", "", "a", "p"}, {"", "t", "o", "y"}};

        int[][] moveMatrix = new int[][]{{0,0,0,0},
                                         {0,0,0,0},
                                         {0,1,-1,1}};

        //printEachRowString(matrix1);
        System.out.println("===============");
        printEachRowString(matrix);
        System.out.println("===============");
        printEachRowStringAfterShift(matrix, moveMatrix, 2);
    }

    public static void printEachRowStringAfterShift(char[][] matrix, int[][] moveMatrix, int seconds){
        int[][] dirs = new int[][]{{-1,0}, {1, 0}};

        for(int i=0; i<moveMatrix.length; i++) {
            for(int j=0; j<moveMatrix[0].length; j++) {
                if(moveMatrix[i][j] != 0){
                    int[] dir = moveMatrix[i][j] == 1 ? dirs[1] : dirs[0];
                    int m = i, n = j;
                    for(int k=0; k<seconds; k++){
                        int nr = m + dir[0];
                        int nc = n + dir[1];

                        if(nr < 0 || nr >=matrix.length || nc < 0 || nc >= matrix[0].length || matrix[nr][nc] != ' ')
                            break;

                        matrix[nr][nc] = matrix[m][n];
                        matrix[m][n] = ' ';
                        m = nr;
                        n = nc;
                    }
                }
            }
        }

        printEachRowString(matrix);
    }

    public static void printEachRowString(String[][] matrix){
        for(String[] cs : matrix){
            System.out.println(String.join("", cs).replaceAll(" ", ""));
        }
    }

    public static void printEachRowString(char[][] matrix){
        for(char[] cs : matrix){
            System.out.println(new String(cs).replaceAll(" ", ""));
        }
    }
}
