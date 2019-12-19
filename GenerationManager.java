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
    Bridge best;
    Display display;
    public GenerationManager(int ng, int nbpg,double mr,double[] bd,ArrayList<Node> ln,ArrayList<Force> bf, int mn, int mnn, Display d){
        bestBridges = new ArrayList<Bridge>();
        numGens = ng;
        numBridgesPerGen = nbpg;
        mutationRate = mr;
        bridgeDimensions = bd;
        lockedNodes = ln;
        bridgeForces = bf;
        maxNodes = mn;
        minNumNodes = mnn; 
        running = true;
        this.display = d;
    }
    public boolean isRunning() {
        return running;
    }
    public void run(){
        running = true;
        for(int nn = minNumNodes; nn < maxNodes; nn++){
            System.out.println(nn);
            Generation generation = new Generation(numBridgesPerGen,nn,bridgeDimensions,bridgeForces,lockedNodes,mutationRate);
            if (generation.gen == null) {
                System.out.println("ANOTHER NULL");
            }
            for(int i = 0; i < numGens; i++){
                generation.testGen();
                generation.createNewGen();
                //System.out.println(i);
            }
            best = generation.getBest();
            display.drawBridge(best);
            if (best == null) {
                System.out.println("NULL");
            }
            bestBridges.add(best);
        }
        running = false;
    }
    public Bridge getCurrentBridge(){
        if (bestBridges.size()>0) {
            return bestBridges.get(bestBridges.size()-1);
        } else {
            return null;
        }
    }
    public Bridge getBest() {
        return best;
    }
}