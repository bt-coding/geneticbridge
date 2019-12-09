public class Force {
    double[] location; //x,y
    double value;
    public Force(double x, double y, double val) {
        location = new double[]{x,y};
        value = val;
    }
    public double getX() {
        return location[0];
    }
    public double getY() {
        return location[1];
    }
    public double[] getLocation() {
        return location;
    }
    public double getValue() {
        return value;
    }
    public int getDirection() {
        if (value>0) {
            return 1;
        } else if (value==0) {
            return 0;
        } else {
            return -1;
        }
    }
}