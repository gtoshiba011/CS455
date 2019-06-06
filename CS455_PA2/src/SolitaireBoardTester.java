// Name: Po-Hao Chen
// USC NetID: pohaoche
// CSCI455 PA2
// Fall 2018


/**
 * This class is for test the function correctness of SolitaireBoard
 */
import java.util.ArrayList;
public class SolitaireBoardTester {
    public static void main(String[] args) {

        SolitaireBoard board = new SolitaireBoard();
        // test 1: print the random
        System.out.println("*** test 1: random ***");
        System.out.println(board.configString());

        // test 2: print the assign
        ArrayList<Integer> arrList = new ArrayList<>();
        arrList.add(9);
        arrList.add(4);
        arrList.add(2);
        arrList.add(2);
        arrList.add(4);
        arrList.add(24);
        board = new SolitaireBoard(arrList);
        System.out.println("*** test 2 ***");
        System.out.println("exp: 9 4 2 2 4 24");
        System.out.println("ans: " + board.configString());

        board.playRound();
        System.out.println("*** playRound ***");
        System.out.println("exp: 8 3 1 1 3 23 6");
        System.out.println("ans: " + board.configString());

        board.playRound();
        System.out.println("*** playRound ***");
        System.out.println("exp: 7 2 2 22 5 7");
        System.out.println("ans: " + board.configString());

        // test 3: 1, 1, ..., 1
        arrList = new ArrayList<>();
        for (int i = 0; i < 45; i++) {
            arrList.add(1);
        }
        board = new SolitaireBoard(arrList);
        System.out.println("*** test 3 ***");
        System.out.println("exp: 1 1 1 ... 1");
        System.out.println("ans: " + board.configString());

        board.playRound();
        System.out.println("*** playRound ***");
        System.out.println("exp: 45");
        System.out.println("ans: " + board.configString());

        // test 4: illegal input
        System.out.println("*** test 4 ***");
        System.out.println("exp: 45 0 0 0");
        arrList = new ArrayList<>();
        arrList.add(45);
        arrList.add(0);
        arrList.add(0);
        arrList.add(0);
        try {
            board = new SolitaireBoard(arrList);
        } catch (AssertionError e) {
            System.out.println("exp: exception error for 45 0 0 0");
        }
    }
}
