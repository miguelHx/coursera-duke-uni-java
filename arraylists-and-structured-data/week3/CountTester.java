
/**
 * Class to test methods from assignment
 * 
 * @author Miguel Hernandez
 * @version nil
 */

import java.util.*;

public class CountTester {
    public void testCounts() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        HashMap<String, Integer> counts = la.countVisitsPerIP();
        System.out.println(counts);
        
    }
    
    public void testMostNumberVisitsByIP() {
        LogAnalyzer la = new LogAnalyzer();
        String file = "weblog2_log";
        la.readFile(file);
        HashMap<String, Integer> counts = la.countVisitsPerIP();
        int maxVisits = la.mostNumberVisitsByIP(counts);
        System.out.println("Max visits by an IP address in file " + file + " is " + maxVisits);
    }
    
    public void testiPsMostVisits() {
        LogAnalyzer la = new LogAnalyzer();
        String file = "weblog2_log";
        la.readFile(file);
        HashMap<String, Integer> counts = la.countVisitsPerIP();
        ArrayList<String> iPsMostVisits = la.iPsMostVisits(counts);
        System.out.println("IP addresses with most visits in file " + file + " are " + iPsMostVisits);
        
    }
    
    public void testiPsForDays() {
        LogAnalyzer la = new LogAnalyzer();
        String file = "weblog3-short_log";
        la.readFile(file);
        HashMap<String, ArrayList<String>> iPsForDays = la.iPsForDays();
        System.out.println("Hashmap of dates to ip's from file " + file + " contains \n" + iPsForDays);
        
    }
    
    public void testdayWithMostIPVisits() {
        LogAnalyzer la = new LogAnalyzer();
        String file = "weblog2_log";
        la.readFile(file);
        String dayWithMostVisits = la.dayWithMostIPVisits(new HashMap<String, ArrayList<String>>());
        System.out.println("Day with most IP visits in file " + file + " is " + dayWithMostVisits);
        
    }
    
    public void testiPsWithMostVisitsOnDay() {
        LogAnalyzer la = new LogAnalyzer();
        String file = "weblog2_log";
        la.readFile(file);
        String testDate = "Sep 30";
        ArrayList<String> ipsWithMostVisitsOnDay = la.iPsWithMostVisitsOnDay(new HashMap<String, ArrayList<String>>(), testDate);
        System.out.println("Ips with most visits from file " + file + " on " + testDate + " are \n" + ipsWithMostVisitsOnDay);
    }
    
    public void testCountUniqueIPs() {
        LogAnalyzer la = new LogAnalyzer();
        String file = "weblog2_log";
        la.readFile(file);
        int numUniqueIps = la.countUniqueIPs();
        System.out.println("Number of unique IPs in " + file + " is " + numUniqueIps);
        
    }
}
