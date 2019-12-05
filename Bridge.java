import java.util.*;

public class Bridge {
    ArrayList<Node> nodelist;
    ArrayList<ArrayList<Node>> members;
    public Bridge() {
        nodelist = new ArrayList<Node>();
        members = new ArrayList<ArrayList<Node>>();
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
    public ArrayList<Node> getNodes() {
        return nodelist;
    }
    public ArrayList<ArrayList<Node>> getMembers() {
        return members;
    }
}