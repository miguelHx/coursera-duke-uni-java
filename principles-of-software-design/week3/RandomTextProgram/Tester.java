
/**
 * Write a description of Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;
public class Tester {
    public void testGetFollows() {
        MarkovOne markov = new MarkovOne();
        FileResource fr = new FileResource();
		String st = fr.asString();
		st = st.replace('\n', ' ');
		//st = "this is a test yes a test";
		markov.setRandom(1024);
		markov.setTraining(st);
		String test = "he";
		ArrayList<String> follows = markov.getFollows(test);
		System.out.println("Number of characters that follow " + test + ": " + follows.size());
    }

}
