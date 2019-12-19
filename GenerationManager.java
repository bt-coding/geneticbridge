import java.util.*;
public class GenerationManager{
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
    }
    public void runGens(){
        for(int nn = minNumNodes; nn < maxNodes; nn++){
            Generation gen = new Generation(numBridgesPerGen,nn,bridgeDimensions,bridgeForces,lockedNodes,mutationRate);
            for(int i = 0; i < numGens; i++){
                gen.testGen();
                gen.createNewGen();
            }
            bestBridges.add(gen.best);
        }
    }
    public  Bridge getCurrentBridge(){
        return bestBridges.get(bestBridges.size()-1);
    }
}