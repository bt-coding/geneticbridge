public class Node {
    double x;
    double y;
    boolean locked;
    public Node(double x, double y, boolean lock) {
        this.x = x;
        this.y = y;
        locked = lock;
    }
    public void setX(double x) {
        this.x = x;
    }
    public void setY(double y) {
        this.y = y;
    }
    public void lock() {
        locked = true;
    }
    public void unlock() {
        locked = false;
    }
    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public boolean getLocked() {
        return locked;
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
    public String toString() {
        String lockstring = "unlocked";
        if (locked) {
            lockstring = "locked";
        }
        return "(" + x + "," + y + ") " +lockstring;
    }
}