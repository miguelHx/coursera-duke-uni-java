/**
 * @author Miguel Hernandez
 */

import java.util.*;
import edu.duke.*;
import java.io.File;

public class VigenereBreaker {
    /**
     * Method sliceString
     * 
     * This method takes a string and uses a string builder to change the characters based on which index to start with (whichSlice)
     * with the total amount of indices to skip over (totalSlices).  The purpose is to encrypt using a vigenere cipher.
     *
     * @param message           message of type string that the output will be based off of.
     * @param whichSlice        the starting index of the message
     * @param totalSlices       the total amount of slices to increment with
     * @return                  returns a modified message using certain characters of the message
     */
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder msg = new StringBuilder(message);
        StringBuilder sliced = new StringBuilder();
        for (int i = whichSlice; i < message.length(); i+=totalSlices) {
            char wantedSlice = msg.charAt(i);
            sliced.append(wantedSlice);
        }

        String output = sliced.toString();
        return output;
    }

    /**
     * Method tryKeyLength
     *
     * @param encrypted         vigenere cipher encrypted message that we will work with
     * @param klength           key length, not always known, used to figure out how many keys used based on the vigenere cipher key
     * @param mostCommon        most common letter in the alphabet of a certain language, used to determine the key based on char frequency
     * @return                  returns an array of integers containing possible keys for the vigenere cipher
     */
    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] output = new int[klength];
        String[] slices = new String[klength];
        CaesarCracker ccr = new CaesarCracker(mostCommon);

        for (int i = 0; i < klength; i++) {
            slices[i] = sliceString(encrypted, i, klength);
        }
        int i = 0;
        while (i < klength) {
            output[i] = ccr.getKey(slices[i]);
            i++;
        }

        return output;
    }

    public void breakVigenere () {
        HashMap<String, HashSet<String>> langDicts = new HashMap<String, HashSet<String>>();
        System.out.println("Choose all dictionaries from the appropriate folder");
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f.getPath()); // creates a file resource based on the path of the file
            String fileName = f.getName(); // gets the name of the file, which should be the language name
            HashSet<String> currDict = readDictionary(fr); // gets the dictionary and puts it into a hashset from the method readDictionary
            langDicts.put(fileName, currDict);
            System.out.println("Mapped the language \"" + fileName + "\" to its dictionary");
        }
        
        
        System.out.println("Choose an encrypted file or file to encrypt");
        FileResource fr = new FileResource();
        String fileText = fr.asString();
        int testKeyLength = 38;
        
        breakForAllLanguages(fileText, langDicts);
        /*System.out.println("Choose the dictionary");
        FileResource fr2 = new FileResource();
        HashSet<String> dict = readDictionary(fr2);
        
        int[] keyArr = tryKeyLength(fileText, testKeyLength, mostCommonCharIn(dict));
        VigenereCipher vc = new VigenereCipher(keyArr); // new instance of the VigenereCipher class
        String decryptedMsg = vc.decrypt(fileText); // calls the decrypt method to get a possible decrypted message
        int wordCount = countWords(decryptedMsg, dict);
        System.out.println("Word count of the message with key length of " + testKeyLength + " is " + wordCount);
        */
        
        /*System.out.println("Choose the dictionary");
        FileResource fr2 = new FileResource();
        HashSet<String> dict = readDictionary(fr2);
        
        System.out.println("Printing encrypted file...\n" + fileText);
        System.out.println("Key array is ");
        
        String decrypted = breakForLanguage(fileText, dict);
        System.out.println("Decrypted message using breakForLanguage is \n" + decrypted);
        */
    }
    

    /**
     * Method readDictionary
     *                      
     * @param fr            FileResource that should contain the dictionary choice
     * @return              Returns a hashset of words in the dictionary, no repeats
     */
    public HashSet<String> readDictionary(FileResource fr) {
        HashSet<String> output = new HashSet<String>(); // new hash set to contain the dictionary words
        for (String line : fr.lines()) {
            String lineLower = line.toLowerCase();
            output.add(lineLower);

        }

        return output;
    }

    /**
     * Method countWords
     *
     * @param message           Message that will be used to compare to the dictionary
     * @param dictionary        the hashset of non-repeated words from a dictionary
     * @return                  returns the amount of words there are in the message
     */
    public int countWords(String message, HashSet<String> dictionary) {
        int count = 0;
        for (String word : message.split("\\W")) {
            String wordLower = word.toLowerCase();
            if (dictionary.contains(wordLower)) {
                count += 1;
            }
        }
        return count;
    }
    
    /**
     * Method findMax
     *
     * @param arrList           an array list of integers
     * @return                  returns the highest number from the array list of integers
     */
    public int findMax(ArrayList<Integer> arrList) {
        int max = 0;
        for (int i = 0; i < arrList.size(); i++) {
            int curr = arrList.get(i);
            if (curr > max) {
                max = curr;
            }
        }
        return max;
    }

    
    /**
     * Method breakForLanguage
     *
     * @param encrypted         encrypted string of vigenere cipher
     * @param dictionary        the hashset of dictionary words that aren't repeated
     * @return                  returns the decrypted message after trying 100 keys lengths (1-100) and choosing the correct one that
     *                          has the most reals words based on the dictionary given and choose that one as the decrypted message.
     */
    public String breakForLanguage(String encrypted, HashSet<String> dictionary) {
        int[] key_list = new int[100];
        int[] wordCount = new int[100]; // holds the word count of each potential decrypted message 
        
        for (int i = 1; i <= 100; i++) { // initializes values of 1-100 into key_list array
            key_list[i-1] = i;
        }
        
        for (int i = 0; i < 100; i++) { // tries all the keys from 0-100
            int[] key = tryKeyLength(encrypted, key_list[i], 'e'); // new array within this loop's scope that uses tryKeyLength 
            //method which returns a possible key arrangement to decrypt below
            VigenereCipher vc = new VigenereCipher(key); // new instance of the VigenereCipher class
            String decryptedMsg = vc.decrypt(encrypted); // calls the decrypt method to get a possible decrypted message
            wordCount[i] = countWords(decryptedMsg, dictionary); // stores the word count of decryptedMsg in the wordcount array based on words from dictionary
            // this is important later on to help us find the max number of words
        }
        
        // the loop below finds the max word count from wordCount and stores the index of it.  The index corresponds to the key_list (1-100)
        int largest = 0;
        int index = 0;
        for (int i = 0; i < 100; i++) { // this loop finds the max word count from wordCount and stores the index of it.  The index corresponds to the key_list (1-100)
            if (wordCount[i] > largest) {
                largest = wordCount[i];
                index = i;
            }
        }
        
        System.out.println("The largest count is "+largest);
        
        int maxKey = key_list[index];
        char mostCommonChar = mostCommonCharIn(dictionary);
        int[] keysArr = tryKeyLength(encrypted, maxKey, mostCommonChar); // using the largest index, we calculate the correct key array using tryKeyLength
        System.out.println("The keys are:");
        for (int i = 0; i < keysArr.length; i++) {
            System.out.println(keysArr[i]);
        }
        System.out.println("The key length is "+keysArr.length);
        VigenereCipher vc = new VigenereCipher(keysArr); // making a new instance of the vigenere cipher using the correct set of keys in order to get our answer
        String output = vc.decrypt(encrypted);
        return output;
    }

    /**
     * Method mostCommonCharIn
     *
     * @param dictionary        a hashset of words from a dictionary
     * @return                  returns the most common letter in the entire dictionary
     */
    public char mostCommonCharIn(HashSet<String> dictionary) {
        HashMap<Character, Integer> lettersWithCounts = new HashMap<Character, Integer>();
        //HashSet<Character> lettersFromDict = new HashSet<Character>();
        String alph = "abcdefghijklmnopqrstuvwxyz";
        for (int i = 0; i < alph.length(); i++) {
            lettersWithCounts.put(alph.charAt(i), 0);
        }
        
        
        for (String s : dictionary) { // this loop will add in letters from dictionary of any language to the non-repeating hashset above
            s = s.toLowerCase();
            for (Character c : lettersWithCounts.keySet()) {
                for (int i = 0; i < s.length(); i++) {
                    char currChar = s.charAt(i);
                    if (currChar == c) {
                        lettersWithCounts.put(c, lettersWithCounts.get(c)+1);
                    }
                    
                }
            }
        }
        
        System.out.println(lettersWithCounts);
        int max = 0;
        char output = 'e';
        
        for (Character c : lettersWithCounts.keySet()) { // finds the 
            int count = lettersWithCounts.get(c);
            if (count > max) {
                max = count;
                output = c;
            }
            
        }
        
        return output;
    }   
    
    /**
     * Method breakForAllLanguages - takes in an encrypted string and runs it through all language dictionaries
     *                               to find the best decrypted message and prints it out.
     * 
     *
     * @param encrypted         encrypted string of vigenere cipher 
     * @param languages         A hashmap with language (string) mapped to its dictionary (hashset)
     */
    public void breakForAllLanguages(String encrypted, HashMap<String, HashSet<String>> languages) {
        int idx = 0;
        ArrayList<Integer> wordCountList = new ArrayList<Integer>();
        ArrayList<String> langList = new ArrayList<String>();
        
        for (String lang : languages.keySet()) { //use break for language and countWords
            HashSet<String> currSet = languages.get(lang); // dictionary of current language in the loop
            String possibleMessage = breakForLanguage(encrypted, currSet); // possible message from language
            int currWordCount = countWords(possibleMessage, currSet); // higher word count means higher chance that curr language is correct
            wordCountList.add(currWordCount);// updating array list to help find highest word count for a language
            langList.add(lang);
            
        }
        
        System.out.println(wordCountList);
        
        int maxWordCount = findMax(wordCountList);
        int maxIndexofList = wordCountList.indexOf(maxWordCount);
        String langAnswer = langList.get(maxIndexofList);
        
        
        String decryptedMsg = breakForLanguage(encrypted, languages.get(langAnswer));
        System.out.println("Decrypted message itself is: \n\n" + decryptedMsg);
        System.out.println("\nLanguage of decyrypted message is " + langAnswer);
        
        
    }
    
    
}
