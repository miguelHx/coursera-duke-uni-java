
/**
 * Analyzes log file
 * 
 * @author Miguel Hernandez
 * @version nil
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
    
    public LogAnalyzer() {
         records = new ArrayList<LogEntry>();
         
    }
        
    public void readFile(String filename) {
         FileResource fr = new FileResource(filename);
         for (String line : fr.lines()) {
             records.add(WebLogParser.parseEntry(line)); //adds log entry object into array list
            }
    }
     
    public int countUniqueIPs() {
        /*
         //unique IPs starts as an empty list
         ArrayList<String> uniqueIPs = new ArrayList<String>();
         //  for each element le in records {
         for (LogEntry le : records) {
         // ipAddr is le's ipAddress
             String ipAddr = le.getIpAddress();
             //if ipAddr is not in uniqueIPs {
             if (!uniqueIPs.contains(ipAddr)) {
             //add ipAddr to unique Ips
                uniqueIPs.add(ipAddr);
            }
        }
        // return the size of uniqueIPs
        return uniqueIPs.size();
        */
       //****Alternate Solution****
       
       HashMap<String, Integer> counts = countVisitsPerIP();
       return counts.size();
    }
    
    public void printAllHigherThanNum(int num) {
        System.out.println("unique log entries with status code greater than " + num);
        ArrayList<Integer> uniqueCodes = new ArrayList<Integer>();
        for (LogEntry le : records) {
            int statusCode = le.getStatusCode();
            if (statusCode > num) {
                if (!uniqueCodes.contains(statusCode)) {
                    uniqueCodes.add(statusCode);
                    System.out.println(statusCode);
                }
            }
            
        }
    }
    
    public ArrayList<String> uniqueIPVisitsOnDay(String someday) {
        System.out.println("Log entries with unique ip visits on " + someday);
        ArrayList<String> uniqueIPs = new ArrayList<String>();
        for (LogEntry le : records) {
            String ipAddr = le.getIpAddress();
            System.out.println(le.getAccessTime());
            String currAccessDate = le.getAccessTime().toString();
            int indexDate = currAccessDate.indexOf(someday);
            if (!uniqueIPs.contains(ipAddr) && indexDate != -1) {
                uniqueIPs.add(ipAddr);
            }
            
        }
        
        return uniqueIPs;
    }
    
    public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le.getAccessTime());
            
         }
         
    }
     
    public int countUniqueIPsInRange(int low, int high) {
         ArrayList<String> uniqueIPs = new ArrayList<String>();
         for (LogEntry le : records) {
             String ipAddr = le.getIpAddress();
             int statusCode = le.getStatusCode();
             System.out.println(statusCode);
             if (!uniqueIPs.contains(ipAddr) && (statusCode >= low && statusCode <= high)) {
                 uniqueIPs.add(ipAddr);
             }
             
         }
         return uniqueIPs.size();
    }
     
    public HashMap<String, Integer> countVisitsPerIP() {
         // Make an empty Hashmap<String, Integer> counts
         HashMap<String, Integer> counts = new HashMap<String, Integer>();
         //For each le in records
         for (LogEntry le : records) {
             //ip is le's ipAddress
             String ip = le.getIpAddress();
             //check if ip is in counts
             if (!counts.containsKey(ip)) {
                //If not: put ip in with a value of 1
                counts.put(ip, 1);
            }
                //If so: update the value to be 1 more
            else {
                counts.put(ip, counts.get(ip) + 1);
            }
         }
         //counts is the answer
         return counts;
    }
     
    public int mostNumberVisitsByIP(HashMap<String, Integer> hashmap) {
        int maxVisits = 0;
        for (Integer visits : hashmap.values()) {
            if (visits > maxVisits) {
                maxVisits = visits;
            }
        }
        
        return maxVisits;
    }
     
    public ArrayList<String> iPsMostVisits(HashMap<String, Integer> hashmap) {
        int max = mostNumberVisitsByIP(hashmap);
        ArrayList<String> output = new ArrayList<String>();
        for (String s : hashmap.keySet()) {
            int value = hashmap.get(s);
            if (value == max) {
                output.add(s);
            }
        }
      
        return output;
    }
     
    public HashMap<String, ArrayList<String>> iPsForDays() {
        HashMap<String, ArrayList<String>> output = new HashMap<String, ArrayList<String>>();
        for (LogEntry le : records) {
            String date = le.getAccessTime().toString();
            date = date.substring(4,10);
            //String ipAddr = le.getIpAddress();
            if (!output.containsKey(date)) {
                output.put(date, new ArrayList<String>());
            }
        }
        
        for (String s : output.keySet()) {
            for (LogEntry le : records) {
                String currAccessDate = le.getAccessTime().toString();
                String ipAddr = le.getIpAddress();
                if (currAccessDate.contains(s)) {
                    output.get(s).add(ipAddr);
                }
            }
        }
        return output;
    }
    
    public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> hashmap) {
        hashmap = iPsForDays();
        int mostVisits = 0;
        String dateMostVisits = "";
        for (String s : hashmap.keySet()) {
            int ipVisits = hashmap.get(s).size();
            if (ipVisits > mostVisits) {
                mostVisits = ipVisits;
                dateMostVisits = s;
            }
        }
        return dateMostVisits;
    }
    
    /*public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> hashmap, String day) {
        hashmap = iPsForDays();
        HashMap<String, Integer> ipCounts = new HashMap<String, Integer>();
        ArrayList<String> output = new ArrayList<String>();
        //get an iterator to help with removing objects from an arraylist
        //Iterator<LogEntry> ite = records.iterator();
        for (int i = 0; i < records.size(); i++) {
            String accessDate = records.get(i).getAccessTime().toString();
            int currLogIndexOfDate = accessDate.indexOf(day);
            //LogEntry value = ite.next();
            if (currLogIndexOfDate == -1) {
                records.remove(records.get(i));
            }
            else {
                System.out.println(records.get(i));
            }
        }
        
        ipCounts = countVisitsPerIP();
        output = iPsMostVisits(ipCounts);
        return output;
    }
    */
   
    public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> hashmap, String day) {
        hashmap = iPsForDays();
        HashMap<String, Integer> ipCounts = new HashMap<String, Integer>();
        ArrayList<String> output = new ArrayList<String>();
        //get an iterator to help with removing objects from an arraylist
        Iterator<LogEntry> ite = records.iterator();
        while(ite.hasNext()) {
            LogEntry le = ite.next();
            String logEntry = le.toString();
            int currLogIndexOfDate = logEntry.indexOf(day);
            
            if (currLogIndexOfDate == -1) {
                ite.remove();
            }
            else {
                System.out.println(le);
            }
        }
        
        ipCounts = countVisitsPerIP();
        output = iPsMostVisits(ipCounts);
        return output;
    }
}
