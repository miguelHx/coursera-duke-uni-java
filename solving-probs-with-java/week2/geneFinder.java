/**
 * Finds a protein within a strand of DNA represented as a string of c,g,t,a letters.
 * A protein is a part of the DNA strand marked by start and stop codons (DNA triples)
 * that is a multiple of 3 letters long.
 *
 * @author Miguel Hernandez
 * @version nill
 */
import edu.duke.*;
import java.io.*;

public class TagFinder {
    public String findProtein(String dna) {
        dna = dna.toLowerCase();
        int start = dna.indexOf("atg");
        if (start == -1) {
            return "";
        }
        int stop1 = dna.indexOf("tag", start+3);
        int stop2 = dna.indexOf("tga", start+3);
        int stop3 = dna.indexOf("taa", start+3);
        if ((stop1 - start) % 3 == 0) {
            return dna.substring(start, stop1+3);
        }
        else if ((stop2-start) % 3 == 0) {
            return dna.substring(start, stop2+3);
          }
          
        else if ((stop3-start) % 3 == 0) {
            return dna.substring(start, stop3+3);
          }
        
        else {
            return "";
        }
    }
    
    public void testing() {
        //String a = "AATGCTAGTTTAAATCTGA";
        //String ap = "ATGCTAGTTTAAATCTGA";
        //String a = "ataaactatgttttaaatgt";
        //String ap = "atgttttaa";
        String a = "acatgataacctaag";
        String ap = "";
        String result = findProtein(a);
        if (ap.toLowerCase().equals(result)) {
            System.out.println("success for " + ap + " length " + ap.length());
        }
        else {
            System.out.println("mistake for input: " + a);
            System.out.println("got: " + result);
            System.out.println("not: " + ap);
        }
    }

    public void realTesting() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            String s = fr.asString();
            System.out.println("read " + s.length() + " characters");
            String result = findProtein(s);
            System.out.println("found " + result);
        }
    }
}
