import java.util.*;
public class GeneticAlgorithm{
    ArrayList<Bridge> gen;
    double genSize;
    double numGens;
    int numBridgeNodes;
    double[] bridgeDimentions;
    ArrayList<Force> bridgeForces;
    Bridge best;
    public GeneticAlgorithm(double gs,double ng,int nbn,double[] bd,ArrayList<Force> bf){
        genSize = gs;
        numGens = ng;
        numBridgeNodes = nbn;
        bridgeDimentions = bd;
        bridgeForces = bf;
        for(int i = 0; i < genSize;i++){
            gen.add(new Bridge(numBridgeNodes,bridgeDimentions,bridgeForces));
        }
        best = gen.get(0);
    }
    //test the bridge and sorts it based on score
    public void testGen(){
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
            newGen.add(new Bridge(numBridgeNodes,bridgeDimentions,bridgeForces));
        }
        for(int i  = 0; i < (int)((gen.size()-1)*percentCarryOn);i++){
            newGen.add(gen.get(i));
        }
        for(int i = 0; i < (int)(gen.size()-1)-(int)((gen.size()-1)*percentCarryOn)-(int)((gen.size()-1)*percentNew);i++){
            newGen.add(crossBridges(gen.get((int)(gen.size()*(Math.random()/(1.0/percentCrossRange)))),gen.get((int)(gen.size()*(Math.random()/(1.0/percentCrossRange))))));
        }
    }
    public Bridge crossBridges(Bridge a, Bridge b){
        Bridge newBridge = new Bridge(numBridgeNodes,bridgeDimentions,bridgeForces);
        
        return newBridge;
    }
}