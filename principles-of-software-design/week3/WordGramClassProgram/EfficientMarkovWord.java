/**
 * 
 * @author Miguel Hernandez 
 * @version nil
 */
import java.util.*;
public class EfficientMarkovWord implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    private int myOrder;
    private HashMap<WordGram, ArrayList<String>> map;
    
    public EfficientMarkovWord(int order) {
        myRandom = new Random();
        myOrder = order;
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
        myText = text.split("\\s+");
    }
    
    private HashMap<WordGram, ArrayList<String>> buildMap() {
        HashMap<WordGram, ArrayList<String>> output = new HashMap<WordGram, ArrayList<String>>();
        int i = 0;
        //while the current location of myText is less than the array's length minus the order
        while (i < myText.length - (myOrder - 1)) {
            //create a new WordGram object that starts at the current location of myText
            WordGram wg = new WordGram(myText, i, myOrder); // wg has a length of myOrder
            
            //if the wg string arr (key) is not in the hashmap
            if (!output.containsKey(wg)) {
                if (i + myOrder < myText.length) {
                    //add new value to output with key of wg and value of word that follows
                    output.put(wg, new ArrayList<String>(Arrays.asList(myText[i + myOrder])));
                    //System.out.println("Word that follows wordgram: " + myText[i + myOrder]);
                }
            }
            //otherwise, if wg is already in hashmap
            else if (output.containsKey(wg)) {
                if (i + myOrder <  myText.length) {
                    //get entry and replace with curr value + value of word that follows
                    ArrayList<String> currVals = output.get(wg);
                    currVals.add(myText[i + myOrder]);
                    output.replace(wg, currVals);
                    //System.out.println("Words in wordgram: " + currVals);
                }
            }
            // if the wordgram string array is not in the hashmap and we are at the end of myText array
            else if (!output.containsKey(wg)) {
                if (i + myOrder == myText.length) {
                    //create a new entry with key of wg and empty value set
                    output.put(wg, new ArrayList<String>());
                }
            }
            i++;
        }
        return output;
    }
    
    public void printHashMapInfo() {
        /*
        if (map.size() <= 50) {
            System.out.println(map);
        }
        */
        System.out.println("Number of keys in map: " + map.size() + "(-1)\n");
        int max = 0;
        for (ArrayList<String> arrList : map.values()) {
            if (arrList.size() > max) {
                max = arrList.size();
            }
        }
        System.out.println("Largest value in map: " + max + "\n");
        System.out.println("Keys with largest value in map:\n");
        for (WordGram wg : map.keySet()) {
            if (map.get(wg).size() == max) {
                System.out.println(wg + "\n");
            }
        }
    }
    
    private int indexOf(String[] words, WordGram target, int start) {
        for (int i = start; i < words.length - target.length(); i++) {
            if (words[i].equals(target.wordAt(0))) {
                boolean targetFound = true;
                for (int j = 1; j < target.length(); j++) {
                    if (!words[i+j].equals(target.wordAt(j))) {
                        targetFound = false;
                        break;
                    }
                }
                if (targetFound) {
                    return i;
                }
            }
        }
        return -1;
    }
    
    public String getRandomText(int numWords){
        map = buildMap();
        printHashMapInfo();
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-myOrder);  // random word to start with
        WordGram kGram = new WordGram(myText, index, myOrder);
        sb.append(kGram.toString());
        sb.append(" ");
        for(int k=0; k < numWords-1; k++){
            ArrayList<String> follows = getFollows(kGram);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            kGram = kGram.shiftAdd(next);
        }
        
        return sb.toString().trim();
    }
    
    private ArrayList<String> getFollows(WordGram kGram) {
        ArrayList<String> follows = new ArrayList<String>();
        int pos = 0;
        while (pos < myText.length - kGram.length()) {
            int start = indexOf(myText, kGram, pos);
            if (start == -1) {
                break;
            }
            if (start + kGram.length() >= myText.length) {
                break;
            }
            String next = myText[start+kGram.length()];
            follows.add(next);
            pos = start + kGram.length();
        }
        return follows;
    }
}
