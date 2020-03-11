public class Member {
    private Node n1;
    private Node n2;
    private double leftLoadHPercent;
    private double leftLoadVPercent;
    private double rightLoadHPercent;
    private double rightLoadVPercent;
    //angle to horizon in radians
    double angleOne;
    double angleTwo;
    double forceApplied;
    public Member(Node n1, Node n2) {
        this.n1 = n1;
        this.n2 = n2;
    }
    public double getLeftLoadHPercent(){
        return leftLoadHPercent;
    }
    public void setLeftLoadHPercent(double llhp){
        leftLoadHPercent = llhp;
    }
    public double getLeftLoadVPercent(){
        return leftLoadVPercent;
    }
    public void setLeftLoadVPercent(double llvp){
        leftLoadVPercent = llvp;
    }
    public double getRightLoadHPercent(){
        return rightLoadHPercent;
    }
    public void setRightLoadHPercent(double rlhp){
        rightLoadHPercent = rlhp;
    }
    public double getRightLoadVPercent(){
        return rightLoadVPercent;
    }
    public void setRightLoadVPercent(double rlvp){
        rightLoadVPercent = rlvp;
    }
    public Node getNodeOne() {
        return n1;
    }
    public Node getNodeTwo() {
        return n2;
    }
    public void setNodeOne(Node n1) {
        this.n1 = n1;
    }
    public void setNodeTwo(Node n2) {
        this.n2 = n2;
    }
}