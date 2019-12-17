public class Member {
    Node n1;
    Node n2;
    public Member(Node n1, Node n2) {
        this.n1 = n1;
        this.n2 = n2;
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