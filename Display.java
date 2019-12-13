import javax.swing.*;
import java.awt.*; 
import java.awt.event.*;
public class Display extends JPanel implements ActionListener {
    JPanel superpanel;
    JButton nodebutton;
    JButton memberbutton;
    JButton forcebutton;
    int height;
    int width;
    int toolselected; //0=nodebutton, 1=memberbutton, 2=force
    final String[] toolnames = new String[]{"Node","Member","Force"};
    int zoomscale; //10-20 is pretty normal zoom
    int xoffset;
    int yoffset;
    int[] dirmove; //0=left,1=up,2=right,3=down
    Bridge b;
    public Display(JPanel superpannel, JButton nodebutton, JButton memberbutton, JButton forcebutton, int width, int height, Bridge b){
        super();
        this.superpanel = superpannel;
        this.nodebutton = nodebutton;
        this.memberbutton = memberbutton;
        this.forcebutton = forcebutton;
        nodebutton.addActionListener(this);
        memberbutton.addActionListener(this);
        forcebutton.addActionListener(this);
        this.width = width;
        this.height = height;
        toolselected = 0;
        zoomscale = 15;
        xoffset = 0;
        yoffset = 0;
        dirmove = new int[4];
        this.b = b;
    }
    public void draw(){
        width = this.getWidth();
        height = this.getHeight(); 
        this.repaint();
    }
    public void update() {
        if (dirmove[0]==1) {
            xoffset+=10;
        }
        if (dirmove[1]==1) {
            yoffset+=10;
        }
        if (dirmove[2]==1) {
            xoffset-=10;
        }
        if (dirmove[3]==1) {
            yoffset-=10;
        }
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        if (b==null) {
            System.out.println("Value of bridge is null");
            return;
        }

        g.setColor(new Color(100,100,100));
        for(int r=0;r<((double)height/(double)zoomscale)+1;r++) {
            g.drawLine(0, (r*zoomscale)+(yoffset%zoomscale), width, (r*zoomscale)+(yoffset%zoomscale));
        }
        for(int w=0;w<((double)width/(double)zoomscale)+1;w++) {
            g.drawLine((w*zoomscale)+(xoffset%zoomscale), 0, (w*zoomscale)+(xoffset%zoomscale), height);
        }
        
        g.setColor(Color.RED);
        for(Node n : b.getNodes()) {
            //System.out.println(n.getX()+","+n.getY());
            double xcord = zoomscale*(n.getX()+xoffset);
            double ycord = zoomscale*(n.getY()+yoffset);
            g.fillOval((int)xcord, (int)ycord, 2*zoomscale, 2*zoomscale);
        }
        
        g.setColor(Color.GREEN);
        g.fillOval(-2+xoffset, -2+yoffset, 4*zoomscale, 4*zoomscale);
        System.out.println(zoomscale);
        
    }
    public void drawBridge(Bridge b) {
        this.b = b;
        this.repaint();
    }
    public void actionPerformed(ActionEvent evt) {
        Object src = evt.getSource();
        if (src == nodebutton) {
            toolselected = 0;
        } else if (src == memberbutton) {
            toolselected = 1;
        } else if (src == forcebutton) {
            toolselected = 2;
        }
        superpanel.requestFocus();
        draw();
    }
    public void mouseClicked(double x, double y) {
        
        
    }
    public void scroll(int amount) {
        if (!(zoomscale+(amount*(Math.sqrt(zoomscale))) <= 4)) {
            zoomscale+=amount*(Math.sqrt(zoomscale));
        } else {
            zoomscale = 4;
        }
        draw();
    }
    public void keypressed(int key) {
        //0=left,1=up,2=right,3=down
        dirmove[key]=1;
        draw();
    }
    public void keyreleased(int key) {
        //0=left,1=up,2=right,3=down
        dirmove[key]=0;
        draw();
    }
    public void offset(int x, int y) {
        xoffset+=x;
        yoffset+=y;
        draw();
    }
    public void goHome() {
        xoffset = 0;
        yoffset = 0;
    }
}