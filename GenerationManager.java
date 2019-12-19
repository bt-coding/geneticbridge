import java.util.*;
public class GenerationManager implements Runnable{
    ArrayList<Bridge> bestBridges;
    int numGens;
    int numBridgesPerGen;
    double mutationRate;
    double[] bridgeDimensions;
    ArrayList<Node> lockedNodes;
    ArrayList<Force> bridgeForces;
    int genSize;
    int maxNodes;
    int minNumNodes;
    boolean running;
    public GenerationManager(int ng, int nbpg,double mr,double[] bd,ArrayList<Node> ln,ArrayList<Force> bf, int mn, int mnn){
        bestBridges = new ArrayList<Bridge>();
        numGens = ng;
        numBridgesPerGen = nbpg;
        mutationRate = mr;
        bridgeDimensions = bd;
        lockedNodes = ln;
        bridgeForces = bf;
        maxNodes = mn;
        minNumNodes = mnn; 
        running = false;
    }
    public void run(){
        running = true;
        for(int nn = minNumNodes; nn < maxNodes; nn++){
            Generation generation = new Generation(numBridgesPerGen,nn,bridgeDimensions,bridgeForces,lockedNodes,mutationRate);
            if (generation.gen == null) {
                System.out.println("ANOTHER NULL");
            }
            for(int i = 0; i < numGens; i++){
                generation.testGen();
                generation.createNewGen();
            }
            bestBridges.add(generation.best);
        }
        running = false;
    }
    public  Bridge getCurrentBridge(){
        return bestBridges.get(bestBridges.size()-1);
    }
}