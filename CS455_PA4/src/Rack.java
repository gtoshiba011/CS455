// Name: Po-Hao Chen
// USC NetID: pohaoche
// CS 455 PA4
// Fall 2018

import java.util.ArrayList;
import java.util.Arrays;

/**
    A Rack of Scrabble tiles
 */

public class Rack {

    /**
     * Representation invariant:
     * 1. unique.length() <= word.length()
     * 2. unique.length() == mult.length
     */
    // Instance Variable
    String word;
    String unique;
    int[] mult;

    /**
     * create a rack and initialize the multiset (unique and mult)
     * @param word original input rack
     */
    public Rack(String word) {
        this.word = word;
        this.unique = "";
        if (word.length() > 0) {
            initMultiset();
        }
        else {
            mult = new int[0];
        }
    }

    /**
     * return the original input rack
     * @return original input rack
     */
    public String getWord() {
        return word;
    }

    /**
     * return all subsets of the multiset
     * @return all subset of the indicated multiset
     */
    public ArrayList<String> getAllSubsets() {
        return Rack.allSubsets(unique, mult, 0);
    }

    /**
     * Find the String unique and Arrays mult from word
     * PRE: word.length() > 0
     */
    private void initMultiset () {
        char[] wordCharArr = word.toCharArray();
        Arrays.sort(wordCharArr);
        ArrayList<Integer> tempList = new ArrayList<>();
        int count = 0;
        for (int i = 0; i < wordCharArr.length-1; i++) {
            count++;
            if (wordCharArr[i] != wordCharArr[i+1]) {
                unique = unique + wordCharArr[i];
                tempList.add(count);
                count = 0;
            }
        }
        count++;
        unique = unique + wordCharArr[wordCharArr.length-1];
        tempList.add(count);

        // copy int from tempList to mult
        mult = new int[tempList.size()];
        for (int i = 0; i < tempList.size(); i++) {
            mult[i] = tempList.get(i);
        }
    }

    /**
        Finds all subsets of the multiset starting at position k in unique and mult.
        unique and mult describe a multiset such that mult[i] is the multiplicity of the char
              unique.charAt(i).
        PRE: mult.length must be at least as big as unique.length()
              0 <= k <= unique.length()
        @param unique a string of unique letters
        @param mult the multiplicity of each letter from unique.
        @param k the smallest index of unique and mult to consider.
        @return all subsets of the indicated multiset
        @author Claire Bono
     */
    private static ArrayList<String> allSubsets(String unique, int[] mult, int k) {
        ArrayList<String> allCombos = new ArrayList<>();

        if (k == unique.length()) {  // multiset is empty
            allCombos.add("");
            return allCombos;
        }

        // get all subsets of the multiset without the first unique char
        ArrayList<String> restCombos = allSubsets(unique, mult, k+1);

        // prepend all possible numbers of the first char (i.e., the one at position k)
        // to the front of each string in restCombos.  Suppose that char is 'a'...

        String firstPart = "";             // in outer loop firstPart takes on the values: "", "a", "aa", ...
        for (int n = 0; n <= mult[k]; n++) {
            for (int i = 0; i < restCombos.size(); i++) {  // for each of the subsets
                                                                          // we found in the recursive call
                // create and add a new string with n 'a's in front of that subset
                allCombos.add(firstPart + restCombos.get(i));
            }
            firstPart += unique.charAt(k);  // append another instance of 'a' to the first part
        }

        return allCombos;
    }
}
