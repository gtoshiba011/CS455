// Name: Po-Hao Chen
// USC NetID: pohaoche
// CS 455 PA4
// Fall 2018

/**
 * used to get the score of each word
 * all the method in ScoreTable are public static
 */

public class ScoreTable {

    private static final int[] SCORE_ARRAY = {1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10};

    /**
     * Calculate the score of word
     * @param word the string to calculate the score
     * @return score of word
     */
    public static int getScore(String word) {
        int totalScore = 0;
        for (int i = 0; i < word.length(); i++) {
            totalScore += SCORE_ARRAY[Character.toLowerCase(word.charAt(i)) - 'a'];
        }
        return totalScore;
    }
}