// Name: Po-Hao Chen
// USC NetID: pohaoche
// CS 455 PA3
// Fall 2018


/**
  VisibleField class
  This is the data that's being displayed at any one point in the game (i.e., visible field, because it's what the
  user can see about the minefield), Client can call getStatus(row, col) for any square.
  It actually has data about the whole current state of the game, including  
  the underlying mine field (getMineField()).  Other accessors related to game status: numMinesLeft(), isGameOver().
  It also has mutators related to moves the player could do (resetGameDisplay(), cycleGuess(), uncover()),
  and changes the game state accordingly.
  
  It, along with the MineField (accessible in mineField instance variable), forms
  the Model for the game application, whereas GameBoardPanel is the View and Controller, in the MVC design pattern.
  It contains the MineField that it's partially displaying.  That MineField can be accessed (or modified) from 
  outside this class via the getMineField accessor.  
 */
public class VisibleField {
    // ----------------------------------------------------------
    // The following public constants (plus numbers mentioned in comments below) are the possible states of one
    // location (a "square") in the visible field (all are values that can be returned by public method
    // getStatus(row, col)).

    // Covered states (all negative values):
    public static final int COVERED = -1;       // initial value of all squares
    public static final int MINE_GUESS = -2;    // (yellow)
    public static final int QUESTION = -3;      // (question)

    // Uncovered states (all non-negative values):
    // values in the range [0,8] corresponds to number of mines adjacent to this square
    public static final int MINE = 9;        // (black) this loc is a mine that hasn't been guessed already (end of losing game)
    public static final int INCORRECT_GUESS = 10;  // (cross) is displayed a specific way at the end of losing game
    public static final int EXPLODED_MINE = 11;    // (red) the one you uncovered by mistake (that caused you to lose)
    // ----------------------------------------------------------
  
    // <put instance variables here>
    private final int[][] POSITION_ARR = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
    /**
     * Representation invariant:
     * 1. numSquare == mineFiled.numRows() * mineFiled.numCols()
     * 2. statesArr.length == mineField.numRows(); statesArr[0].length == mineField.numCols()
     * 3. 0 <= numUncovered <= numSquare
     * 4. 0 <= numGuess <= numSquare
     */
    private int numSquare;
    private MineField mineField;
    private int[][] statesArr;
    private int numUncovered = 0;
    private int numGuess = 0;
    private boolean isLoss = false;

    /**
        Create a visible field that has the given underlying mineField.
        The initial state will have all the mines covered up, no mines guessed, and the game
        not over.
        @param mineField  the minefield to use for for this VisibleField
     */
    public VisibleField(MineField mineField) {
        this.mineField = mineField;
        this.numSquare = this.mineField.numRows() * this.mineField.numCols();
        this.statesArr = new int[this.mineField.numRows()][this.mineField.numCols()];
        // initialize array
        for (int i = 0; i < this.mineField.numRows(); i++) {
            for (int j = 0; j < this.mineField.numCols(); j++) {
                this.statesArr[i][j] = COVERED;
            }
        }
        assert isValidVisibleField();
    }


    /**
        Reset the object to its initial state (see constructor comments), using the same underlying MineField.
    */
    public void resetGameDisplay() {
        this.numUncovered = 0;
        this.numGuess = 0;
        this.isLoss = false;
        for (int i = 0; i < this.mineField.numRows(); i++) {
            for (int j = 0; j < this.mineField.numCols(); j++) {
                this.statesArr[i][j] = COVERED;
            }
        }
        assert isValidVisibleField();
    }
  

    /**
        Returns a reference to the mineField that this VisibleField "covers"
        @return the minefield
     */
    public MineField getMineField() {
        assert isValidVisibleField();
        return this.mineField;
    }


    /**
        get the visible status of the square indicated.
        @param row  row of the square
        @param col  col of the square
        @return the status of the square at location (row, col).  See the public constants at the beginning of the class
        for the possible values that may be returned, and their meanings.
        PRE: getMineField().inRange(row, col)
     */
    public int getStatus(int row, int col) {
        assert isValidVisibleField();
        return this.statesArr[row][col];
    }


    /**
        Return the the number of mines left to guess.  This has nothing to do with whether the mines guessed are correct
        or not.  Just gives the user an indication of how many more mines the user might want to guess.  So the value can
        be negative, if they have guessed more than the number of mines in the minefield.
        @return the number of mines left to guess.
     */
    public int numMinesLeft() {
        assert isValidVisibleField();
        return this.mineField.numMines() - this.numGuess;
    }
 

    /**
        Cycles through covered states for a square, updating number of guesses as necessary.  Call on a COVERED square
        changes its status to MINE_GUESS; call on a MINE_GUESS square changes it to QUESTION;  call on a QUESTION square
        changes it to COVERED again; call on an uncovered square has no effect.
        @param row  row of the square
        @param col  col of the square
        PRE: getMineField().inRange(row, col)
     */
    public void cycleGuess(int row, int col) {
        if (this.statesArr[row][col] == COVERED) {
            this.statesArr[row][col] = MINE_GUESS;
            this.numGuess++;
        }
        else if (this.statesArr[row][col] == MINE_GUESS) {
            this.statesArr[row][col] = QUESTION;
            this.numGuess--;
        }
        else if (this.statesArr[row][col] == QUESTION) {
            this.statesArr[row][col] = COVERED;
        }
        assert isValidVisibleField();
    }


