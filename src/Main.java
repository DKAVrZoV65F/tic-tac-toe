import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println("1-9");
        printTableMapping();
        char[][] gameTable = {
                {' ', ' ', ' '},
                {' ', ' ', ' '},
                {' ', ' ', ' '}
        };

        if (new Random().nextBoolean()) {
            makeComputerMove(gameTable);
            printGameTable(gameTable);
        }

        while (true) {
            makeUserMove(gameTable);
            printGameTable(gameTable);

            if (isUserWin(gameTable)) {
                System.out.println("You win!");
                break;
            }

            if (isDraw(gameTable)) {
                System.out.println("Sorry, draw!");
                break;
            }

            makeComputerMove(gameTable);
            printGameTable(gameTable);

            if (isComputerWin(gameTable)) {
                System.out.println("Computer win!");
                break;
            }
            if (isDraw(gameTable)) {
                System.out.println("Sorry, draw!");
                break;
            }
        }

        System.out.println("Game over!");
    }


    private static void printTableMapping() {
        char[][] mappingTable = {
                {'7', '8', '9'},
                {'4', '5', '6'},
                {'1', '2', '3'}
        };
        printGameTable(mappingTable);
    }

    private static void printGameTable(char[][] gameTable) {
        for (int i = 0; i < 3; i++) {
            System.out.println("-------------");

            for (int j = 0; j < 3; j++) {
                System.out.print("| " + gameTable[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("-------------\n");
    }


    private static void makeUserMove(char[][] gameTable) {
        while (true) {
            System.out.println("1-9");
            String string = new Scanner(System.in).nextLine();
            if (string.length() == 1) {
                char ch = string.charAt(0);
                if (ch >= '1' && ch <= '9') {
                    if (makeUserMoveToCell(gameTable, ch)) return;
                }
            }
        }
    }

    private static boolean makeUserMoveToCell(char[][] gameTable, char digit) {
        char[][] mappingTable = {
                {'7', '8', '9'},
                {'4', '5', '6'},
                {'1', '2', '3'}
        };
        for (int i = 0; i < mappingTable.length; i++) {
            for (int j = 0; j < mappingTable[i].length; j++) {
                if(mappingTable[i][j] == digit) {
                    if (gameTable[i][j] == ' ') {
                        gameTable[i][j] = 'X';
                        return true;
                    } else {
                        System.out.println("Can't make a move! Try again!");
                        return false;
                    }
                }
            }
        }

        return false;
    }

    private static void makeComputerMove(char[][] gameTable) {
        Random random = new Random();
        while (true) {
            int row = random.nextInt(3);
            int col = random.nextInt(3);

            if (gameTable[row][col] == ' ') {
                gameTable[row][col] = '0';
                return;
            }
        }
    }


    private static boolean isWinner(char[][] gameTable, char ch) {
        for (int i = 0; i < 3; i++) {
            if (gameTable[i][0] == gameTable[i][1] && gameTable[i][0] == gameTable[i][2]
                    && gameTable[i][0] == ch) {
                return true;
            }
        }

        for (int i = 0; i < 3; i++) {
            if (gameTable[0][i] == gameTable[1][i] && gameTable[0][i] == gameTable[2][i]
                    && gameTable[0][i] == ch) {
                return true;
            }
        }

        if (gameTable[0][0] == gameTable[1][1] && gameTable[0][0] == gameTable[2][2]
                && gameTable[0][0] == ch) {
            return true;
        }

        return gameTable[0][2] == gameTable[1][1] && gameTable[0][2] == gameTable[2][0]
                && gameTable[0][2] == ch;
    }

    private static boolean isComputerWin(char[][] gameTable) {
        return isWinner(gameTable, '0');
    }

    private static boolean isUserWin(char[][] gameTable) {
        return isWinner(gameTable, 'X');
    }

    private static boolean isDraw(char[][] gameTable) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (gameTable[i][j] == ' ') {
                    return false;
                }
            }
        }

        return true;
    }
}