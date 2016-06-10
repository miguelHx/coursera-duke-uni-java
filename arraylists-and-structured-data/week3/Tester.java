
/**
 * Tester class for log methods
 * 
 * @author Miguel Hernandez
 * @version nil
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        // complete method. Done
        String filename = "short-test_log";
        LogAnalyzer la = new LogAnalyzer();
        la.readFile(filename);
        la.printAll();
    }
    
    public void testUniqIP() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        int uniqueIPs = la.countUniqueIPs();
        System.out.println("There are " + uniqueIPs + " IPs");
        
    }
    
    public void testUniqueIPVisitsOnDay() {
        LogAnalyzer la = new LogAnalyzer();
        //String filename = "weblog-short_log";
        String filename = "weblog2_log";
        la.readFile(filename);
        String date1 = "Sep 14";
        //String date2 = "Sep 30";
        String date2 = "Sep 24";
        ArrayList<String> uniqIPDate1 = la.uniqueIPVisitsOnDay(date1);
        ArrayList<String> uniqIPDate2 = la.uniqueIPVisitsOnDay(date2);
        System.out.println("Unique ip visits on " + date1 + " is " + uniqIPDate1.size());
        System.out.println("Unique ip visits on " + date2 + " is " + uniqIPDate2.size());
    }
    
    public void testCountUniqueIPsInRange() {
        LogAnalyzer la = new LogAnalyzer();
        String filename = "short-test_log";
        filename = "weblog2_log";
        la.readFile(filename);
        //System.out.println("number of unique ips in range (400, 499) is " + la.countUniqueIPsInRange(200,299));
        System.out.println("number of unique ips in range (400,499) is " + la.countUniqueIPsInRange(400,499));
    }
    
    public void testPrintAllHigherThanNum() {
        LogAnalyzer la = new LogAnalyzer();
        String filename = "weblog1_log";
        la.readFile(filename);
        la.printAllHigherThanNum(400);
    }
}
