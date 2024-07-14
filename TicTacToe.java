import java.util.Scanner;

public class TicTacToe {
   private boolean isTurnX;
   private String[][] board;
   private int[][] values;
   private int moves;
   private Scanner s;
   private boolean isGameOver;
   private int row;
   private int col;
   private String winner;
   
   public static void main (String[] args) {
      TicTacToe t1 = new TicTacToe();
      while (!t1.isGameOver && t1.moves < 10) {
         t1.input();
         t1.pickSpot();
         t1.printBoard();
         t1.checkWin();
      }
      if (t1.isGameOver) {
         System.out.println("\n" + t1.winner + " wins");
      }
      if (t1.moves == 9) {
         System.out.println("\n tie");
      }
   }
   
   
   public TicTacToe() {
      isTurnX = true;
      isGameOver = false;
      row = -1;
      col = -1;
      board = new String[3][3];
      values = new int[3][3];
      moves = 0;
      winner = "";
      for (int i = 0; i < board.length; i++) {
         for (int j = 0; j < board[0].length; j++) {
            board[i][j] = "_ ";
         }
      }
      for (int i = 0; i < values.length; i++) {
         for (int j = 0; j < values[0].length; j++) {
            values[i][j] = 0;
         }
      }
      s = new Scanner(System.in);
      isGameOver = false;
   }
   
   public void printBoard () {
      for (int i = 0; i < board.length; i++) {
         for (int j = 0; j < board[i].length; j++) {
            System.out.print(board[i][j]);
         }
         System.out.println("");
      }
   }
   
   public void input() {
      while (row < 0) {
         System.out.println("What row");
         printBoard();
         row = s.nextInt() - 1;
                  
         if (row < 0 || row > 2) {
            row = -1;
            System.out.println("Invalid number");
         }
      }
      while (col < 0) {
         System.out.println("What column");
         printBoard();
         col = s.nextInt() - 1;
         
         if (col < 0 || col > 2) {
            col = -1;
            System.out.println("Invalid number");
         }
      }
   }
   
   public void pickSpot () {
      if (board[row][col].equals("_ ")) {
         if (isTurnX) {
            isTurnX = false;
            board[row][col] = "X ";
            values[row][col] = 1;
            moves ++;
         }
         else {
            isTurnX = true;
            board[row][col] = "O ";
            values[row][col] = -1;
            moves ++;
         }
      }
      else {
         System.out.println("Invalid");
      }
      row = -1;
      col = -1;
   }
   
   public void checkWin () {
      for (int i = 0; i < values.length; i++) {
         int rowSum = values[i][0] + values[i][1] + values[i][2];
         if (rowSum == 3) {
            isGameOver = true;
            winner = "X";
         }
         else if (rowSum == -3) {
            isGameOver = true;
            winner = "O";
         }
         for (int j = 0; j < values[0].length; j++) {
             int colSum = values[0][i] + values[1][i] + values[2][i];
             if (colSum == 3) {
               isGameOver = true;
               winner = "X";
             }
             else if (colSum == -3) {
               isGameOver = true;
               winner = "O";
             }
         }
      }
      
      int slantSum1 = values[0][0] + values[1][1] + values[2][2];
      int slantSum2 = values[0][2] + values[1][1] + values[2][0];
      
      if (slantSum1 == 3 || slantSum2 == 3) {
         isGameOver = true;
         winner = "X";
      }
      else if (slantSum1 == -3 || slantSum2 == -3) {
         isGameOver = true;
         winner = "O";
      }
      else if (moves == 9) {
         isGameOver = true;
         winner = "no one";
      }
   }
}