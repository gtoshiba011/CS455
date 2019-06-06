// Name: Po-Hao Chen
// USC NetID: pohaoche
// CS 455 PA4
// Fall 2018

import java.io.FileNotFoundException;
import java.util.*;

/**
 * This class has main function and other helper function
 */
public class WordFinder {
    /**
     * main
     * 1. argument: file name (optional)
     * HOW TO RUN
     * 1. after compile, enter "java WordFinder [filename]"
     * @param args file name
     */
    public static void main(String[] args) {

        String fileName;
        if (args.length != 0) {
            fileName = args[0];
        }
        else {
            fileName = "sowpods.txt";
        }

        // create dictionary
        AnagramDictionary ad;
        try {
            ad = new AnagramDictionary(fileName);
        }
        catch (FileNotFoundException e) {
            System.out.println("Cannot find file " + fileName + ", please try again.");
            return;
        }

        System.out.println("Type . to quit.");
        Scanner in = new Scanner(System.in);
        boolean done = false;
        do {
            System.out.print("Rack? ");
            String word = in.next();

            if (word.equals(".")) {
                done = true;
                continue;
            }

            Rack rack = new Rack(word);
            printRackInfo(rack, ad);

            if(in.hasNextLine()){
                in.nextLine();
            }
        } while (!done);
    }

    /**
     * display all anagram word of the rack
     * 1. get all subset of the rack
     * 2. create anagramFreqMap: key: anagram word, value: score of the word
     * 3. for each subset, get all anagram words
     * 4. for each anagram word, save the word the score to map
     * 5. sort the entry of the map
     * 6. display information
     * @param rack input rack
     * @param ad anagram dictionary
     */
    private static void printRackInfo(Rack rack, AnagramDictionary ad) {

        ArrayList<String> subsetList = rack.getAllSubsets();
        Map<String, Integer> anagramFreqMap = new HashMap<>();
        for (String subset: subsetList) {
            ArrayList<String> anagramList = ad.getAnagramsOf(subset);
            if (anagramList.size() != 0) {
                for (String anagram: anagramList) {
                    anagramFreqMap.put(anagram, ScoreTable.getScore(anagram));
                }
            }
        }

        // sort the anagram list by freq and alphabet
        System.out.println("We can make " + anagramFreqMap.size() + " words from \"" + rack.getWord() + "\"");
        if (anagramFreqMap.size() != 0) {
            System.out.println("All of the words with their scores (sorted by score):");
            List<Map.Entry<String, Integer>> tempList = new ArrayList<>(anagramFreqMap.entrySet());
            Collections.sort(tempList, new AnagramComparator());
            for (Map.Entry<String, Integer> entry : tempList) {
                System.out.println(entry.getValue() + ": " + entry.getKey());
            }
        }
    }
}

/**
 * comparator for anagram entry
 * compare priority:
 * 1. entry's value (score)
 * 2. entry's key (alphabetical order)
 */
class AnagramComparator implements Comparator<Map.Entry<String, Integer>> {
    public int compare(Map.Entry<String, Integer> a, Map.Entry<String, Integer> b) {
        int freqCompare = -Integer.compare(a.getValue(), b.getValue());
        if (freqCompare != 0) {
            return freqCompare;
        }
        return a.getKey().compareTo(b.getKey());
    }
}