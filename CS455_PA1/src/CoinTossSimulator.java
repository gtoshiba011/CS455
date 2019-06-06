// Name: PO HAO CHEN
// USC NetID: pohaoche
// CS 455 PA1
// Fall 2018


import java.util.Random;

/**
 * class CoinTossSimulator
 *
 * Simulates trials of repeatedly tossing two coins and allows the user to access the
 * cumulative results.
 *
 * NOTE: we have provided the public interface for this class.  Do not change
 * the public interface.  You can add private instance variables, constants,
 * and private methods to the class.  You will also be completing the
 * implementation of the methods given.
 *
 * Invariant: getNumTrials() = getTwoHeads() + getTwoTails() + getHeadTails()
 *
 */
public class CoinTossSimulator {

    // Constants
    private static final int COIN_NUMBER = 2;
    private static final int HEAD = 1;
    private static final int TAIL = 0;



    // Instance variables
    private int twoHeadNum;
    private int twoTailNum;
    private int headTailNum;
    private Random randomGenerator;



    // Methods
    // Public methods
    /**
     Creates a coin toss simulator with no trials done yet.
     */
    public CoinTossSimulator() {

        this.twoTailNum = 0;
        this.twoTailNum = 0;
        this.headTailNum = 0;
        this.randomGenerator = new Random();

    }

    /**
     Runs the simulation for numTrials more trials. Multiple calls to this method
     without a reset() between them *add* these trials to the current simulation.

     @param numTrials  number of trials to for simulation; must be >= 1
     */
    public void run(int numTrials) {
        for (int i = 0; i < numTrials; i++)
        {
            toss();
        }
    }

    /**
     Get number of trials performed since last reset.
     @return total number of trial
     */
    public int getNumTrials() {
        return getTwoHeads() + getTwoTails() + getHeadTails();
    }

    /**
     Get number of trials that came up two heads since last reset.
     @return # of two heads
     */
    public int getTwoHeads() {
        return this.twoHeadNum;
    }

    /**
     Get number of trials that came up two tails since last reset.
     @return # of two tails
     */
    public int getTwoTails() {
        return this.twoTailNum;
    }

    /**
     Get number of trials that came up one head and one tail since last reset.
     @return # of one head one tail
     */
    public int getHeadTails() {
        return this.headTailNum;
    }

    /**
     Resets the simulation, so that subsequent runs start from 0 trials done
     */
    public void reset() {
        this.twoHeadNum = 0;
        this.twoTailNum = 0;
        this.headTailNum = 0;
    }

    // Private methods
    /**
     * Increase this.TwoHeadNum by one
     */
    private void incrTwoHead() {
        this.twoHeadNum++;
    }

    /**
     * Increase this.TwoTailNum by one
    */
    private void incrTwoTail() {
        this.twoTailNum++;
    }

    /**
     * Increase this.headTailNum by one
     */
    private void incrHeadTail() {
        this.headTailNum++;
    }

    /**
     Toss two coins with random generator "one time"
     0 means tail, and 1 means head
     call the incr* methods based on the results
     */
    private void toss() {
        int first = this.randomGenerator.nextInt(COIN_NUMBER);
        int second = this.randomGenerator.nextInt(COIN_NUMBER);

        if (first == HEAD && second == HEAD) {
            incrTwoHead();
        }
        else if (first == TAIL && second == TAIL) {
            incrTwoTail();
        }
        else {
            incrHeadTail();
        }
    }
}