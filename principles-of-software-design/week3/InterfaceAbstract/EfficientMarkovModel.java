/**
 * 
 * @author Miguel Hernandez
 * @version nil
 */

import java.util.*;

public class EfficientMarkovModel extends AbstractMarkovModel {
    int markovNum;
    private HashMap<String, ArrayList<String>> map;
    
    public EfficientMarkovModel(int N) {
        myRandom = new Random();
        map = new HashMap<String, ArrayList<String>>();
        markovNum = N;
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String s) {
        super.setTraining(s);
        buildMap();
        printHashMapInfo();
    }
    
    public void buildMap() {
        for (int i = 0; i < myText.length() - markovNum; i++) {
            String key = myText.substring(i, i+markovNum);
            String followingKey = myText.substring(i+markovNum, i+markovNum+1);
            
            if (map.containsKey(key)) {
                map.get(key).add(followingKey);
            }
            else {
                ArrayList<String> list = new ArrayList<String>();
                list.add(followingKey);
                map.put(key, list);
            }
        }
    }
    
    public ArrayList<String> getFollows(String key) {
        return map.get(key);
    }
    
    public String getRandomText(int numChars) {
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length()-markovNum);
        String key = myText.substring(index, index+markovNum);
        sb.append(key);
        
        for (int k=0; k < numChars-markovNum; k++) {
            ArrayList<String> follows = getFollows(key);
            if (follows == null) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key = key.substring(1) + next;
        }
        
        return sb.toString();
        
    }
    
    public void printHashMapInfo() {
        if (map.size() < 50) {
            System.out.println(map);
        }
        System.out.println("Number of keys: " + map.size());
        int max = 0;
        for (String key : map.keySet()) {
            max = Math.max(max, map.get(key).size());
        }
       System.out.printf("Map size:\t%d\nMax size:\t%d\n", map.size(), max);
    }
    
    public String toString() {
        return "MarkovModel of order " + markovNum + ".";
    }
}
