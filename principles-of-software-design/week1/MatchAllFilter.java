import java.util.*;

public class MatchAllFilter implements Filter {
    private ArrayList<Filter> filters;
    
    public MatchAllFilter() {
        filters = new ArrayList<Filter>();
    }
    
    public void addFilter(Filter f) {
        filters.add(f);
    }
    //we'll see .satisfies in a second
    
    public boolean satisfies(QuakeEntry qe) {
        for (Filter f : filters) {
            if (!f.satisfies(qe)) {
                return false;
            }
        }
        return true;
    }
    
    public String getName() {
        String output = "";
        for (int i = 0; i < filters.size(); i++) {
            Filter currFilter = filters.get(i);
            String filterName = currFilter.getName();
            output += filterName + " ";
        }
        return output;
    }
}
