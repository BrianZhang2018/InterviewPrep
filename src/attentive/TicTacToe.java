package attentive;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

interface MoveMethod {
  public int move();
}

class SimpleMoveStrategy implements MoveMethod {
  private TicTacToe game;

  public SimpleMoveStrategy(TicTacToe t) {
    game = t;
  }

  public int move() {
    for (int i = 0; i < TicTacToe.N; i++) {
      for (int j = 0; j < TicTacToe.N; j++) {
        if (game.board[i][j] == 0)
          return (i * 3 + j + 1);
      }
    }
    return 0;
  }
}

class HumanMove implements MoveMethod {
  private TicTacToe game;

  public HumanMove(TicTacToe t) {
    game = t;
  }

  public int move() {

    String move_str;
    int move_int = 0;
    boolean valid_input = false;
    while (!valid_input) {
      System.out.print("Where to ? ");
      move_str = TicTacToe.getUserInput();
      if (Character.isDigit(move_str.toCharArray()[0])) {
        move_int = Integer.parseInt(move_str);
        if ((move_int <= (TicTacToe.N) * (TicTacToe.N)) &&
            move_int >= 1) {
          valid_input = true;
          break;
        }
      }

      if (!valid_input) {
        System.out.println("Invalid input");
        continue;
      }
    }
    return move_int;
  }

}

class TicTacToe {
  protected static final int N = 3;
  private static final int HSPACE = 20;
  protected int[][] board;
  private static BufferedReader reader =

      new BufferedReader(new InputStreamReader(System.in));

  class Player {
    private String name;
    private int player_type;
    private int player_order;
    private MoveMethod move_strategy;

    public Player(String pname, int type,
                  int order, MoveMethod move_s) {
      name = pname;
      player_type = type;
      player_order = order;
      move_strategy = move_s;
    }

    public String getName() {
      return name;
    }

    public int getPlayerType() {
      return player_type;
    }

    public int getMove() {
      return move_strategy.move();
    }
  }

  private Player player1, player2;

  public Player getplayer1() {
    return player1;
  }

  public Player getplayer2() {
    return player2;
  }

  public static String getPosDescription(int pos) {
    String str = "";
    if (pos == 5) {
      str = "center";
      return str;
    }

    if ((pos - 1) / 3 == 0) {
      str += "upper ";
    } else if ((pos - 1) / 3 == 1) {
      str += "middle ";
    } else
      str += "lower ";

    if ((pos - 1) % 3 == 0)
      str += "left";
    else if ((pos - 1) % 3 == 1)
      str += "middle";
    else
      str += "right";

    return str;
  }

  protected static String getUserInput() {
    String input = "";
    try {
      input = reader.readLine();
    } catch (IOException ex) {
      ex.printStackTrace();
    }
    return input;
  }

  public TicTacToe() {
    board = new int[N][N];
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        board[i][j] = 0;
      }
    }

    System.out.println("Enter player name");
    player1 = new Player(getUserInput(), 2, 0, new HumanMove(this));

    player2 = new Player("", 1, 1, new SimpleMoveStrategy(this));
    System.out.println("\nHuman player " + player1.getName() +
                       " vs Computer Player" + player2.getName() + ":");

  }

  public boolean setMove(int move, int p_type) {
    int x_coord = (move - 1) / 3;
    int y_coord = (move - 1) % 3;

    if (board[x_coord][y_coord] == 0) {
      board[x_coord][y_coord] = p_type;
      return true;
    } else {
      System.out.println("Invalid move");
      return false;
    }
  }

  private enum WinConfig {
    DRAW, WIN, NONE
  }

  private WinConfig isWinningConfig() {
    WinConfig w = WinConfig.WIN;
    // rows
    for (int i = 0; i < N; i++) {
      if ((board[i][0] != 0) && (board[i][0] == board[i][1]) &&
          (board[i][0] == board[i][2]))

      {
        return w;
      }
    }
    // columns
    for (int i = 0; i < N; i++) {
      if ((board[0][i] != 0) && (board[0][i] == board[1][i]) &&
          (board[0][i] == board[2][i]))

      {
        return w;
      }
    }
    // diags
    if ((board[0][0] != 0) && (board[0][0] == board[1][1]) &&
        (board[0][0] == board[2][2])) {
      return w;
    }

    if ((board[2][0] != 0) && (board[2][0] == board[1][1]) &&
        (board[2][0] == board[0][2]))

    {
      return w;
    }

    // draw
    w = WinConfig.DRAW;
    for (int i = 0; i < N; i++)
      for (int j = 0; j < N; j++) {
        if (board[i][j] == 0)
          w = WinConfig.NONE;
      }
    return w;

  }

  private String getRowString(int row) {
    String s = "";
    for (int i = 0; i < N; i++) {
      switch (board[row][i]) {
        case 0:
          s += " ";
          break;
        case 1:
          s += "O";
          break;
        case 2:
          s += "X";
      }

      if (i != N - 1) {
        s += " | ";
      }
    }

    s += String.format("%" + HSPACE + "s", "");

    for (int i = 0; i < N; i++) {
      s += row * 3 + i + 1;

      if (i == N - 1) {
        s += "\n";
      } else {
        s += " | ";
      }
    }
    return s;
  }

  public String toString() {
    String s = "";
    // iterate through the rows
    for (int i = 0; i < N; i++) {
      s += getRowString(i);
    }
    return s;
  }

  public static void main(String[] args) {
    System.out.println("Welcome to Tic-Tac-Toe.");
    System.out.println("");

    TicTacToe game = new TicTacToe();
    String move_str;
    int move1 = 0;
    int move2 = 0;
    int player_type = 0;
    WinConfig w = WinConfig.NONE;

    System.out.println("Please make your move selection by entering "
                       + "a number 1-9 corresponding to the movement "
                       + "key on the right.\n");
    System.out.println(game.toString());

    while (game.isWinningConfig() == WinConfig.NONE) {
      do {
        move1 = game.getplayer1().getMove();
      } while (!game.setMove(move1, game.getplayer1().getPlayerType()));


      if ((w = game.isWinningConfig()) == WinConfig.WIN) {
        System.out.println("");
        System.out.println(game.toString());
        System.out.println("You have beaten my poor AI!");
        break;
      } else if (w == WinConfig.DRAW) {
        System.out.println("");
        System.out.println(game.toString());
        System.out.println("Well played. It is a draw!");
        break;
      }

      move2 = game.getplayer2().getMove();
      System.out.println("");
      System.out.println("You have put an X in the " +
                         TicTacToe.getPosDescription(move1) +
                         ". I will put a O in the " +
                         TicTacToe.getPosDescription(move2) + ".");

      game.setMove(move2, game.getplayer2().getPlayerType());

      if (game.isWinningConfig() == WinConfig.WIN) {
        System.out.println("");
        System.out.println(game.toString());
        System.out.println("I won. Thanks for playing.");
        break;
      }
      System.out.println(game.toString());
    }

  }
}
