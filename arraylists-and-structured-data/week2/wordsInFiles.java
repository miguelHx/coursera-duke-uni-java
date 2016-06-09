/**
 * Calculates the amount of words in a file
 * 
 * @author Miguel Hernandez
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
import java.io.*;
public class wordsInFiles {
    
    private HashMap<String, ArrayList<String>> map;
    
    public wordsInFiles() {
       map = new HashMap<String, ArrayList<String>>();
    }
    
    private void addWordsFromFile(File f) {
        FileResource fr = new FileResource(f.getPath());
        for (String w : fr.words()) {
            if (!map.containsKey(w)) {
                //System.out.println("Adding \"" + w + "\" to hash map");
                map.put(w, new ArrayList<String>());
            }
            String fileName = f.getName();
            
            if (!map.get(w).contains(fileName)) {
                map.get(w).add(fileName);
            }
        }  
    }
    
    public void buildWordFileMap() {
        map.clear();
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            addWordsFromFile(f);
        }
    }
    
    public int maxNumber() {
        int maxNum = 0; // the size of the array list will tell us the number of file appearances
        for (ArrayList arrList : map.values()) {
            int currNumFiles = arrList.size();
            if (currNumFiles > maxNum) {
                maxNum = currNumFiles;
            }
        }
        
        return maxNum;
    }
    
    public ArrayList<String> wordsInNumFiles(int number) {
        ArrayList<String> output = new ArrayList<String>();
        for (ArrayList arrList : map.values()) {
            int currNumFiles = arrList.size();
            if (currNumFiles == number) {
                output = arrList;
            }
        }
        System.out.println("No array list was found for file appearance of " + number);
        return output;
        
    }
    
    
    private void printsFilesIn(String word) {
        System.out.println("\"" + word + "\" appears in the files: \n");
        if (!map.containsKey(word)) {
            System.out.println("Word not found");
            return;
        }
        ArrayList<String> arrList = map.get(word);
        for (String s : arrList) {
            System.out.println(s);
        }
        System.out.println("\n");
    }
    
    public void tester() {
        buildWordFileMap();
        System.out.println("Printing map...");
        for (String w : map.keySet()) {
            System.out.println(w + " : size: " + map.get(w).size());
        }
        //int number = maxNumber();
        int count = 0;
        int number = 4;
        //int number = 7;
        for (String w : map.keySet()) {
            if (map.get(w).size() == number) {
                System.out.println("Word(s) with most file appearances:\n" + w);
                printsFilesIn(w);
                count++;
            }
        }
        System.out.println("Number of words that appear in " + number + " files " + count);
        
        String word1 = "sad";
        String word2 = "red";
        word2 = "laid";
        word2 = "tree";
        ArrayList<String> arrList = map.get(word2);
        System.out.println("Files where the word " + word2 + " appears in:\n");
        for (String s : arrList) {
            System.out.println(s);
        }
    }
}
