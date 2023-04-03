package tictactoe;

import java.util.Scanner;

public class Main {
    private static final int size = 3;
    private static char[][] board;
    static Scanner in = new Scanner(System.in);

    private static void showBoard() {
        System.out.println("-".repeat(size * 2 + 3));
        for (char[] row : board) {
            System.out.print("| ");
            for (int field : row) {
                System.out.printf("%c ", field);
            }
            System.out.println("|");
        }
        System.out.println("-".repeat(size * 2 + 3));
    }

    private static void init() {
        init("_".repeat(size * size));
    }

    private static void init(String iStr) {
        board = new char[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = iStr.charAt(i * size + j);
            }
        }
    }

    private static char check() {
        int[]
                countRows = new int[size],
                countColumns = new int[size];
        int
                countDiagonal1 = 0,
                countDiagonal2 = 0;
        int countDiff = 0, countAll = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int d = 0;
                if (board[i][j] == 'X') {
                    d = 1;
                    countDiff++;
                    countAll++;
                } else if (board[i][j] == 'O') {
                    d = -1;
                    countDiff--;
                    countAll++;
                }
                countRows[i] += d;
                countColumns[j] += d;
                if (i == j) {
                    countDiagonal1 += d;
                }
                if (i == size - 1 - j) {
                    countDiagonal2 += d;
                }
            }
        }
        int winsX = 0, winsO = 0;
        winsX += countDiagonal1 == size ? 1 : 0;
        winsX += countDiagonal2 == size ? 1 : 0;
        winsO += countDiagonal1 == -size ? 1 : 0;
        winsO += countDiagonal2 == -size ? 1 : 0;
        for (int i = 0; i < size; i++) {
            winsX += countRows[i] == size ? 1 : 0;
            winsX += countColumns[i] == size ? 1 : 0;
            winsO += countRows[i] == -size ? 1 : 0;
            winsO += countColumns[i] == -size ? 1 : 0;
        }
        if (Math.abs(countDiff) > 1 || winsX + winsO > 1) {
            return 'E';
        } else if (winsX == 1) {
            return 'X';
        } else if (winsO == 1) {
            return 'O';
        } else if (countAll == size * size) {
            return '-';
        } else {
            return '.';
        }
    }

    private static void turn(char symbol) {
        int row, col;
        boolean done = false;
        while (!done) {
            try {

                String rowStr = in.next();
                String colStr = in.nextLine().strip();
                row = Integer.decode(rowStr) - 1;
                col = Integer.decode(colStr) - 1;
                if (board[row][col] == '_') {
                    done = true;
                    board[row][col] = symbol;
                } else {
                    System.out.println("This cell is occupied! Choose another one!");
                }
            } catch (NumberFormatException e) {
                System.out.println("You should enter numbers!");
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.printf("Coordinates should be from 1 to %d!%n", size);
            } catch (Exception e) {
                System.out.println(e.getClass().getName());
            }

        }
    }

    public static void main(String[] args) {
        init();
        showBoard();
        char state;
        char current = 'X';
        do {
            turn(current);
            showBoard();
            state = check();
            current = current == 'X' ? 'O' : 'X';
        } while (state != 'X' && state != 'O');
        System.out.printf("%c wins", state);


    }

}
