import java.util.*;
import edu.duke.*;

public class EarthQuakeClient {
    public EarthQuakeClient() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData,
    double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        // TODO
        for (QuakeEntry qe : quakeData) {
            if (qe.getMagnitude() > magMin) {
                answer.add(qe);
            }
        }
        return answer;
    }

    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData,
    double distMax,
    Location from) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        // TODO
        for (QuakeEntry qe : quakeData) {
            if (qe.getLocation().distanceTo(from) < distMax) {
                answer.add(qe);
            }
        }
        
        return answer;
    }

    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }

    }

    public void bigQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        
        
        ArrayList<QuakeEntry> listBig = filterByMagnitude(list, 5.0);
        for (QuakeEntry qe : listBig) {
            System.out.println(qe);
        }
        
        
        
    }

    public void closeToMe(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        // This location is Durham, NC
        //Location city = new Location(35.988, -78.907);

        // This location is Bridgeport, CA
        Location city =  new Location(38.17, -118.82);
        ArrayList<QuakeEntry> close = filterByDistanceFrom(list, 1000*1000, city);
        
        for (int i = 0; i < close.size(); i++) {
            QuakeEntry entry = close.get(i);
            double distanceInMeters = city.distanceTo(entry.getLocation());
            System.out.println(distanceInMeters/1000 + " " + entry.getInfo());
            
        }
        System.out.println("Found " + close.size() + " quakes that match that criteria");

        // TODO
    }

    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
    }
    
    /**
     * Method filterByDepth
     *
     * @param quakeData         array list of quake entries to filter from
     * @param minDepth          minimum depth used to filter
     * @param maxDepth          maximum depth used to filter
     * @return                  an array list of type QuakeEntry of all the earthquakes from quakeData
     *                          whose depth is between minDepth and maxDepth.
     */
    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData, double minDepth, double maxDepth) {
        ArrayList<QuakeEntry> output = new ArrayList<QuakeEntry>();
        
        for (QuakeEntry qe : quakeData) {
            double currDepth = qe.getDepth();
            if (currDepth < minDepth && currDepth > maxDepth) {
                output.add(qe);
            }
        }
        return output;
    }
    
    /**
     * Method quakesOfDepth     Prints out filtered quakes by depth
     *
     */
    public void quakesOfDepth() {
       EarthQuakeParser parser = new EarthQuakeParser();
       //String source = "data/nov20quakedatasmall.atom";
       String source = "data/nov20quakedata.atom";
       ArrayList<QuakeEntry> list = parser.read(source);
       dumpCSV(list);
       System.out.println("read data for "+list.size()+" quakes");
        
       double min = -2000.0;
       double max = -4000.0;
       
       System.out.println("Find quakes with depth between " + max + " and " + min);
       ArrayList<QuakeEntry> filterQuakes = filterByDepth(list, min, max);
       
       for (int i = 0; i < filterQuakes.size(); i++) {
           QuakeEntry currEntry = filterQuakes.get(i);
           System.out.println(currEntry);
        }
       
       
       System.out.println("Found " + filterQuakes.size() + " quakes that matach that criteria.");
        
    }
    
    /**
     * Method filterByPhrase
     *
     * @param quakeData             array list of quake entries to filter from
     * @param where                 string that tells us the starting point     
     * @param phrase                phrase that we are trying to find in title of quake entry
     * @return                      an arraylist of filtered quake entries based on phrase
     *                              and specified location
     *                              
     */
    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData, String where, String phrase) {
        ArrayList<QuakeEntry> output = new ArrayList<QuakeEntry>();
        
        for (QuakeEntry qe : quakeData) {
            String info = qe.getInfo();
            //System.out.println(info);
            
            if (where=="start") {
                if (info.startsWith(phrase)) {
                    output.add(qe);
                }
            }
            
            if (where=="end") {
                if (info.endsWith(phrase)) {
                    output.add(qe);
                }
            }
            
            if (where=="any") {
                if (info.indexOf(phrase) != -1) {
                    output.add(qe);
                }
            }
        }
        return output;
    }
    
    public void quakesByPhrase() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedatasmall.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        dumpCSV(list);
        System.out.println("read data for "+list.size()+" quakes\n");
        
        String where = "any";
        String phrase = "Can";
        
        ArrayList<QuakeEntry> filtered = filterByPhrase(list, where, phrase);
        
        for (int i = 0; i < filtered.size(); i++) {
            QuakeEntry currEntry = filtered.get(i);
            System.out.println(currEntry);
        }
        System.out.println("Found " + filtered.size() + " quakes that match " + phrase + " at " + where);
    }
}
