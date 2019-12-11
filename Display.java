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
    final String[] toolnames = new String[]{"Node","Member","Force"};
    int zoomscale; //10-20 is pretty normal zoom
    int xoffset;
    int yoffset;
    int[] dirmove; //index 0 for x, index 1 for y
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
        xoffset = 0;
        yoffset = 0;
        dirmove = new int[2];
    }
    public void draw(){
        width = this.getWidth();
        height = this.getHeight();        
        super.repaint();
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        if (b==null) {
            //System.out.println("Value of bridge is null");
            //return;
        }

        g.setColor(new Color(100,100,100));
        for(int r=0;r<((double)height/(double)zoomscale)+1;r++) {
            g.drawLine(0, (r*zoomscale)+(yoffset%zoomscale), width, (r*zoomscale)+(yoffset%zoomscale));
        }
        for(int w=0;w<((double)width/(double)zoomscale)+1;w++) {
            g.drawLine((w*zoomscale)+(xoffset%zoomscale), 0, (w*zoomscale)+(xoffset%zoomscale), height);
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
    public void keypressed(int[] key) {
        //0=left,1=up,2=right,3=down
        if (key[0]==1) {
            xoffset+=10;
        } 
        if (key[1]==1) {
            yoffset+=10;
        }
        if (key[2]==1) {
            xoffset-=10;
        }
        if (key[3]==1) {
            yoffset-=10;
        }
        draw();
    }
    public void keyreleased(int[] key) {
        //0=left,1=up,2=right,3=down
        if (key[0]==1) {
            xoffset-=10;
        } 
        if (key[1]==1) {
            yoffset-=10;
        }
        if (key[2]==1) {
            xoffset+=10;
        }
        if (key[3]==1) {
            yoffset+=10;
        }
        draw();
    }
    public void offset(int x, int y) {
        xoffset+=x;
        yoffset+=y;
        draw();
    }
}