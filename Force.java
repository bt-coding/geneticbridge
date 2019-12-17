public class Force {
    Node node;
    double value;
    int direction;
    public Force(Node n, double val, int dir) {
        this.node = n;
        this.value = val;
        this.direction = dir;
    }
    public double getX() {
        return node.getX();
    }
    public double getY() {
        return node.getY();
    }
    public double[] getLocation() {
        return node.getPoint();
    }
    public double getValue() {
        return value;
    }
    public int getDirection() {
        return direction;
    }
    public Node getNode() {
        return node;
    }
    public void setNode(Node n) {
        this.node = n;
    }
    public void setValue(double d) {
        this.value = d;
    }
    public void setDirection(int dir) {
        this.direction=dir;
    }
}