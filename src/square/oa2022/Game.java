package square.oa2022;

class Player {
    Direction direction;
    int row, column;

    public Player(int row, int column, Direction direction) {
        this.direction = direction;
        this.row = row;
        this.column = column;
    }
}

enum Direction {
    NORTH("^"), EAST(">"), WEST("<"), SOUTH("V");
    String sign;
    Direction(String sign) {
        this.sign = sign;
    }
}

public class Game {
    Player[][] board;

    public Game(int numOfRow, int numOfColumn) {
        board = new Player[numOfRow][numOfColumn];
    }

    public Player placePlayer(int r, int c, Direction direction) {
        if (r < 0 || c < 0 || r >= board.length || c >= board[0].length)
            return null;

        board[r][c] = new Player(r, c, direction);
        return board[r][c];
    }

    public void printBoard() {
        if (this.board == null) return;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                Player player = board[i][j];
                if (player == null) {
                    System.out.print("-");
                } else {
                    System.out.print(player.direction.sign);
                }
            }
            System.out.println();
        }
    }

    public void movePlayerByCommand(String command, Player player) {
        char[] moves = command.toCharArray();
        for(char move : moves) {
            player = calculate(player, move);
        }
    }

    private Player calculate(Player player, char move) {
        Direction direction = player.direction;
        int m = board.length, n = board[0].length;
        if (move == 'F') {
            int r = player.row, c = player.column;
            switch (direction) {
                case NORTH:
                    if (r == 0) {
                        r = m - 1;
                    } else {
                        r -= 1;
                    }
                    break;
                case SOUTH:
                    if (r == m - 1) {
                        r = 0;
                    } else {
                        r += 1;
                    }
                    break;
                case EAST:
                    if (c == n - 1) {
                        c = 0;
                    } else {
                        c += 1;
                    }
                    break;
                case WEST:
                    if (c == 0) {
                        c = n - 1;
                    } else {
                        c -= 1;
                    }
                    break;
                default:
                    break;
            }
            board[r][c] = new Player(r, c, player.direction);
            board[player.row][player.column] = null;
            player = board[r][c];
        }
        else if(move == 'L') {
            Direction newDirection = null;
            switch (direction) {
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
                    // System.out.println("invalid direction: " + direction);
                    break;
            }
            player.direction = newDirection;
        }
        else if(move == 'R') {
            Direction newDirection = null;
            switch (direction) {
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
            player.direction = newDirection;
        }

        return player;
    }

    public static void main(String[] args) {
        Game game = new Game(3, 3);
        Player player = game.placePlayer(1,1, Direction.EAST);
        game.printBoard();
        game.movePlayerByCommand("FF", player);
        game.printBoard();
    }
}