import java.util.Scanner;

public class Main {
    private static final int ROW = 3;
    private static final int COL = 3;
    private static String board[][] = new String[ROW][COL];
    private static final String playerOne = "X";
    private static final String playerTwo = "O";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String currentPlayer;
        int count;
        boolean playerSwitch = true; // True = X | False = O
        int playerRow, playerCol;
        boolean isNotRunning;
        boolean playAgain = true;



        while(playAgain) {
            clearBoard();
            count = 0;
            isNotRunning = false;
            currentPlayer = playerOne;

            do{
                display();
                playerRow = SafeInput.getRangedInt(sc, "Player: [" + currentPlayer + "] Row", 1, ROW) - 1;
                playerCol = SafeInput.getRangedInt(sc, "Player: [" + currentPlayer + "] Column", 1, COL) - 1;

                if(isValidMove(playerRow, playerCol)){
                    board[playerRow][playerCol] = currentPlayer;
                    count++;

                    if(count >= 5) {
                        if(isWin(currentPlayer)){
                            System.out.println("Player: " + currentPlayer + " is the Winner!");
                            display();
                            isNotRunning = true;
                        } else if(count == 9){
                            display();
                            System.out.println("It's a Tie!");
                            isNotRunning = true;
                        }
                    }

                    // Switch players
                    currentPlayer = currentPlayer.equals(playerOne) ? playerTwo : playerOne;
                } else {
                    System.out.println("[Error]: Invalid Move");
                }
            } while(!isNotRunning);

            playAgain = SafeInput.getYNConfirm(sc, "Do you want to play again?");
        }


    }

    private static void clearBoard(){
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                board[i][j] = "#";
            }
        }
    }

    private static void display() {
        System.out.println("========================");
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("========================");
    }

    private static boolean isValidMove(int row, int col){
        return board[row][col].equals("#");
    }

    private static boolean isWin(String player){
        return isColWin(player) || isRowWin(player) || isDiagnalWin(player);
    }

    private static boolean isColWin(String currentPlayer){
        for (int i = 0; i < COL; i++) {
            if(board[0][i].equals(currentPlayer) && board[1][i].equals(currentPlayer) && board[2][i].equals(currentPlayer)){
                return true;
            }
        }
        System.out.println("Col");
        return false;
    }
    private static boolean isRowWin(String currentPlayer){
        for (int i = 0; i < COL; i++) {
            if(board[i][0].equals(board[i][1]) && board[i][1].equals(board[i][2]) && board[i][0].equals(currentPlayer)){
                return true;
            }
        }
        System.out.println("Row");
        return false;
    }
    private static boolean isDiagnalWin(String currentPlayer){
        if(board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2]) && board[0][0].equals(currentPlayer)){
            return true;
        }
        System.out.println("Diagnal");
        return board[0][2].equals(board[1][1]) && board[1][1].equals(board[2][0]) && board[0][2].equals(currentPlayer);
    }

    private static boolean isTie(String currentPlayer, int row, int col){
        return !isValidMove(row, col) && !isWin(currentPlayer);
    }
}
