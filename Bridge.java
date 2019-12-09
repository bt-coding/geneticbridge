import java.util.*;

public class Bridge implements Comparable {
    ArrayList<Node> nodelist;
    ArrayList<ArrayList<Node>> members;
    ArrayList<Force> forces;
    double score;
    public Bridge() {
        nodelist = new ArrayList<Node>();
        members = new ArrayList<ArrayList<Node>>();
        score = -1;
    }
    public Bridge(int nodes, double[] cords) {
        //cords should be in the form of minx,miny,maxx,maxy
        nodelist = new ArrayList<Node>();
        members = new ArrayList<ArrayList<Node>>();
        score = -1;
        for(int i=0;i<nodes;i++) {
            double xcord = (Math.random()*(double)cords[2])-(double)cords[0];
            double ycord = (Math.random()*(double)cords[3])-(double)cords[1];
            Node n = new Node(xcord,ycord);
            nodelist.add(n);
        }
        
        
    }
    public void generateRandom() {
        
    }
    public void addNode(Node n) {
        nodelist.add(n);
    }
    public void addMember(Node n1, Node n2, boolean externalnodes) {
        //set externalnodes to true if the argument nodes are not direct references to the existing nodelist
        if (externalnodes) {
            for(Node node : nodelist) {
                double nodex = node.getX();
                double nodey = node.getY();
                if (nodex==n1.getX() && nodey==n1.getY()) {
                    n1 = node;
                }
                if (nodex==n2.getX() && nodey==n2.getY()) {
                    n2 = node;
                }
            }
        }
        ArrayList<Node> temp = new ArrayList<Node>();
        temp.add(n1);
        temp.add(n2);
        members.add(temp);
    }
    public double getScore() {
        if (score != -1) {
            return score;
        }
        return 0;
    }
    public ArrayList<Node> getNodes() {
        return nodelist;
    }
    public ArrayList<ArrayList<Node>> getMembers() {
        return members;
    }
    public int compareTo(Object other) {
        Bridge b2 = (Bridge)other;
        if (b2.getScore()>this.getScore()) {
            return -1;
        } else if (b2.getScore()<this.getScore()) {
            return 1;
        }
        return 0;
    }
}