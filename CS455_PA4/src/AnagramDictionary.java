// Name: Po-Hao Chen
// USC NetID: pohaoche
// CS 455 PA4
// Fall 2018

import java.io.FileNotFoundException;
import java.util.*;
import java.io.File;


/**
    A dictionary of all anagram sets.
    Note: the processing is case-sensitive; so if the dictionary has all lower
    case words, you will likely want any string you test to have all lower case
    letters too, and likewise if the dictionary words are all upper case.
 */

public class AnagramDictionary {

    /**
     * Representation invariant:
     * 1. anagramMap.size >= 0
     */
    // Instance Variable
    // HashMap; key: ordered String, value: ArrayList<String>
    private Map<String, ArrayList<String>> anagramMap;


    /**
        Create an anagram dictionary from the list of words given in the file
        indicated by fileName.
        PRE: The strings in the file are unique.
        @param fileName  the name of the file to read from
        @throws FileNotFoundException  if the file is not found
     */
    public AnagramDictionary(String fileName) throws FileNotFoundException {
        anagramMap = new HashMap<>();
        Scanner in = new Scanner(new File(fileName));
        while (in.hasNext()) {
            String word = in.nextLine().trim();
            char[] wordCharArr = word.toCharArray();
            Arrays.sort(wordCharArr);
            String sortedWord = new String(wordCharArr);
            if (!anagramMap.containsKey(sortedWord)) {
                anagramMap.put(sortedWord, new ArrayList<>());
            }
            anagramMap.get(sortedWord).add(word);
        }
    }


    /**
        Get all anagrams of the given string. This method is case-sensitive.
        E.g. "CARE" and "race" would not be recognized as anagrams.
        @param s string to process
        @return a list of the anagrams of s
     */
    public ArrayList<String> getAnagramsOf(String s) {
        char[] wordCharArr = s.toCharArray();
        Arrays.sort(wordCharArr);
        String sortedWord = new String(wordCharArr);
        if (anagramMap.containsKey(sortedWord)) {
            return new ArrayList<>(anagramMap.get(sortedWord));
        }
        else {
            return new ArrayList<>();
        }
    }
}
