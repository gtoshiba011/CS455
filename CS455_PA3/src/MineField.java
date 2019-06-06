// Name: Po-Hao Chen
// USC NetID: pohaoche
// CS 455 PA3
// Fall 2018

import java.util.Random;
/** 
    MineField
        class with locations of mines for a game.
        This class is mutable, because we sometimes need to change it once it's created.
        mutators: populateMineField, resetEmpty
        includes convenience method to tell the number of mines adjacent to a location.
 */
public class MineField {

    private final int[][] POSITION_ARR = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
    /**
     * Representation invariant:
     * 1. mineData.length == numRows, mineData[0].length == numCols
     * 2. numSquare == numRows * numCols
     * 3. number of 'true' in minData == numMines if isMineFieldSet
     * 4. 0 <= numMines <= numSquare
     */
    // <put instance variables here>
    private int numRows;
    private int numCols;
    private boolean[][] mineData;
    private int numSquare;
    private int numMines;
    private boolean isMineFieldSet;
    // for 3 parameter Constructor
    private Random randomGenerator;

    /**
        Create a minefield with same dimensions as the given array, and populate it with the mines in the array
        such that if mineData[row][col] is true, then hasMine(row,col) will be true and vice versa.  numMines() for
        this minefield will corresponds to the number of 'true' values in mineData.
     * @param mineData  the data for the mines; must have at least one row and one col.
     */
    public MineField(boolean[][] mineData) {
        this.numRows = mineData.length;
        this.numCols = mineData[0].length;
        this.mineData = mineData.clone();
        this.numSquare = this.numRows * this.numCols;
        this.isMineFieldSet = true;
        // count the number of mines
        this.numMines = 0;
        for (boolean[] row: mineData) {
            for (boolean square: row) {
                if (square) {
                    this.numMines++;
                }
            }
        }
        assert isValidMineField();
    }


    /**
        Create an empty minefield (i.e. no mines anywhere), that may later have numMines mines (once
        populateMineField is called on this object).  Until populateMineField is called on such a MineField,
        numMines() will not correspond to the number of mines currently in the MineField.
        @param numRows  number of rows this minefield will have, must be positive
        @param numCols  number of columns this minefield will have, must be positive
        @param numMines    number of mines this minefield will have,  once we populate it.
        PRE: numRows > 0 and numCols > 0 and 0 <= numMines < (1/3 of total number of field locations).
     */
    public MineField(int numRows, int numCols, int numMines) {
        this.numRows = numRows;
        this.numCols = numCols;
        this.mineData = new boolean[numRows][numCols];
        this.numSquare = numRows * numCols;
        this.numMines = numMines;
        this.isMineFieldSet = false;
        this.randomGenerator = new Random();
        assert isValidMineField();
    }


    /**
        Removes any current mines on the minefield, and puts numMines() mines in random locations on the minefield,
        ensuring that no mine is placed at (row, col).
        @param row the row of the location to avoid placing a mine
        @param col the column of the location to avoid placing a mine
        PRE: inRange(row, col)
     */
    public void populateMineField(int row, int col) {
        this.resetEmpty();
        int count = 0;
        while (count != this.numMines) {
            int randomNum = this.randomGenerator.nextInt(this.numSquare);
            int targetRow = randomNum / this.numCols;
            int targetCol = randomNum % this.numCols;
            if ((targetRow == row && targetCol == col) || this.mineData[targetRow][targetCol]) {
                continue;
            }
            this.mineData[targetRow][targetCol] = true;
            count++;
        }
        this.isMineFieldSet = true;
        assert isValidMineField();
    }


    /**
        Reset the minefield to all empty squares.  This does not affect numMines(), numRows() or numCols()
        Thus, after this call, the actual number of mines in the minefield does not match numMines().
        Note: This is the state the minefield is in at the beginning of a game.
     */
    public void resetEmpty() {
        for (int i = 0; i < this.numRows; i++) {
            for (int j = 0; j < this.numCols; j++) {
                this.mineData[i][j] = false;
            }
        }
        this.isMineFieldSet = false;
        assert isValidMineField();
    }


  /**
      Returns the number of mines adjacent to the specified mine location (not counting a possible
      mine at (row, col) itself).
      Diagonals are also considered adjacent, so the return value will be in the range [0,8]
      @param row  row of the location to check
      @param col  column of the location to check
      @return  the number of mines adjacent to the square at (row, col)
      PRE: inRange(row, col)
    */
    public int numAdjacentMines(int row, int col) {
        int count = 0;
        for (int i = 0; i < this.POSITION_ARR.length; i++) {
            int newRow = row + this.POSITION_ARR[i][0];
            int newCol = col + this.POSITION_ARR[i][1];
            if (this.inRange(newRow, newCol) && this.mineData[newRow][newCol]) {
                count++;
            }
        }
        assert isValidMineField();
        return count;
    }


    /**
        Returns true iff (row,col) is a valid field location.  Row numbers and column numbers
        start from 0.
        @param row  row of the location to consider
        @param col  column of the location to consider
        @return whether (row, col) is a valid field location
    */
    public boolean inRange(int row, int col) {
        assert isValidMineField();
        return 0 <= row && row < this.numRows && 0 <= col && col < this.numCols;
    }


    /**
        Returns the number of rows in the field.
        @return number of rows in the field
    */
    public int numRows() {
        assert isValidMineField();
        return this.numRows;
    }


    /**
        Returns the number of rows in the field.
        @return number of rows in the field
    */
    public int numCols() {
        assert isValidMineField();
        return this.numCols;
    }


    /**
        Returns whether there is a mine in this square
        @param row  row of the location to check
        @param col  column of the location to check
        @return whether there is a mine in this square
        PRE: inRange(row, col)
    */
    public boolean hasMine(int row, int col) {
        assert isValidMineField();
        return this.mineData[row][col];
    }


    /**
        Returns the number of mines you can have in this minefield.  For mines created with the 3-arg constructor,
        some of the time this value does not match the actual number of mines currently on the field.  See doc for that
        constructor, resetEmpty, and populateMineField for more details.
     * @return number of mines in the field
     */
    public int numMines() {
        assert isValidMineField();
        return this.numMines;
    }

    // <put private methods here>

    /**
     * Return true iff the mine field is in a valid state
     * 1. mineData.length == numRows, mineData[0].length == numCols
     * 2. numSquare == numRows * numCols
     * 3. number of 'true' in minData == numMines if isMineFieldSet
     * 4. 0 <= numMines <= numSquare
     * @return Return true iff the mine field is in a valid state
     */
    private boolean isValidMineField() {
        // 1. mineData.length == numRows, mineData[0].length == numCols
        if (this.mineData.length != this.numRows || this.mineData[0].length != this.numCols) {
            return false;
        }
        // 2. numSquare == numRows * numCols
        if (this.numSquare != this.numRows * this.numCols) {
            return false;
        }
        // 3. number of 'true' in minData == numMines
        int count = 0;
        for (int i = 0; i < this.numRows; i++) {
            for (int j = 0; j < this.numCols; j++) {
                if (this.mineData[i][j]) {
                    count++;
                }
            }
        }
        if (this.isMineFieldSet && count != this.numMines) {
            return false;
        }
        // 4. 0 <= numMines <= numSquare
        if (this.numMines < 0 || this.numMines > this.numSquare) {
            return false;
        }
        return true;
    }
}

