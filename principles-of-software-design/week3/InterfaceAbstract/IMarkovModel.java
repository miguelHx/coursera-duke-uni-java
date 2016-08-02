
/**
 * 
 * @author Miguel Hernandez 
 * @version Nil
 */

public interface IMarkovModel {
    public void setTraining(String text);
    
    public String getRandomText(int numChars);
    
    public void setRandom(int seed);
    
}
