
/**
 * 
 * @author Miguel Hernandez
 * @version Nil
 */
import java.util.*;

public class TitleAndDepthComparator implements Comparator<QuakeEntry> {
    public int compare(QuakeEntry q1, QuakeEntry q2) {
        int compareResult = q1.getInfo().compareTo(q2.getInfo());
        if (compareResult == 0) {
            double depth1 = q1.getDepth();
            double depth2 = q2.getDepth();
            return Double.compare(depth1, depth2);
        }
        return compareResult;
    }

}
