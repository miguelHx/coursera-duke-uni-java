
/**
 * @author Miguel Hernandez 
 * @version nil
 */
import java.util.*;

public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry> {
    public int compare(QuakeEntry q1, QuakeEntry q2) {
        String q1LastWord = q1.getInfo().substring(q1.getInfo().lastIndexOf(" ")+1);
        String q2LastWord = q2.getInfo().substring(q2.getInfo().lastIndexOf(" ")+1);
        int compareResult = q1LastWord.compareTo(q2LastWord);
        if (compareResult == 0) {
            return Double.compare(q1.getMagnitude(), q2.getMagnitude());
        }
        return compareResult;
    }

}
