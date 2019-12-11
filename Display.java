import javax.swing.*;
import java.awt.*; 
import java.awt.event.*;
public class Display extends JPanel implements ActionListener {
    JButton nodebutton;
    JButton memberbutton;
    JButton forcebutton;
    int height;
    int width;
    int toolselected; //0=nodebutton, 1=memberbutton, 2=force
    int zoomscale; //10-20 is pretty normal zoom
    Bridge b;
    public Display(JButton nodebutton, JButton memberbutton, JButton forcebutton, int width, int height){
        super();
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
    }
    public void draw(){
        super.repaint();
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        if (b==null) {
            //System.out.println("Value of bridge is null");
            //return;
        }
        g.setColor(new Color(100,100,100));
        for(int r=0;r<height/zoomscale;r++) {
            g.drawLine(0, r*zoomscale, width, r*zoomscale);
        }
        for(int w=0;w<width/15;w++) {
            g.drawLine(w*zoomscale, 0, w*zoomscale, height);
        }
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
    }
    public void mouseClicked(double x, double y) {
     
    }
}