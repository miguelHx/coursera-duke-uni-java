
/**
 * Finds the characters in a play, calculates the number of parts they have,
 * calculates the max parts using array lists.
 * 
 * @author Miguel Hernandez
 * @version Nil
 */
import edu.duke.*;
import java.util.*;


public class CharactersInPlay {
    private ArrayList<String> names;
    private ArrayList<Integer> nameCounts;
    
    public CharactersInPlay() {
        names = new ArrayList<String>();
        nameCounts = new ArrayList<Integer>();
    }
    
    private void update(String person) {
        int index = names.indexOf(person);
        if (index == -1) {
            names.add(person);
            nameCounts.add(1);
        }
        else {
            int count = nameCounts.get(index);
            nameCounts.set(index, count+1);
        }
    
    }
    
    public void findAllCharacters() {
        FileResource fr = new FileResource();
        for (String l : fr.lines()) {
            int index = l.indexOf(".");
            if (index > -1) {
                int nb = 0;
                while(nb < index) {
                    if (!l.substring(nb, nb + 1).equals(" ")) {
                        break;
                    }
                    else {
                        nb += 1;
                    }
                }
                String posChar = l.substring(nb, index);
                update(posChar);
            }
                
        }
            
            
        
    }
    
    public int findIndexOfMax(){
        int max = nameCounts.get(0);
        int maxIndex = 0;
        for(int k=0; k < nameCounts.size(); k++){
            if (nameCounts.get(k) > max){
                max = nameCounts.get(k);
                maxIndex = k;
            }
        }
        return maxIndex;
    }
    
    public void tester() {
        findAllCharacters();
        System.out.println("Finding all characters...");
        
        charactersWithNumParts(75, 250);
        int maxIndex = findIndexOfMax();
        System.out.println("most number of parts is " + nameCounts.get(maxIndex) + " by the character " + names.get(maxIndex));
    }
    
    public void charactersWithNumParts(int num1, int num2) {
        System.out.println("Finding characters with appearances between " + num1 + " and " + num2);
        for (int i = 0; i < names.size(); i++) {
            if (nameCounts.get(i) >= num1 && nameCounts.get(i) <= num2) {
                System.out.println("Character: " + names.get(i) + " has appearances between " +
                num1 + " and " + num2);
            }
        
        }
            
    }
}
