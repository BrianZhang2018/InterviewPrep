package square.my2022onsite.game;

// single player
enum Direction {
    NORTH("^"), EAST(">"), WEST("<"), SOUTH("V");
    String sign;
    Direction(String sign) {
        this.sign = sign;
    }
}

public class GameSinglePlayer {
    int[][] board;
    int row, column;
    Direction direction;
    public GameSinglePlayer(int numOfRow, int numOfColumn) {
        board = new int[numOfRow][numOfColumn];
    }

    public boolean placePlayer(int r, int c, Direction direction) {
        if (r < 0 || c < 0 || r >= board.length || c >= board[0].length) {
            return false;
        }
        this.row = r;
        this.column =c;
        this.direction = direction;
        return true;
    }

    public void movePlayerByCommand(String command) {
        char[] moves = command.toCharArray();
        for(char move : moves) {
            calculate(move);
        }
    }

    private void calculate(char move) {
        int m = board.length, n = board[0].length;
        if (move == 'F') {
            switch (this.direction) {
                case NORTH:
                    if (this.row == 0) {
                        this.row = m - 1;
                    } else {
                        this.row -= 1;
                    }
                    break;
                case SOUTH:
                    if (this.row == m - 1) {
                        this.row = 0;
                    } else {
                        this.row += 1;
                    }
                    break;
                case EAST:
                    if (this.column == n - 1) {
                        this.column = 0;
                    } else {
                        this.column += 1;
                    }
                    break;
                case WEST:
                    if (this.column == 0) {
                        this.column = n - 1;
                    } else {
                        this.column -= 1;
                    }
                    break;
                default:
                    break;
            }
        }
        else if(move == 'L') { // direction turn Left 90 degree
            Direction newDirection = null;
            switch (this.direction) {
                case NORTH:
                case SOUTH:
                    newDirection = Direction.WEST;
                    break;
                case EAST:
                    newDirection = Direction.NORTH;
                    break;
                case WEST:
                    newDirection = Direction.SOUTH;
                    break;
                default:
                    System.out.println("invalid direction: " + direction);
                    break;
            }
            this.direction = newDirection;
        }
        else if(move == 'R') { //  direction turn right 90 degree
            Direction newDirection = null;
            switch (this.direction) {
                case NORTH:
                case SOUTH:
                    newDirection = Direction.EAST;
                    break;
                case EAST:
                    newDirection = Direction.SOUTH;
                    break;
                case WEST:
                    newDirection = Direction.NORTH;
                    break;
                default:
                    break;
            }
            this.direction = newDirection;
        }
    }

    public void printBoard() {
        if (this.board == null) return;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (i == this.row && j == this.column) {
                    System.out.print(this.direction.sign);
                } else {
                    System.out.print("-");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        GameSinglePlayer game = new GameSinglePlayer(3, 3);
        if(!game.placePlayer(1,1, Direction.EAST)) {
            System.out.println("invalid input");
            return;
        }
        game.printBoard();
        System.out.println("After Move:");
        game.movePlayerByCommand("FFL");
        game.printBoard();
    }
}