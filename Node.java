public class Node {
    double x;
    double y;
    public Node(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public void setX(double x) {
        this.x = x;
    }
    public void setY(double y) {
        this.y = y;
    }
    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public double[] getPoint() {
        return new double[]{x,y};
    }
    public void setPoint(double[] d) {
        if (d.length != 2) {
            System.out.println("Input to setPoint method was an array with length != 2");
            return;
        }
        this.x = d[0];
        this.y = d[1];
    }
}