    /**
        Uncovers this square and returns false iff you uncover a mine here.
        If the square wasn't a mine or adjacent to a mine it also uncovers all the squares in
        the neighboring area that are also not next to any mines, possibly uncovering a large region.
        Any mine-adjacent squares you reach will also be uncovered, and form
        (possibly along with parts of the edge of the whole field) the boundary of this region.
        Does not uncover, or keep searching through, squares that have the status MINE_GUESS.
        @param row  of the square
        @param col  of the square
        @return false    iff you uncover a mine at (row, col)
        PRE: getMineField().inRange(row, col)
     */
    public boolean uncover(int row, int col) {
        if (this.mineField.hasMine(row, col)) {
            this.isLoss = true;
            this.updateLoss(row, col);
            assert isValidVisibleField();
            return false;
        }
        else {
            this.updateUncover(row, col);
            if (this.isGameOver()) {
                this.updateWin();
            }
            assert isValidVisibleField();
            return true;
        }
    }
 

    /**
        Returns whether the game is over.
        @return whether game over
     */
    public boolean isGameOver() {
        assert isValidVisibleField();
        return (this.numUncovered == (this.numSquare - this.mineField.numMines()) || this.isLoss);
    }
 

    /**
        Return whether this square has been uncovered.  (i.e., is in any one of the uncovered states,
        vs. any one of the covered states).
        @param row of the square
        @param col of the square
        @return whether the square is uncovered
        PRE: getMineField().inRange(row, col)
     */
    public boolean isUncovered(int row, int col) {
        assert isValidVisibleField();
        return this.statesArr[row][col] >= 0;
    }

 
    // <put private methods here>
    /**
     * When win the game, update all uncovered squares to be MINE_GUESS
     */
    private void updateWin() {
        for (int i = 0; i < this.mineField.numRows(); i++) {
            for (int j = 0; j < this.mineField.numCols(); j++) {
                if (!this.isUncovered(i, j)) {
                    this.statesArr[i][j] = MINE_GUESS;
                }
            }
        }
        assert isValidVisibleField();
    }


    /**
     * When loss the game at [row, col], update the squares to be
     * 1. EXPLODER_MINE
     * 2. INCORRECT_GUESS
     * 3. MINE
     * @param row
     * @param col
     * PRE: getMineField().inRange(row, col)
     */
    private void updateLoss(int row, int col) {
        for (int i = 0; i < this.mineField.numRows(); i++) {
            for (int j = 0; j < this.mineField.numCols(); j++) {
                // update EXPLODER_MINE (11)
                if (i == row && j == col) {
                    this.statesArr[i][j] = EXPLODED_MINE;
                }
                // update INCORRECT_GUESS (10)
                else if (this.statesArr[i][j] == MINE_GUESS && !this.mineField.hasMine(i, j)) {
                    this.statesArr[i][j] = INCORRECT_GUESS;
                }
                // update MINE (9)
                else if ((this.statesArr[i][j] == COVERED || this.statesArr[i][j] == QUESTION) && this.mineField.hasMine(i, j)) {
                    this.statesArr[i][j] = MINE;
                }
            }
        }
        assert isValidVisibleField();
    }


    /**
     * Recursively and automatically uncover the square (with zero adjacent mine) until
     * 1. boundary
     * 2. MINE_GUESS
     * 3. isUncover
     * @param row
     * @param col
     * PRE: getMineField().inRange(row, col)
     */
    private void updateUncover(int row, int col) {
        int numAdjMines = this.mineField.numAdjacentMines(row, col);
        numUncovered++;
        if (numAdjMines > 0) {
            this.statesArr[row][col] = numAdjMines;
        }
        else {
            this.statesArr[row][col] = numAdjMines;
            for (int i = 0; i < this.POSITION_ARR.length; i++) {
                int newRow = row + this.POSITION_ARR[i][0];
                int newCol = col + this.POSITION_ARR[i][1];
                if (this.mineField.inRange(newRow, newCol) && this.statesArr[newRow][newCol] != MINE_GUESS && !this.isUncovered(newRow, newCol)) {
                    this.updateUncover(newRow, newCol);
                }
            }
        }
        assert isValidVisibleField();
    }

    /**
     * Return true iff the visible field is in a valid state
     * 1. numSquare == mineFiled.numRows() * mineFiled.numCols()
     * 2. statesArr.length == mineField.numRows(); statesArr[0].length == mineField.numCols()
     * 3. 0 <= numUncovered <= numSquare
     * 4. 0 <= numGuess <= numSquare
     * @return Return true iff the visible field is in a valid state
     */
    private boolean isValidVisibleField() {
        // 1. numSquare == mineFiled.numRows() * mineFiled.numCols()
        if (this.numSquare != this.mineField.numRows() * this.mineField.numCols()) {
            return false;
        }
        // 2. statesArr.length == mineField.numRows()
        //    statesArr[0].length == mineField.numCols()
        if (this.statesArr.length != this.mineField.numRows() || this.statesArr[0].length != this.mineField.numCols()) {
            return false;
        }
        // 3. 0 <= numUncovered <= numSquare
        if (this.numUncovered < 0 || this.numUncovered > this.numSquare) {
            return false;
        }
        // 4. 0 <= numGuess <= numSquare
        if (this.numGuess < 0 || this.numGuess > this.numSquare) {
            return false;
        }
        return true;
    }
}