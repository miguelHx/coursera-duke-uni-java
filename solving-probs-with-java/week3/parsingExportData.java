/**
 * Final quiz week 3
 * 
 * @author Miguel Hernandez
 */
import edu.duke.*;
import org.apache.commons.csv.*;

public class FirstCSVExample {
    public void readFood() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser(); //CSV Parser is a class
        for(CSVRecord record : parser) { //CSVRecord is also a class
            System.out.print(record.get("Name") + " ");
            System.out.print(record.get("Favorite Color") + " ");
            System.out.println(record.get("Favorite Food"));
        }
    }
    
    
    public void tester() {
        
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        System.out.println("Testing countryInfo method");
        System.out.println();
        countryInfo(parser, "Nauru"); //testing #2
        System.out.println("Done...");
        /*
        parser = fr.getCSVParser(); //resetting parser
        System.out.println("Testing listExportersTwoProducts method");
        System.out.println();
        listExportersTwoProducts(parser, "cotton", "flowers");//testing #3
        System.out.println("Done...");
        System.out.println();
        */
        parser = fr.getCSVParser(); //parser reset
        System.out.print("Testing numberOfExporters method"); //testing #4
        System.out.println();
        System.out.println("Number of countries that have cocoa: " + numberOfExporters(parser, "cocoa"));
        System.out.println();
        System.out.println("Done...");
        System.out.println();
        /*
        parser = fr.getCSVParser(); //parser reset
        System.out.println("Final test:  bigExporters method"); //testing #5
        System.out.println("Countries with a value higher than $999,999,999,999:");
        System.out.println();
        bigExporters(parser, "$999,999,999,999");
        System.out.println("Done...");
        System.out.println("Testing complete");
        */
        
       }
       
    public void countryInfo(CSVParser parser, String country) { // #2
        
        for (CSVRecord record : parser) {
            //Look at the countries column
            String countries = record.get("Country");
            //Check if it contains counry of interest
            if (countries.contains(country)) {
                //If so, write down "Country" from that row, along with it's exports and value
                String Country = record.get("Country");
                String exports = record.get("Exports");
                String value = record.get("Value (dollars)");
                System.out.print(Country + ": " + exports + ": " + value);
                System.out.println();
            }
        }
        System.out.println();
    }
    // #3
    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2) {
        //returns countries that have both exportItem1 and exportItem2
        for (CSVRecord record : parser) {
            //Look at the exports column
            String export = record.get("Exports");
            if ((export.contains(exportItem1) && export.contains(exportItem2))) {
                String Country = record.get("Country");
                System.out.println(Country);
                
            }
        }
        System.out.println();
    }
    // #4
    public int numberOfExporters(CSVParser parser, String exportItem) {
        //returns number of countries that export exportItem
        int count = 0;
        for (CSVRecord record : parser) {
            //Look at the exports column
            String export = record.get("Exports");
            //Check if it contains the export item to add to count of countries
            if (export.contains(exportItem)) {
                //If so, add count by 1
                count++;
            }
            
        }
        return count;
    }
    // #5
    public void bigExporters(CSVParser parser, String amount) {
        //amount format example:  "$400,000,000"
        //This method prints the names of countries and their value for all countries whose Value (dollars)
        //string is larger than the amount string.
        for (CSVRecord record : parser) {
            //Look at values column
            String value = record.get("Value (dollars)");
            //Check if the string length from the column is longer than the one from the parameter
            if (value.length() > amount.length()) {
                //If so, print both the country and the value
                String Country = record.get("Country");
                System.out.println(Country + " " + value);
            }
            
        }
    }
}
