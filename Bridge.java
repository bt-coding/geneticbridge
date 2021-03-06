import java.util.*;

public class Bridge implements Comparable {
    ArrayList<Node> nodelist;
    ArrayList<Node> lockednodes;
    ArrayList<ArrayList<Node>> members;
    ArrayList<ArrayList<Node>> lockedmembers;
    ArrayList<Force> forces;
    double score;
    public Bridge(ArrayList<Force> forces) {
        nodelist = new ArrayList<Node>();
        lockednodes = new ArrayList<Node>();
        members = new ArrayList<ArrayList<Node>>();
        lockedmembers = new ArrayList<ArrayList<Node>>();
        this.forces = forces;
        score = -1;
    }
    public Bridge() {
        nodelist = new ArrayList<Node>();
        lockednodes = new ArrayList<Node>();
        members = new ArrayList<ArrayList<Node>>();
        lockedmembers = new ArrayList<ArrayList<Node>>();
        forces = new ArrayList<Force>();
        score = -1;
    }
    public Bridge(ArrayList<Node> nodelist, ArrayList<Node> lockednodes, ArrayList<ArrayList<Node>> members, ArrayList<ArrayList<Node>> lockedmembers, ArrayList<Force> forces, double score) {
        this.nodelist = nodelist;
        this.lockednodes = lockednodes;
        this.members = members;
        this.lockedmembers = lockedmembers;
        this.forces = forces;
        this.score = score;
    }
    public Bridge(int nodes, double[] cords, ArrayList<Force> forces, ArrayList<Node> lockednodes) {
        //cords should be in the form of minx,miny,maxx,maxy
        nodelist = new ArrayList<Node>();
        
        this.lockednodes = lockednodes;
        members = new ArrayList<ArrayList<Node>>();
        lockedmembers = new ArrayList<ArrayList<Node>>();
        this.forces = forces;
        score = -1;
        for(int i=0;i<nodes;i++) {
            double xcord = (Math.random()*(double)cords[2])-(double)cords[0];
            double ycord = (Math.random()*(double)cords[3])-(double)cords[1];
            Node n = new Node(xcord,ycord,false);
            nodelist.add(n);
        }
        //M = 2J - R for static determinant
        //int membercount = (2*nodes)-forces.size();
        int membercount = 2*nodes;
        for(int i=0;i<membercount;i++) {
            Node n1 = nodelist.get((int)(Math.random()*(double)nodelist.size()));
            Node n2 = nodelist.get((int)(Math.random()*(double)nodelist.size()));
            while(n2 == n1) {
                n2 = nodelist.get((int)(Math.random()*(double)nodelist.size()));
            }
            ArrayList<Node> member = new ArrayList<Node>();
            member.add(n1);
            member.add(n2);
            boolean existing = true;
            while(existing) {
                boolean exist = false;
                for(ArrayList<Node> membertemp : members) {
                    if (membertemp==member) {
                        exist = true;
                    }
                }
                if (exist==false) {
                    existing = false;
                }
            }
            members.add(member);
        }
    }
    public void generateRandom() {
        
    }
    public void addNode(Node n, boolean locked) {
        if (locked) {
            lockednodes.add(n);
        } else {
            nodelist.add(n);
        }
    }
    public void addMember(Node n1, Node n2, boolean externalnodes, boolean locked) {
        //set externalnodes to true if the argument nodes are not direct references to the existing nodelist
        if (externalnodes) {
            if (locked) {
                for(Node node : lockednodes) {
                    double nodex = node.getX();
                    double nodey = node.getY();
                    if (nodex==n1.getX() && nodey==n1.getY()) {
                        n1 = node;
                    }
                    if (nodex==n2.getX() && nodey==n2.getY()) {
                        n2 = node;
                    }
                }
            } else {
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
        }
        ArrayList<Node> temp = new ArrayList<Node>();
        temp.add(n1);
        temp.add(n2);
        System.out.println("Node 1: " + n1.toString());
        System.out.println("Node 2: " + n2.toString());
        if(locked) {
            lockedmembers.add(temp);
        } else {
            members.add(temp);
        }
    }
    public double getScore() {
        if (score != -1) {
            return score;
        }
        score = TestBridge.getScore(this);
        return score;
    }
    public ArrayList<Node> getNodes() {
        return nodelist;
    }
    public ArrayList<Node> getNodesLocked() {
        return lockednodes;
    }
    public ArrayList<ArrayList<Node>> getMembers() {
        return members;
    }
    public ArrayList<ArrayList<Node>> getMembersLocked() {
        return lockedmembers;
    }
    public ArrayList<Force> getForces() {
        return forces;
    }
    public int compareTo(Object other) {
        Bridge b2 = (Bridge)other;
        if (b2.getScore()>this.getScore()) {
            return 1;
        } else if (b2.getScore()<this.getScore()) {
            return -1;
        }
        return 0;
    }
    public void setNodes(ArrayList<Node> nodes){
        nodelist = nodes;
    }
    public void setMembers(ArrayList<ArrayList<Node>> m){
        members = m;
    }
    public void removeNode(Node n, boolean locked) {
        ArrayList<ArrayList<Node>> toremove = new ArrayList<ArrayList<Node>>();
        for(ArrayList<Node> arn : members) {
            if (arn.get(0) == n || arn.get(1) == n) {
                toremove.add(arn);
            }
        }
        for(ArrayList<Node> arn : toremove) {
            members.remove(arn);
        }
        if (locked) {
            lockednodes.remove(n);
        } else {
            nodelist.remove(n);
        }
    }
}