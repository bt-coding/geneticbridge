import java.util.*;
public class TestBridge{
    //bridge nodes are sorted based on largest y cordinate (top-bottom)
    public static ArrayList<Member> testBridge(Bridge b,double maxTen, double maxCom, ArrayList<Node> forces){
        ArrayList<Member> members = new ArrayList<Member>();
        for(ArrayList<Node> mem : b.members){
            members.add(new Member(mem.get(0),mem.get(1)));
        }
        for(Member mem: members){
            mem.angleOne = Math.atan((mem.getNodeTwo().y-mem.getNodeOne().y)/(mem.getNodeTwo().x-mem.getNodeOne().x));
        }
        for(Member mem: members){
            mem.angleTwo = Math.atan((mem.getNodeOne().y-mem.getNodeTwo().y)/(mem.getNodeOne().x-mem.getNodeTwo().x));
        }
        for(Node n: forces){
                calcLoadPercents(n,connectedNodes(n,members));
            }
        double force = 0.5;
        while(!broken(members,maxTen,maxCom)){
            
            force += 0.5;
        }
        return members;
    }
    public static ArrayList<Member> connectedNodes(Node node, ArrayList<Member> mems){
        ArrayList<Member> connected = new ArrayList<Member>();
        for(Member mem: mems){
            if(mem.getNodeOne() == node || mem.getNodeTwo() == node){
                connected.add(mem);
            }
        }
        return connected;
    }
    public static void calcLoadPercents( Node node,ArrayList<Member> connected){
        for(Member mem: connected){
            if(mem.getNodeOne() == node){
                mem.setLeftLoadHPercent(getPercentLoadHorizontally(connected, mem,1));
            }
            else{
                mem.setRightLoadHPercent(getPercentLoadHorizontally(connected,mem,1));
            }
        }
    }   
    public static double getPercentLoadHorizontally(ArrayList<Member> mems,Member m,int angleNum){
        if(angleNum == 1){
            double sum = 0;
            for(Member mem: mems){
                sum += Math.abs(Math.cos(mem.angleOne));
            }
            return Math.abs(Math.cos(m.angleOne))/sum;
        }
        else{
            double sum = 0;
            for(Member mem: mems){
                sum += Math.abs(Math.cos(mem.angleTwo));
            }
            return Math.abs(Math.cos(m.angleTwo))/sum;
        }
    }
    public static double getPercentLoadVertically(ArrayList<Member> mems,Node n,Member m){
        double sum = 0;
        for(Member mem: mems){
            sum += Math.abs(Math.sin(mem.angleOne));
        }
        return Math.abs(Math.sin(m.angleOne))/sum;
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
        return ((Math.abs(x2)-Math.abs(x1))*(Math.abs(y2)-Math.abs(y1)));
        //return b.getNodes().size();
    }   
}