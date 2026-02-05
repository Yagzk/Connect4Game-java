// March 7, 2025

import java.util.*;

public class ConnectFourGame {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int row = 6;
        int column = 7;

        char[][] board = new char[row][column];

        createBoard(board, row, column);        //We created an empty board by calling a method so we can build the board
        printBoard(board, row, column);         //Then we printed "| " to form a table-like board

        char player = 'A';                      // We set the starting player as A.                
        while (true) {

            System.out.println("please choose column: ");
            int choosecolumn = input.nextInt() - 1;                      //Since our index starts from 0, we subtract 1.
            if (columnCheck(choosecolumn)) //With these methods, we check whether the entered column value is valid and whether the column is full
            {
                continue;
            }
            if (columnCheck2(board, choosecolumn)) {
                continue;
            }

            dropDisc(player, board, row, choosecolumn);      // Now we can drop the discs into columns :)
            printBoard(board, row, column);                  // We printed the board again together with the disc we dropped into a column

            if (isWon(board, player)) {                        // If there is a winner, it returns true and we stop the game.
                break;
            }
            if (isDraw(board)) {                               // If the game ended in a draw, it returns true and we stop the game.
                break;
            }

            //Formula we use to switch players after each disc placement
            if (player == 'A') {
                player = 'B';
            } else if (player == 'B') {
                player = 'A';
            }

        }

    }

    /*
      The purpose of the method is to create an empty board
      @param row represents the number of rows of the board
      @param column represents the number of columns of the board
      
     */
    public static void createBoard(char[][] board, int row, int column) {

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                board[i][j] = ' ';
            }
        }

    }

    /*
    The purpose of the method is to print the board by adding the '|' shape. 
    Also, we print the A or B value assigned to board[][] in the dropDisc method on our board below
    @param board[i][j] = ' ' or  board[i][j] = player{ A,B } 
     */
    public static void printBoard(char[][] board, int row, int column) {

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                System.out.print("| " + board[i][j] + " ");
            }
            System.out.println("|");
        }
        for (int j = 1; j <= column; j++) {        //A code to print the column numbers at the bottom
            System.out.print("  " + j + " ");
        }
        System.out.println();
    }


    /*
The purpose of the method: if there is an empty space in the selected column, place the player's letter {A,B}
@param row = row
@param column = column
     */
    public static void dropDisc(char player, char[][] board, int row, int column) {

        //The for loop starts from the bottom row because we will place letters starting from the bottom. It moves upward each time by rows.
        for (int i = row - 1; i >= 0; i--) {
            if (board[i][column] == ' ') {
                board[i][column] = player;
                break;
            }

        }

    }


    /*
    It checks the board vertically, horizontally, and diagonally to see whether 4 A's or 4 B's are side by side.
    If they are, it prints the win message.
    @param row = number of rows
    @param column = number of columns

    @return Since we will use this method inside an if statement in main, we return true after a successful check to stop the game. 
    @return At the end, we return false so that the if statement in main stays false and the game can continue.
     */
    public static boolean isWon(char[][] board, char player) {

        int row = board.length;
        int column = board[0].length;  //We obtained the number of columns of the first row. We use board[0] because it is a 2D array.

        /* 
         Horizontal check
         We check 4 adjacent cells in a row, and if all of them equal the player, we print the win message.
         */
        for (int i = 0; i < row; i++) {
            for (int j = 0; j <= column - 4; j++) {
                if (board[i][j] == player && board[i][j + 1] == player && board[i][j + 2] == player && board[i][j + 3] == player) {
                    System.out.println("player " + player + " wins");
                    return true;
                }

            }

        }

        /*Vertical check
        We check 4 cells on top of each other in a column, and if all of them equal the player, we print the win message.
         */
        for (int i = 0; i < row - 3; i++) {
            for (int j = 0; j < column; j++) {
                if (board[i][j] == player && board[i + 1][j] == player && board[i + 2][j] == player && board[i + 3][j] == player) {
                    System.out.println("player " + player + " wins");
                    return true;
                }

            }

        }

        /* Diagonal up-right
         It checks by increasing one row and one column each step: 1 row up, 1 column right.
         */
        for (int i = 0; i <= row - 4; i++) {
            for (int j = 0; j <= column - 4; j++) {
                if (board[i][j] == player && board[i + 1][j + 1] == player
                        && board[i + 2][j + 2] == player && board[i + 3][j + 3] == player) {
                    System.out.println("Player " + player + " wins!");
                    return true;
                }
            }
        }

        /* Diagonal down-left
        It checks by increasing the row and decreasing the column each step: 1 row down, 1 column left.
         */
        for (int i = 0; i <= row - 4; i++) {
            for (int j = 3; j < column; j++) {
                if (board[i][j] == player && board[i + 1][j - 1] == player
                        && board[i + 2][j - 2] == player && board[i + 3][j - 3] == player) {
                    System.out.println("Player " + player + " wins!");
                    return true;
                }
            }
        }

        return false;

    }

    /*
Method that checks whether there is a draw.
If all columns in the top row are full, it prints that the game ended in a draw.
Since we will use it inside an if statement in main:
@return false for continuity
@return true to stop the game
@param column = number of columns
     */
    public static boolean isDraw(char[][] board) {

        int column = board[0].length;

        for (int j = 0; j < column; j++) {
            if (board[0][j] == ' ') {
                return false;
            }
        }
        System.out.println("The game is DRAW! there is no winner");
        return true;

    }


    /*
Method to warn the user if they enter a column number outside the valid range.
Since we will use it inside an if statement in main:
@return true makes the if condition true
@return false makes the if condition false so it does not run.
@param column = column number.
     */
    public static boolean columnCheck(int column) {

//If the entered column number is less than 0 or greater than 6, it prints this warning.
        if (column < 0 || column > 6) {
            System.out.println("-----------------------------------");
            System.out.println("please type 1-6 intervals of column");
            System.out.println("-----------------------------------");
            return true;

        }
        return false;

    }


    /*  If the top cell of the selected column is not empty, it prints a warning that you cannot place in that column.
@return true (since it will be inside an if statement in main, we return true/false)
     */
    public static boolean columnCheck2(char[][] board, int column) {
        if (board[0][column] != ' ') {
            System.out.println("------------------------------------------------------------------");
            System.out.println("you cannot place it in this column! Please choose another column!!");
            System.out.println("------------------------------------------------------------------");
            return true;
        }
        return false;
    }

}
