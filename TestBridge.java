import java.util.*;
public class TestBridge{
    //bridge nodes are sorted based on largest y cordinate (top-bottom)
    public static ArrayList<Member> testBridge(Bridge b,double maxTen, double maxCom, ArrayList<Node> forces){
        ArrayList<Member> members = new ArrayList<Member>();
        for(ArrayList<Node> mem : b.members){
            members.add(new Member(mem.get(0),mem.get(1)));
        }
        for(Member mem: members){
            mem.angle = Math.atan((mem.getNodeOne().y-mem.getNodeTwo().y)/(mem.getNodeTwo().x-mem.getNodeOne().x));
        }
        double force = 0.5;
        while(!broken(members,maxTen,maxCom)){
            for(Node n: forces){
                applyForce(force,n);
            }
            force += 0.5;
        }
        return members;
    }
    public static void applyForce(double force, Node node){
        
    }
    public static boolean broken(ArrayList<Member> mems, double maxT, double maxC){
        for(Member mem: mems){
            if(mem.forceApplied > maxC || mem.forceApplied < maxT)
                return true;
        }
        return false;
    }
    public static double getScore(Bridge b){
        double x2 = 0;
        double x1 = 0;
        double y2 = 0;
        double y1 = 0;
        for(Node n: b.nodelist){
            if(n.x > x2){
                x2 = n.x;
            }
            if(n.x< x1){
                x1 = n.x;
            }
            if(n.y > y2){
                y2 = n.y;
            }
            if(n.y < y1){
                y1 = n.y;
            }
        }
        return (x2-x1)*(y2-y1);
        //return b.getNodes().size();
    }   
}