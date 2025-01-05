import java.sql.SQLOutput;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private final static int BOARD_SIZE = 9;
    public static void main(String[] args) {
        int [][]board = {{2, 1, 0, 0, 6, 0, 0, 0, 0},
                {0, 3, 0, 0, 0, 0, 0, 0, 0},
                {6, 0, 0, 0, 0, 0, 4, 9, 7},
                {0, 0, 0, 0, 0, 0, 3, 0, 4},
                {0, 0, 0, 0, 0, 9, 0, 6, 0},
                {0, 7, 3, 0, 5, 0, 8, 0, 0},
                {5, 0, 0, 4, 0, 7, 9, 0, 1},
                {0, 0, 2, 0, 1, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0}
        };

        printBoard(board);

        if (solveBoard(board)) {
            System.out.println("Board is solved: ");
        }
        else {
            System.out.println("Board is unsolved");
        }
        printBoard(board);
    }
    private static boolean isNumberInRow(int[][] board, int number, int row) {
        for (int j = 0; j < BOARD_SIZE; j++) {
            if (board[row][j] == number)
                return true;
        }
        return false;
    }
    private static boolean isNumberInColumn(int[][] board, int number, int column) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (board[i][column] == number)
                return true;
        }
        return false;
    }
    private static boolean isNumberInSquare(int[][] board, int number, int row, int column) {
        int local_row = row - row % 3;
        int local_column = column - column % 3;
        for (int i = local_row; i < local_row + 3; i++) {
            for (int j = local_column; j < local_column + 3; j++) {
                if (board[i][j] == number)
                    return true;
            }
        }
        return false;
    }
    private static boolean isValidNumber(int[][] board, int number, int row, int column) {
        return !(isNumberInRow(board, number, row))&&
                !(isNumberInColumn(board, number, column))&&
                !(isNumberInSquare(board, number, row, column));
    }
    private static boolean solveBoard(int[][]board) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (board[i][j] == 0) {
                    for (int numberToTry = 1; numberToTry <= BOARD_SIZE; numberToTry++) {
                        if (isValidNumber(board, numberToTry, i, j)) {
                            board[i][j] = numberToTry;
                            if (solveBoard(board)) {
                                return true;
                            } else {
                                board[i][j] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
    private static void printBoard(int[][] board) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            if ((i % 3 == 0) && (i != 0))
                System.out.println("-----------");
            for (int j = 0; j < BOARD_SIZE; j++) {
                if ((j % 3 == 0) && (j != 0))
                    System.out.print("|");
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }
}