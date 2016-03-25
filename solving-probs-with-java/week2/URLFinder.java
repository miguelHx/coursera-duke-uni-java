/**
 * Prints out all links within the HTML source of a web page.
 * 
 * @author Miguel Hernandez
 */
import edu.duke.*;

public class URLFinder {
    public StorageResource findURLs(String url) {
        URLResource page = new URLResource(url);
        String source = page.asString();
        StorageResource storeURL = new StorageResource();
        int start = 0;
        int startDot = 0;
        int dotAmount = 0;
        while (true) {
            int index = source.indexOf("href=", start);
            if (index == -1) {
                break;
            }
            int firstQuote = index+6; // after href="
            int endQuote = source.indexOf("\"", firstQuote);
            String sub = source.substring(firstQuote, endQuote);
            if (sub.startsWith("http") && sub.contains(".com")) { //change http to https when needed
                //also change to if sub.endswith(".com") || sub.endswith(".com/")
                storeURL.add(sub);
            }
            start = endQuote + 1;
        }
        return storeURL;
    }
    public int findDOTs(StorageResource sr) {
        
        int start = 0;
        int dotAmount = 0;
        for (String link : sr.data()) {
            while (true) {
                int index = link.indexOf(".", start);
                if (index == -1) {
                    break;
                }
                dotAmount += 1;
                start = index + 1;
            }
        }
        return dotAmount;
            
    }
    

    public void testURLWithStorage() {
        StorageResource s2 = findURLs("http://www.dukelearntoprogram.com/course2/data/newyorktimes.html");
        for (String link : s2.data()) {
            System.out.println(link);
        }
        System.out.println("size = " + s2.size());
        System.out.println("Dots: " + findDOTs(s2));
    }
}
