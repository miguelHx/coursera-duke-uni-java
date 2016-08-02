
/**
 * determine the N biggest earthquakes, those with largest magnitude.
 * 
 * @author Miguel Hernandez 
 * @version 0
 */

import java.util.*;

public class LargestQuakes {
    
    public void findLargestQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedatasmall.atom";
        String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        
        ArrayList<QuakeEntry> list  = parser.read(source);
        /*
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
        */
        System.out.println("read data for "+list.size());
        
        /*int maxSrcIdx = indexOfLargest(list);
        
        System.out.println("Max index from " + source + " is " + maxSrcIdx + 
                "\nIt's magnitude is " + list.get(maxSrcIdx).getMagnitude());
                
        */
       int num = 50;
       ArrayList<QuakeEntry> largest = getLargest(list, num);
       
       System.out.println("printing the " + num + " largest earthquakes from " + source + "...");
       for (QuakeEntry qe : largest) {
           System.out.println(qe);
        }
       
    }
    
    public int indexOfLargest(ArrayList<QuakeEntry> data) {
        int idxMax = 0;
        double max = -999.9;
        for (int i = 0; i < data.size(); i++) {
            QuakeEntry currEntry = data.get(i);
            double currMag = currEntry.getMagnitude();
            if (currMag > max) {
                idxMax = i;
                max = currMag;
            }
            
        }
        return idxMax;
    }
    
    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData, int howMany) {
        ArrayList<QuakeEntry> output = new ArrayList<QuakeEntry>();
        ArrayList<QuakeEntry> copy = quakeData;
        
        for (int i = 0; i < howMany; i++) {
            int currMax = indexOfLargest(copy);
            QuakeEntry currMaxEntry = copy.get(currMax);
            output.add(currMaxEntry);
            
            copy.remove(currMaxEntry);
        }
        return output;
    }
}
