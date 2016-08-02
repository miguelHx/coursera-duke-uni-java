
/**
 * Write a description of MarkovModel here.
 * 
 * @author Miguel Hernandez 
 * @version Nil
 */
import java.util.*;

public class MarkovModel extends AbstractMarkovModel {
    int markovNum;
    
    public MarkovModel(int N) {
        myRandom = new Random();
        markovNum = N;
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String s) {
        myText = s.trim();
    }
    
    public String getRandomText(int numChars) {
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length()-markovNum);
        String key = myText.substring(index, index+markovNum);
        sb.append(key);
        
        for (int k=0; k < numChars-markovNum; k++) {
            ArrayList<String> follows = getFollows(key);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key = key.substring(1) + next;
        }
        
        return sb.toString();
        
    }
    
    public String toString() {
        return "MarkovModel of order " + markovNum + ".";
    }
    
}
