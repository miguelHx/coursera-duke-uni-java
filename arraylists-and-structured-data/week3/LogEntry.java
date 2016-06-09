
/**
 * Plain old java object storing information.
 * Stores information of a server log.  Will help us in finding
 * things such as unique ip visits, number of ip visits, popular visit times,
 * the ip address of visitors, etc.
 * 
 * @author Miguel Hernandez
 * @version Nil
 */

import  java.util.*;
public class LogEntry
{
     private String ipAddress;
     private Date accessTime;
     private String request;
     private int statusCode;
     private int bytesReturned;
     
   public LogEntry(String ip, Date time, String req, int status, int bytes) {
       ipAddress = ip;
       accessTime = time;
       request = req;
       statusCode = status;
       bytesReturned = bytes;
       
   }
   public String getIpAddress() {
         return ipAddress;
    }
    public Date getAccessTime() {
         return accessTime;
   }   
   public String getRequest() {
         return request;
   }
   public int getStatusCode() {
         return statusCode;
   }
   public int getBytesReturned() {
         return bytesReturned;
   }
   
   public String toString() { // without this method, printing out the object will give us the 
       //memory address location in hexadecimal instead of specifying how to print it out.
       return ipAddress + " " + accessTime + " " + request 
           + " " + statusCode + " " + bytesReturned;
    }
}
