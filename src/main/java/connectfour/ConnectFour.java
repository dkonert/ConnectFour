package connectfour;

/**
 * Main class for running Connect Four Game calling both functions
 */
public class ConnectFour{

    /**
     * Main class for running program
     * @param args
     */
    public static void main(String[] args) {
        Board board = new Board(); //init board
        boolean done = false; //init variable
        int response = -1; //init variable
        int position; //init variable
        int turn = 1; //init variable
        while (response == -1) { //loops until they make a valid choice
            response = TextUI.mainMenu(); //takes response from user
            switch (response) {
                case 1: //If they want to play
                    break;
                case 2:
                    //Get file name and try to load until valid file is found
                    while (board.load(TextUI.filename()) != 0) {
                        TextUI.failToLoad(); //Error message
                    }
                    turn = board.calcTurn(); //calculates turn
                    break;
                case 3:
                    done = true; //Quit Program
                    break;
                default:
                    TextUI.invalidChoice(); //Error message
                    response = -1;
            }
        }
        while (!done) { //Loops until game is finished
            position = TextUI.turn(turn, board.toString()); //position player chooses
            if (position == -1) {
                while (board.save(TextUI.filename()) != 0) { //Get filename and tries to save file until valid
                    TextUI.failToSave(); //Error Message
                }
                return;
            }
            if (board.place(position, turn) == -1) {
                TextUI.invalidMove(); //Error message
                continue;
            }
            if (turn == 1) {
                turn = 2; //changes turn if player 1 just played to player 2
            } else {
                turn = 1; //changes turn if player 2 just played to player 1
            }
            int winner = board.winner(); //check to see if there is a winner
            if (winner != 0) {
                TextUI.winner(winner, board.toString()); //outputs winner
                done = true; //Game is done
            }
        }
    }
}