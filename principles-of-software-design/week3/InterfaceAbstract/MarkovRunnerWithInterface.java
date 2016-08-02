
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import edu.duke.*;

public class MarkovRunnerWithInterface {
    public void runModel(IMarkovModel markov, String text, int size, int seed) {
        markov.setRandom(seed);
        markov.setTraining(text);
        System.out.println("running with " + markov);
        for(int k=0; k < 3; k++){
			String st= markov.getRandomText(size);
			printOut(st);
		}
    }
    
    public void runMarkov() {
        FileResource fr = new FileResource();
		String st = fr.asString();
		st = st.replace('\n', ' ');
		int size = 200;
		
		/*
        MarkovZero mz = new MarkovZero();
        runModel(mz, st, size, 32);
    
        MarkovOne mOne = new MarkovOne();
        runModel(mOne, st, size, 32);
        */
        MarkovModel mThree = new MarkovModel(5);
        runModel(mThree, st, size, 531);
        /*
        MarkovFour mFour = new MarkovFour();
        runModel(mFour, st, size, 32);
        */
    }

	private void printOut(String s) {
		String[] words = s.split("\\s+");
		int psize = 0;
		System.out.println("----------------------------------");
		for(int k=0; k < words.length; k++){
			System.out.print(words[k]+ " ");
			psize += words[k].length() + 1;
			if (psize > 60) {
				System.out.println();
				psize = 0;
			}
		}
		System.out.println("\n----------------------------------");
	}
	
	public void testHashMap() {
	    FileResource fr = new FileResource();
	    String st = fr.asString();
	    st = st.replace('\n', ' ');
	    int size = 50;
	    int seed = 531;
	    
	    EfficientMarkovModel m5 = new EfficientMarkovModel(5);
	    runModel(m5, st, size, seed);
	}
	
	public void compareMethods() {
	    
	    FileResource fr = new FileResource();
		String st = fr.asString();
		st = st.replace('\n', ' ');
		int size = 1000;
		int seed = 42;
		
	    MarkovModel mTwo = new MarkovModel(2);
	    double begin = System.nanoTime();
        runModel(mTwo, st, size, seed);
        double end = System.nanoTime();
        double markovModelTime = (end-begin);
        
	    EfficientMarkovModel mTwoEff = new EfficientMarkovModel(2);
	    begin = System.nanoTime();
	    runModel(mTwoEff, st, size, seed);
	    end = System.nanoTime();
	    double markovEfficientTime = (end-begin);
	    System.out.println("Time for MarkovModel: " + markovModelTime + "\n"
	    + "Time for EfficientMarkovModel: " + markovEfficientTime);
	}
	
}
