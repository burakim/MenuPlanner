package Models;

/**
 * Created by BurakMac on 14/06/15.
 */
public class ConstraintModel {
    private double upperLimit;
    private double lowerLimit;
    private double significance;
    public ConstraintModel(double upperLimit, double lowerLimit,double significance)
    {
        this.setUpperLimit(upperLimit);
        this.setLowerLimit(lowerLimit);
        this.setSignificance(significance);

    }

    public double getUpperLimit() {
        return upperLimit;
    }

    public void setUpperLimit(double upperLimit) {
        this.upperLimit = upperLimit;
    }

    public double getLowerLimit() {
        return lowerLimit;
    }

    public void setLowerLimit(double lowerLimit) {
        this.lowerLimit = lowerLimit;
    }

    public double getSignificance() {
        return significance;
    }

    public void setSignificance(double significance) {
        this.significance = significance;
    }
}
