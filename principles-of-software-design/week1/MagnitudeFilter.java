
public class MagnitudeFilter implements Filter {
    private double minimum;
    private double maximum;
    public MagnitudeFilter(double min, double max) {
        minimum = min;
        maximum = max;
    }
    public boolean satisfies(QuakeEntry qe) {
        return (qe.getMagnitude() >= minimum && qe.getMagnitude() <= maximum);
    }
    public String getName() {
        return "Magnitude";
    }
}