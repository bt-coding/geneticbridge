import java.util.*;
public class Generation{
    ArrayList<Bridge> gen;
    double genSize;
    int numBridgeNodes;
    double[] bridgeDimentions;
    ArrayList<Force> bridgeForces;
    Bridge best;
    ArrayList<Node> lockednodes;
    double mutationRate;
    public Generation(double gs,int nbn,double[] bd,ArrayList<Force> bf,ArrayList<Node> ln,double mr){
        ArrayList<Bridge> gen = new ArrayList<Bridge>();
        genSize = gs;
        numBridgeNodes = nbn;
        bridgeDimentions = bd;
        bridgeForces = bf;
        lockednodes = ln;
        for(int i = 0; i < genSize;i++){
            gen.add(new Bridge(numBridgeNodes,bridgeDimentions,bridgeForces,lockednodes));
        }
        System.out.println(gen.size());
        best = gen.get(0);
        mutationRate = mr;
        if (gen == null) {
            System.out.println("THIRD NULL VALUE");
        }
    }
    //test the bridge and sorts it based on score
    public void testGen(){
        if (gen == null) { 
            System.out.println("NULL VALUE");
        } else {
            System.out.println("VALUE NOT NULL");
        }
        System.out.println(gen.size());
        Collections.sort(gen);
    }
    public void createNewGen(){
        //The percent of new bridges in each generation
        double percentNew = 0.40;
        //The percent of bridges that carry on from one generation to another
        double percentCarryOn = 0.05;
        //The range from 0-x% of the gen that gets crossed
        double percentCrossRange = 0.2;
        if(gen.get(0).getScore() > best.getScore())
            best = gen.get(0);
        ArrayList<Bridge> newGen = new ArrayList<Bridge>();
        newGen.add(best);
        for(int i = 0; i < (int)((gen.size()-1)*percentNew); i++){
            newGen.add(new Bridge(numBridgeNodes,bridgeDimentions,bridgeForces,lockednodes));
        }
        for(int i  = 0; i < (int)((gen.size()-1)*percentCarryOn);i++){
            newGen.add(gen.get(i));
        }
        while(newGen.size() < gen.size()){
            newGen.add(crossSameNodeBridges(gen.get((int)(gen.size()*(Math.random()/(1.0/percentCrossRange)))),gen.get((int)(gen.size()*(Math.random()/(1.0/percentCrossRange))))));
        }
        System.out.println("before: " + gen.size());
        gen = newGen;
        System.out.println("after: " + gen.size());
    }
    public int[][] convertMemberArrayToIntArray(Bridge b){
        int[][] memberList = new int[b.members.size()][2];
        for(int i = 0; i < b.members.size();i++){
            int aNode = 0;
            int bNode = 0;
            for(int x = 0; x < b.nodelist.size(); x++){
                if(b.nodelist.get(x) == b.members.get(i).get(0)){
                    aNode = x;
                }
                if(b.nodelist.get(x) == b.members.get(i).get(1)){
                    bNode = x;
                }
            }
            memberList[i][0] = aNode;
            memberList[i][1] = bNode;
        }
        return memberList;
    }
    public Bridge crossSameNodeBridges(Bridge a, Bridge b){
        double segmentLength = 0.2;
        Bridge newBridge = new Bridge(numBridgeNodes,bridgeDimentions,bridgeForces,lockednodes);
        //int[][] aMemebers = convertMemberArrayToIntArray(a);
        //int[][] bMemebers = convertMemberArrayToIntArray(b);
        ArrayList<Node> newPoints = new ArrayList<Node>();
        ArrayList<ArrayList<Node>> newMembers = new ArrayList<ArrayList<Node>>();
        //clones the array so that there is no issues with object referencing
        for(ArrayList<Node> nodes: a.members){
            ArrayList<Node> temp = new ArrayList<Node>();
            temp.add(nodes.get(0));
            temp.add(nodes.get(1));
            newMembers.add(temp);
        }
        int start = 0;
        boolean aNodes = true;
        //Merges the 2 node point lists
        for(int i = 0; i < a.nodelist.size();i++){
            if(Math.random() < segmentLength || i == a.nodelist.size()-1){
                for(int c = start; c <= i; c++){
                    if(aNodes){
                        newPoints.add(a.nodelist.get(c));
                    }
                    else{
                        newPoints.add(b.nodelist.get(c));
                        for(int z = 0; z < newMembers.size();z++){
                            if(newMembers.get(z).get(0) == a.nodelist.get(c)){
                                newMembers.get(z).set(0,b.nodelist.get(c));
                            }
                            else if(newMembers.get(z).get(1) == a.nodelist.get(c)){
                                newMembers.get(z).set(1,b.nodelist.get(c));
                            }
                        }
                    }
                }
                aNodes = !aNodes;
                start = i+1;
            }
        }
        // mutates the newly formed nodeList by slightly changing the node locations
        for(Node old: newPoints){
            if(Math.random() < mutationRate){
                old.x *= 1+(Math.random()/10)-0.05;
                old.y *= 1+(Math.random()/10)-0.05;
            }
        }
        newBridge.nodelist = newPoints;
        return newBridge;
    }
}