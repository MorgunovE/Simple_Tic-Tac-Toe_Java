package tictactoe;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[][] grid = {
            {'_', '_', '_'},
            {'_', '_', '_'},
            {'_', '_', '_'}
        };
        printGrid(grid);

        boolean xTurn = true;
        while (true) {
            System.out.print("> ");
            String move = scanner.nextLine();
            String[] coordinates = move.split(" ");

            if (coordinates.length != 2 || !isNumeric(coordinates[0]) || !isNumeric(coordinates[1])) {
                System.out.println("You should enter numbers!");
                continue;
            }

            int row = Integer.parseInt(coordinates[0]) - 1;
            int col = Integer.parseInt(coordinates[1]) - 1;

            if (row < 0 || row > 2 || col < 0 || col > 2) {
                System.out.println("Coordinates should be from 1 to 3!");
                continue;
            }

            if (grid[row][col] != '_') {
                System.out.println("This cell is occupied! Choose another one!");
                continue;
            }

            grid[row][col] = xTurn ? 'X' : 'O';
            printGrid(grid);

            String result = analyzeGameState(grid);
            if (!result.equals("Game not finished")) {
                System.out.println(result);
                break;
            }

            xTurn = !xTurn;
        }
    }

    private static void printGrid(char[][] grid) {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    private static String analyzeGameState(char[][] grid) {
        int xCount = 0;
        int oCount = 0;
        boolean xWins = false;
        boolean oWins = false;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[i][j] == 'X') xCount++;
                if (grid[i][j] == 'O') oCount++;
            }
        }

        for (int i = 0; i < 3; i++) {
            if (grid[i][0] == grid[i][1] && grid[i][1] == grid[i][2]) {
                if (grid[i][0] == 'X') xWins = true;
                if (grid[i][0] == 'O') oWins = true;
            }
            if (grid[0][i] == grid[1][i] && grid[1][i] == grid[2][i]) {
                if (grid[0][i] == 'X') xWins = true;
                if (grid[0][i] == 'O') oWins = true;
            }
        }

        if (grid[0][0] == grid[1][1] && grid[1][1] == grid[2][2]) {
            if (grid[0][0] == 'X') xWins = true;
            if (grid[0][0] == 'O') oWins = true;
        }
        if (grid[0][2] == grid[1][1] && grid[1][1] == grid[2][0]) {
            if (grid[0][2] == 'X') xWins = true;
            if (grid[0][2] == 'O') oWins = true;
        }

        if (xWins && oWins || Math.abs(xCount - oCount) > 1) {
            return "Impossible";
        } else if (xWins) {
            return "X wins";
        } else if (oWins) {
            return "O wins";
        } else if (xCount + oCount == 9) {
            return "Draw";
        } else {
            return "Game not finished";
        }
    }

    private static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}