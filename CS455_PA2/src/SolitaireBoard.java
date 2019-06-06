// Name: Po-Hao Chen
// USC NetID: pohaoche
// CSCI455 PA2
// Fall 2018

import java.util.ArrayList;
import java.util.Random;

/*
  class SolitaireBoard
  The board for Bulgarian Solitaire.  You can change what the total number of cards is for the game
  by changing NUM_FINAL_PILES, below.  Don't change CARD_TOTAL directly, because there are only some values
  for CARD_TOTAL that result in a game that terminates.
  (See comments below next to named constant declarations for more details on this.)
*/

public class SolitaireBoard {

    public static final int NUM_FINAL_PILES = 9;
    // number of piles in a final configuration
    // (note: if NUM_FINAL_PILES is 9, then CARD_TOTAL below will be 45)

    public static final int CARD_TOTAL = NUM_FINAL_PILES * (NUM_FINAL_PILES + 1) / 2;
    // bulgarian solitaire only terminates if CARD_TOTAL is a triangular number.
    // see: http://en.wikipedia.org/wiki/Bulgarian_solitaire for more details
    // the above formula is the closed form for 1 + 2 + 3 + . . . + NUM_FINAL_PILES

    // Note to students: you may not use an ArrayList -- see assgt description for details.

    /**
     * Representation invariant:
     * 1. currentSize, which is an companion variable of pilesArr, is the number of current piles
     * 2. 0 <= currentSize <= pilesArr.length
     * 3. when currentSize > 0, card number for piles locates at pilesArr[0, currentSize -1]
     * 4. every card number pilesArr[i] which i in [0, currentSize) is > 0 and <= CARD_TOTAL
     * 5. total number of cards in pilesArr == CARD_TOTAL
    */
    // Instance Variable
    int[] pilesArr = new int[CARD_TOTAL];
    int currentSize = 0;
 
    /**
        Creates a solitaire board with the configuration specified in piles.
        piles has the number of cards in the first pile, then the number of cards in the second pile, etc.
        PRE: piles contains a sequence of positive numbers that sum to SolitaireBoard.CARD_TOTAL
    */
    public SolitaireBoard(ArrayList<Integer> piles) {
        for (Integer pile: piles) {
            this.pilesArr[this.currentSize] = pile;
            this.currentSize++;
        }
        assert isValidSolitaireBoard();
    }
 
    /**
        Creates a solitaire board with a random initial configuration.
    */
    public SolitaireBoard() {
        Random randomGenerator = new Random();
        int totalCard = 0;
        while (totalCard < CARD_TOTAL) {
            this.pilesArr[this.currentSize] = randomGenerator.nextInt(CARD_TOTAL - totalCard) + 1;
            totalCard += this.pilesArr[this.currentSize];
            this.currentSize++;
        }
        assert isValidSolitaireBoard();
    }

    /**
        Plays one round of Bulgarian solitaire.  Updates the configuration according to the rules
        of Bulgarian solitaire: Takes one card from each pile, and puts them all together in a new pile.
        The old piles that are left will be in the same relative order as before,
        and the new pile will be at the end.
    */
    public void playRound() {
        // all piles minus 1
        // use newSize to skip 0
        int newSize = 0;
        for (int i = 0; i < this.currentSize; i++) {
            this.pilesArr[i]--;
            if (this.pilesArr[i] != 0) {
                this.pilesArr[newSize] = this.pilesArr[i];
                newSize++;
            }
        }
        // add the final piles and update currentSize
        this.pilesArr[newSize] = this.currentSize;
        newSize++;
        this.currentSize = newSize;
        assert isValidSolitaireBoard();
    }

    /**
        Returns true iff the current board is at the end of the game.  That is, there are NUM_FINAL_PILES
        piles that are of sizes 1, 2, 3, . . . , NUM_FINAL_PILES, in any order.
    */
    public boolean isDone() {
        int[] checkArr = new int[NUM_FINAL_PILES];
        final int COUNT_ONE = 1;
        for (int i = 0; i < this.currentSize; i++) {
            if (this.pilesArr[i] > NUM_FINAL_PILES) {
                return false;
            }
            checkArr[this.pilesArr[i] - 1] = COUNT_ONE;
        }

        // if sum of checkArr[i] == NUM_FINAL_PILES return true
        int totalSum = 0;
        for (int num : checkArr) {
            totalSum += num;
        }
        //System.out.println(">> isDone: total " + totalSum);
        assert isValidSolitaireBoard();
        return totalSum == NUM_FINAL_PILES;
    }

    /**
        Returns current board configuration as a string with the format of
        a space-separated list of numbers with no leading or trailing spaces.
        The numbers represent the number of cards in each non-empty pile.
    */
    public String configString() {
        String configStr = "";
        for (int i = 0; i < this.currentSize; i++) {
            configStr += this.pilesArr[i] + " ";
        }
        assert isValidSolitaireBoard();
        return configStr.trim();
    }


    /**
        Returns true iff the solitaire board data is in a valid state
        (See representation invariant comment for more details.)
     * 1. currentSize is the number of current piles
     * 2. 0 <= currentSize <= pilesArr.length
     * 3. when currentSize > 0, card number for piles locates at [0, currentSize -1]
     * 4. every card number pilesArr[i] which i in [0, currentSize) is > and <= CARD_TOTAL
     * 5.total number of cards in pilesArr == CARD_TOTAL
    */
    private boolean isValidSolitaireBoard() {
        // 2.
        if (this.currentSize < 0 || this.currentSize > this.pilesArr.length) {
            return false;
        }
        int totalCard = 0;
        // 3.
        for (int i = 0; i < this.currentSize; i++) {
            totalCard += this.pilesArr[i];
            // 4.
            if (this.pilesArr[i] <= 0 || this.pilesArr[i] > CARD_TOTAL) {
                //System.out.println(">> isValid warming: [" + i + "]: " + this.pilesArr[i]);
                return false;
            }
        }
        // 5.
        if (totalCard != CARD_TOTAL) {
            //System.out.println(">> isValid warming: total card [exp: " + CARD_TOTAL + "]: " + totalCard);
            return false;
        }
        return true;
    }
}