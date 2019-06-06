// Name: Po-Hao Chen
// USC NetID: pohaoche
// CSCI455 PA2
// Fall 2018

import java.util.ArrayList;
import java.util.Scanner;
/**
 * main
 * 1. argument:
 *     1.1 -u: Prompts for the initial configuration from the user
 *     1.2 -s: Stops between every round of the gam
 * HOW TO RUN
 * 1. after compile, enter "java BulgarianSolitaireSimulator [-u] [-s]"
 * 2. if enter [-u], enter valid integer array
 * 3. if enter [-s], press "return" for each play round
*/

public class BulgarianSolitaireSimulator {

    public static void main(String[] args) {

        boolean singleStep = false;
        boolean userConfig = false;

        // parse argument
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-u")) {
                userConfig = true;
            }
            else if (args[i].equals("-s")) {
                singleStep = true;
            }
        }

        // create board
        SolitaireBoard board;
        Scanner in = new Scanner(System.in);
        if (userConfig) {
            board = createUserBoard(in);
        }
        else{
            board = new SolitaireBoard();
        }

        // start to play round
        int counter = 0;
        System.out.println("Initial configuration: " + board.configString());
        while (!board.isDone()) {
            board.playRound();
            System.out.println("[" + ++counter + "] Current configuration: " + board.configString());
            if (singleStep) {
                System.out.print("<Type return to continue>");
                in.nextLine();
            }
        }
        System.out.println("Done!");
    }
    // check case
    // 1. error check
    // 40 1 1 1 1 (!= 45)
    // 44 b 1 x (not integer)
    // 100 -50 (integer <= 0)
    // 0 45 (integer <= 0)
    // 45 0 0 0
    // 20 25 0 0 0
    // 45 x
    // 20 25 x x
    // newline (!= 45)

    // 45
    // 1 1 ... 1 1
    // 9 8 7 6 5 4 3 2 1
    // 40 1 1 1 1 1
    // 9 4 6 26

    /**
     * error check user's input
     * 1. check each number > 0 and <= CARD_TOTAL
     * 2. check each number is integer
     * 3. check total == CARD_TOTAL
     * @param input means user's input string
     * @return true if input format is valid
     */
    private static boolean inputCheck(String input) {
        int totalCard = 0;
        Scanner lineScanner = new Scanner(input);
        while (lineScanner.hasNextInt()) {
            int card = lineScanner.nextInt();
            // 1. check each number > 0
            if (card <= 0 || card > SolitaireBoard.CARD_TOTAL) {
                return false;
            }
            totalCard += card;
        }
        // 2. check whether input is integer
        // PS: when remainingStr == "" -> call .nextLine() will return exception
        String remainingStr = lineScanner.hasNext() ? lineScanner.nextLine() : "";
        if (! remainingStr.trim().equals("")) {
            return false;
        }
        // 3. check whether total cards == CARD_TOTAL
        if (totalCard != SolitaireBoard.CARD_TOTAL) {
            return false;
        }
        return true;
    }

    /**
     * create board based on user's input (parameter -u)
     * @param in Scanner(System.in) pass from main
     * @return the created board
     */
    private static SolitaireBoard createUserBoard(Scanner in) {
        System.out.println("Number of total cards is " + SolitaireBoard.CARD_TOTAL);
        System.out.println("You will be entering the initial configuration of the cards (i.e., how many in each pile).");
        System.out.println("Please enter a space-separated list of positive integers followed by newline:");
        String line = in.nextLine();
        while (!inputCheck(line)) {
            System.out.println("ERROR: Each pile must have at least one card and the total number of cards must be " + SolitaireBoard.CARD_TOTAL);
            System.out.println("Please enter a space-separated list of positive integers followed by newline:");
            // prompt again
            line = in.nextLine();
        }
        // scan each tiles
        Scanner lineScanner = new Scanner(line);
        ArrayList<Integer> arrList = new ArrayList<>();
        while (lineScanner.hasNextInt()) {
            arrList.add(lineScanner.nextInt());
        }

        return new SolitaireBoard(arrList);
    }
}