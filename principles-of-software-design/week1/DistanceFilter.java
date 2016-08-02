
public class DistanceFilter implements Filter {
    private double distMax;
    private Location from;
    
    public DistanceFilter(double distMax, Location from) {
        this.distMax = distMax;
        this.from = from;
    }
    
    public boolean satisfies(QuakeEntry qe) {
        return (qe.getLocation().distanceTo(from) < distMax);
    }
    
    public String getName() {
        return "Distance";
    }
}
