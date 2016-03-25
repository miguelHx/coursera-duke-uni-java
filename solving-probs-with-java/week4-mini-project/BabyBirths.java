/**
 * Mini project: baby names
 * 
 * @author Miguel Hernandez
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import edu.duke.DirectoryResource;
import edu.duke.FileResource;
import java.io.File;

public class BabyBirths {
    public void printNames () {
        FileResource fr = new FileResource();
        for (CSVRecord rec : fr.getCSVParser(false)) { //false because of no header row to refer to
            int numBorn = Integer.parseInt(rec.get(2));
            if (numBorn <= 100) {
                System.out.println("Name " + rec.get(0) + " Gender " + rec.get(1) + " Num born " + rec.get(2));
 
            }
        }
    }
    
    public void totalBirths (FileResource fr) {
        int totalBirths = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        int totalUniqueBoys = 0;
        int totalUniqueGirls = 0;
        int numNames = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            
            int numBorn = Integer.parseInt(rec.get(2));
            
            totalBirths += numBorn;
            numNames++;
            
            if (rec.get(1).equals("M")) {
                totalBoys += numBorn;
            }
            else {
                totalGirls += numBorn;
            }
            if (numBorn < 200 && rec.get(1).equals("M")) {
                totalUniqueBoys++;
            }
            else if (numBorn < 200 && rec.get(1).equals("F")) {
                totalUniqueGirls++;
            }
        
        }
        System.out.println("total births = " + totalBirths);
        System.out.println("total girls = " + totalGirls);
        System.out.println("total boys = " + totalBoys);
        System.out.println("total unique boy names = " + totalUniqueBoys);
        System.out.println("total unique girl names = " + totalUniqueGirls);
        System.out.println("total names = " + numNames);
    }
    
    public void testTotalBirths() {
        FileResource fr = new FileResource();
        totalBirths(fr);
        
        
    }
    // # 2 getRank
    public int getRank(int year, String name, String gender) {
        FileResource fr = new FileResource();
        
        int rank = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            
            if(rec.get(1).equals(gender)) {
                rank++;
                if(rec.get(0).equals(name)) {
                    return rank;
                }
            }
        }
        return -1;
    }
    
    public void testgetRank() {
        int year = 1996;
        String name = "Mimi";
        int rank = getRank(year, name, "F");
        System.out.println("Rank of " + name + " is " + rank + " in " + year);
        
    }
    // # 3 getName
    public String getName(int year, int rank, String gender) {
        FileResource fr = new FileResource();
        int rankCurr= 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (rec.get(1).equals(gender)) {
                rankCurr++;
                if (rankCurr == rank) {
                    return rec.get(0);
                }
            }
        }
        return "NO NAME";
    }
    // # 3 test
    
   public void testgetName() {
       int rank = 450;
       String genderM = "M";
       String genderF = "F";
       System.out.println("Name with rank " + rank + " and gender " + genderM + " is " + getName(1982, rank, genderM));
       
       
    }
    
    // # 4 whatIsNameInYear
    public void whatIsNameInYear(String name, int year, int newYear, String gender) {
        int rank1 = getRank(year, name, gender);
        String nameInNewYear = getName(2014, rank1, gender);
        System.out.println(name + " born in " + year + " would be " + nameInNewYear + " if she/he was born in " + newYear);
        
    }
    public void testwhatIsNameInYear() {
        whatIsNameInYear("Susan", 1974, 2014, "F");
        
        
    }
    
    // # 5 yearOfHighestRank
    public int yearOfHighestRank(String name, String gender) {
        
        
        int rank = 1000000;
        int yearHigh = 0;
        DirectoryResource dr = new DirectoryResource();
        
        
        for (File f : dr.selectedFiles()) {
            String fileName = f.getName();
            
            int year = Integer.parseInt(fileName.replaceAll("[\\D]", ""));
            
            FileResource fr = new FileResource(f);
            int currRank = -1;
            int pos = 0;
            for (CSVRecord rec: fr.getCSVParser(false)) {
                if (rec.get(1).equals(gender)) {
                    pos++;
                    if (rec.get(1).equals(gender)) {
                        currRank = pos;
                        break;
                    }
                }
            }
            
            if (currRank != -1 && currRank < rank) {
                rank = currRank;
                yearHigh = year;
                
            }
        }
        return yearHigh;
    }
    
    // # 5 test yearOfHighestRank
    public void testyearOfHighestRank() {
        String name = "Genevieve";
        String gender = "F";
        System.out.println(name + "'s year of highest rank is " + yearOfHighestRank(name, gender));
        
        
    }
    
    // # 6 getAverageRank
    public double getAverageRank(String name, String gender) {
        DirectoryResource dr = new DirectoryResource();
        int fileNum = 0;
        int totalRank = 0;
        
        for (File f : dr.selectedFiles()) {
            fileNum++;
            
            FileResource fr = new FileResource(f);
            
            int pos = 0;
            int currRank = 0;
            for(CSVRecord rec : fr.getCSVParser(false)) {
                if (rec.get(1).equals(gender)) {
                    pos++;
                    if (rec.get(0).equals(name)) {
                        currRank = pos;
                        break;
                    }
                }
            }
            
            totalRank += currRank;
        }
        if (totalRank == 0) {
            return -1;
        }
        else {
            return (double)(totalRank)/(fileNum);
        }
    }
    
    // test # 6 getAverageRank
    public void testgetAverageRank() {
        String name = "Robert";
        String gender = "M";
        
        System.out.println("Average rank of " + name + " is " + getAverageRank(name, gender));
        
        
    }
    
    // # 7 getTotalBirthsRankedHigher
    public int getTotalBirthsRankedHigher(int year, String name, String gender) {
        FileResource fr = new FileResource();
        
        int sum = 0;
        for(CSVRecord rec : fr.getCSVParser(false)) {
            if(rec.get(1).equals(gender)) {
                if(rec.get(0).equals(name)) {
                    return sum;
                    
                }
                sum += Integer.parseInt(rec.get(2));
            }
        }
        return sum;
    }
    
    // test # 7 getTotalBirthsRankedHigher
    
    public void testgetTotalBirthsRankedHigher() {
        int year = 1990;
        String name = "Drew";
        String gender = "M";
        
        System.out.println("Output is " + getTotalBirthsRankedHigher(year, name, gender));
    }
}
