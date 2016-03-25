/**
 * Finds a protein within a strand of DNA represented as a string of c,g,t,a letters.
 * A protein is a part of the DNA strand marked by start and stop codons (DNA triples)
 * that is a multiple of 3 letters long.
 *
 * @author Miguel Hernandez
 */
import edu.duke.*;
import java.io.*;

  public class TagFinder {
    public void printAllStarts(String dna) {
        int start = 0;
        while (true) {
            int loc = dna.indexOf("atg", start);
            if (loc == -1) { //this is when the loop will exit.
                break;
            }
            System.out.println("starts at " + loc);
            start = loc + 3;
        }
        //Now, we need to find the stop codons.
    }
    
    public int findStopIndex(String dna, int index) {
        String dnaLower = dna.toLowerCase();
        int stop1 = dnaLower.indexOf("tga", index);
        if (stop1 == -1 || (stop1-index) % 3 != 0) {
            stop1 = dna.length();
        }
        int stop2 = dnaLower.indexOf("taa", index);
        if (stop2 == -1 || (stop2-index) % 3 != 0) {
            stop2 = dna.length();
        }
        int stop3 = dnaLower.indexOf("tag", index);
        if (stop3 == -1 || (stop3-index) % 3 != 0) {
            stop3 = dna.length();
        }
        return Math.min(stop1, Math.min(stop2,stop3));
    }
    
    public StorageResource storeAll(String dna) {
        StorageResource storeGenes = new StorageResource();
        int start = 0;
        while (true) {
            String dnaLower = dna.toLowerCase();
            int tag = dnaLower.indexOf("atg",start);
            if (tag == -1) {
                break;
            }
            int end = findStopIndex(dna, tag+3);
            
            if (end != dna.length()) {
                //System.out.println("Gene found:");
                //System.out.println(dna.substring(tag, end+3));
                storeGenes.add(dna.substring(tag, end+3));
                start = end + 3;
            }
            else {
                start = start + 3;
            }
        }
        return storeGenes;
    }
    
    public void testStorageFinder() {
        FileResource fr = new FileResource(); //here, we will choose the dna file brca1line to store genes found.
        String dna = fr.asString();
        StorageResource bcraStore = new StorageResource();
        bcraStore = storeAll(dna);
        System.out.println("Number of Genes: " + bcraStore.size());
        
    }
    
    public float cgRatio(String dna) {
        int start = 0;
        int countC = 0;
        int countG = 0;
        String dnaLowercase = dna.toLowerCase();
        while (true) {
            int locC = dnaLowercase.indexOf("c", start);
            if (locC == -1) { //this is when the loop will exit.
                break;
            }
            
            countC += 1;
            
            start = locC + 1;
        }
        start = 0;
        while (true) {
            int locG = dnaLowercase.indexOf("g", start);
            if (locG == -1) {
                break;
            }
            
            countG += 1;
            
            start = locG + 1;
        }
        float cgratio = (float)((countC + countG)) / dna.length();
        return cgratio;
    }
    
    public void printGenes() {
        FileResource fr = new FileResource();
        String dna = fr.asString();
        StorageResource sr = new StorageResource();
        sr = storeAll(dna);
        int countGene = 0;
        int countCG = 0;
        for (String gene : sr.data()) {
            if (gene.length() > 60) {
                System.out.println(gene.length() + "\t" + gene);
                countGene += 1;
            }
        }
        for (String gene : sr.data()) {
            if (cgRatio(gene) > 0.35) {
                System.out.println(cgRatio(gene) + "\t" + gene);
                countCG += 1;
            }
        }
        System.out.println("# of strings that are longer than 60 characters: " + countGene);
        System.out.println("# of strings whose C-G-ratio is higher than 0.35: " + countCG);
    }
    
    public int CTGnum(String dna) {
        
        int start = 0;
        int CTGcount = 0;
        while (true) {
            String dnaLower = dna.toLowerCase();
            int tag = dnaLower.indexOf("ctg",start);
            if (tag == -1) {
                break;
            }
            CTGcount += 1;
            start = tag + 3;
        }
        return CTGcount;
    }
    
    //Assignment 1: processing DNA strings
    public void printCTGandlongGene() {
        FileResource fr = new FileResource();
        String dna = fr.asString();
        StorageResource sr = new StorageResource();
        sr = storeAll(dna);
        int countGene = 0;
        int countCG = 0;
        
        for (String gene : sr.data()) {
            if (gene.length() > 60) {
                System.out.println(gene.length() + "\t" + gene);
                countGene += 1;
            }
        }
        for (String gene : sr.data()) {
            if (cgRatio(gene) > 0.35) {
                
                countCG += 1;
            }
        }
        System.out.println("# of strings that are longer than 60 characters: " + countGene);
        System.out.println("# of strings whose C-G-ratio is higher than 0.35: " + countCG);
        System.out.println("# of times CTG appears in a strand of DNA: " + CTGnum(dna));
    }
}
