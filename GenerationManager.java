import java.util.*;
public class GenerationManager{
    ArrayList<Bridge> bestBridges;
    int numGens;
    int numBridgesPerGen;
    double mutationRate;
    double[] bridgeDimentions;
    ArrayList<Node> lockedNodes;
    ArrayList<Force> bridgeForces;
    int genSize;
    int maxNodes;
    public GenerationManager(int gs,int ng, int nbpg,double mr,double[] bd,ArrayList<Node> ln,ArrayList<Force> bf, int mn){
        bestBridges = new ArrayList<Bridge>();
        numGens= ng;
        numBridgesPerGen = nbpg;
        mutationRate = mr;
        bridgeDimentions = bd;
        lockedNodes = ln;
        bridgeForces = bf;
        maxNodes = mn;
    }
    public void runGens(){
        for(int nn = 0; nn < maxNodes; nn++){
            Generation gen = new Generation(genSize,nn,bridgeDimentions,bridgeForces,lockedNodes,mutationRate);
            for(int i = 0; i < numGens; i++){
                gen.testGen();
                gen.createNewGen();
            }
            bestBridges.add();
        }
    }
}