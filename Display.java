import javax.swing.*;
import java.awt.*; 
import java.awt.event.*;
import java.util.*;
import javax.imageio.*;
import java.io.*;
import java.awt.image.*;
import javax.swing.event.*;
public class Display extends JPanel implements ActionListener,ChangeListener {
    JPanel superpanel;
    JButton nodebutton;
    JButton memberbutton;
    JButton forcebutton;
    JButton erasebutton;
    JButton homebutton;
    JButton clearbutton;
    JButton simulatebutton;
    JSlider nodesizeslider;
    JSlider movespeedslider;
    int height;
    int width;
    int toolselected; //0=nodebutton, 1=memberbutton, 2=force
    final String[] toolnames = new String[]{"Node","Member","Force","Erase"};
    int zoomscale; //10-20 is pretty normal zoom
    int xoffset;
    int yoffset;
    int[] dirmove; //0=left,1=up,2=right,3=down
    Bridge b;
    boolean locked;
    boolean snap;
    BufferedImage lockimage;
    double[] zoomcords;
    int nodesize;
    double movespeed;
    boolean simulating;
    boolean memberconnected;
    int snapScale;
    Node firstnode;
    public Display(JPanel superpannel, JButton nodebutton, JButton memberbutton, JButton forcebutton, JButton erasebutton, JButton homebutton, JButton clearbutton, JSlider nodesizeslider, JSlider movespeedslider, JButton simulatebutton, int width, int height, Bridge b){
        super();
        this.superpanel = superpannel;
        this.nodebutton = nodebutton;
        this.memberbutton = memberbutton;
        this.forcebutton = forcebutton;
        this.erasebutton = erasebutton;
        this.homebutton = homebutton;
        this.clearbutton = clearbutton;
        this.nodesizeslider = nodesizeslider;
        this.movespeedslider = movespeedslider;
        this.simulatebutton = simulatebutton;
        nodebutton.addActionListener(this);
        memberbutton.addActionListener(this);
        forcebutton.addActionListener(this);
        erasebutton.addActionListener(this);
        homebutton.addActionListener(this);
        clearbutton.addActionListener(this);
        nodesizeslider.addChangeListener(this);
        movespeedslider.addChangeListener(this);
        simulatebutton.addActionListener(this);
        this.width = width;
        this.height = height;
        toolselected = 0;
        zoomscale = 15;
        xoffset = 0;
        yoffset = 0;
        dirmove = new int[4];
        zoomcords = new double[2];
        nodesize=6;
        movespeed=10;
        this.b = b;
        snap = true;
        simulating = false;
        memberconnected = false;
        snapScale = 20;
        try {
            lockimage = ImageIO.read(new File("lock.png"));
        } catch (Exception e) {
            System.err.println("Failed to load lock image");
            e.printStackTrace();
        }
    }
    public void draw(){
        width = this.getWidth();
        height = this.getHeight(); 
        this.repaint();
    }
    public void update() {
        double movement = movespeed/zoomscale;
        if (movement<1) {
            movement=1;
        }
        if (dirmove[0]==1) {
            xoffset+=movement;
        }
        if (dirmove[1]==1) {
            yoffset+=movement;
        }
        if (dirmove[2]==1) {
            xoffset-=movement;
        }
        if (dirmove[3]==1) {
            yoffset-=movement;
        }
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics original = g.create();
        Graphics2D g2d = (Graphics2D)(g);
        
        g2d.translate((double)(width/2),(double)(height/2));
        g2d.scale(zoomscale,zoomscale);
        g2d.translate(-(double)(width/2),-(double)(height/2));
        
        /*
        g2d.translate((double)zoomcords[0],(double)zoomcords[1]);
        g2d.scale(zoomscale,zoomscale);
        g2d.translate(-(double)zoomcords[0],-(double)zoomcords[1]);
        */
        if (b==null) {
            System.err.println("Value of bridge is null");
            return;
        }

        g2d.setColor(new Color(100,100,100));
        for(int r=0;r<(double)2000;r+=20) {
            g2d.drawLine(0, (r)+(yoffset), width, (r)+(yoffset));
        }
        for(int w=0;w<(double)2000;w+=20) {
            g2d.drawLine((w)+(xoffset), 0, (w)+(xoffset), height);
        }
        
        
        for(Node n : b.getNodes()) {
            g2d.setColor(Color.RED);
            //System.out.println(n.getX()+","+n.getY());
            double xcord = (n.getX()+xoffset);
            double ycord = (n.getY()+yoffset);
            if (xcord<width&&xcord>0&&ycord<height&&ycord>0) {
                g2d.fillOval((int)xcord-(nodesize/2), (int)ycord-(nodesize/2),nodesize,nodesize);
                g2d.setColor(Color.BLACK);
                g2d.drawOval((int)xcord-(nodesize/2), (int)ycord-(nodesize/2),nodesize,nodesize);
            }
        }
        for(Node n : b.getNodesLocked()) {
            g2d.setColor(Color.ORANGE);
            double xcord = (n.getX()+xoffset);
            double ycord = (n.getY()+yoffset);
            if (xcord<width&&xcord>0&&ycord<height&&ycord>0) {
                g2d.fillOval((int)xcord-(nodesize/2), (int)ycord-(nodesize/2),nodesize,nodesize);
                g2d.setColor(Color.BLACK);
                g2d.drawOval((int)xcord-(nodesize/2), (int)ycord-(nodesize/2),nodesize,nodesize);
            }
        }
        for(ArrayList<Node> m : b.getMembers()) {
            g2d.setColor(Color.BLACK);
            Node n1 = m.get(0);
            Node n2 = m.get(1);
            
            double x1 = (n1.getX()+xoffset);
            double y1 = (n1.getY()+yoffset);
            double x2 = (n2.getX()+xoffset);
            double y2 = (n2.getY()+yoffset);
            
            g2d.drawLine((int)x1, (int)y1, (int)x2, (int)y2);
            g2d.fillOval((int)x1-1, (int)y1-1, 2, 2);
            g2d.fillOval((int)x2-1, (int)y2-1, 2, 2);
        }
        for(ArrayList<Node> m : b.getMembersLocked()) {
            g2d.setColor(Color.GREEN);
            Node n1 = m.get(0);
            Node n2 = m.get(1);
            
            double x1 = (n1.getX()+xoffset);
            double y1 = (n1.getY()+yoffset);
            double x2 = (n2.getX()+xoffset);
            double y2 = (n2.getY()+yoffset);
            
            g2d.drawLine((int)x1, (int)y1, (int)x2, (int)y2);
            g2d.fillOval((int)x1-1, (int)y1-1, 2, 2);
            g2d.fillOval((int)x2-1, (int)y2-1, 2, 2);
        }
        
        if (toolselected == 0) {
            //node placement tool
            if(!snap){
                g2d.setColor(new Color(0,155,0));
                Point mpoint = MouseInfo.getPointerInfo().getLocation();
                double mx = mpoint.getX();
                double my = mpoint.getY();
                Point dpoint = this.getLocationOnScreen();
                double fx = dpoint.getX();
                double fy = dpoint.getY();
                double x = ((int)mx-(int)fx);
                double y = ((int)my-(int)fy);
                x -= width/2;
                y -= height/2;
                x/=zoomscale;
                y/=zoomscale;
                x += width/2;
                y += height/2;
                double realx = x-xoffset;
                double realy = y-yoffset;
                g2d.drawOval((int)realx+xoffset-(int)(nodesize/2),(int)realy+yoffset-(int)(nodesize/2),nodesize,nodesize);
            }
            else if(snap){
                g2d.setColor(new Color(0,155,0));
                Point mpoint = MouseInfo.getPointerInfo().getLocation();
                double mx = mpoint.getX();
                double my = mpoint.getY();
                Point dpoint = this.getLocationOnScreen();
                double fx = dpoint.getX();
                double fy = dpoint.getY();
                double x = ((int)mx-(int)fx);
                double y = ((int)my-(int)fy);
                x -= width/2;
                y -= height/2;
                x/=zoomscale;
                y/=zoomscale;
                x += width/2;
                y += height/2;
                if(x%snapScale < snapScale/2){
                    x -= x%snapScale;
                }
                else{
                    x += snapScale-(x%snapScale);
                }
                if(y%snapScale < snapScale/2){
                    y -= y%snapScale;
                }
                else{
                    y+= snapScale-(y%snapScale);
                }
                g2d.drawOval((int)x-(int)(nodesize/2),(int)y-(int)(nodesize/2),nodesize,nodesize);
            }
        } else if (toolselected == 3) {
            //erase tool
            g2d.setColor(new Color(255,0,0,100));
            Point mpoint = MouseInfo.getPointerInfo().getLocation();
            double mx = mpoint.getX();
            double my = mpoint.getY();
            Point dpoint = this.getLocationOnScreen();
            double fx = dpoint.getX();
            double fy = dpoint.getY();
            double x = ((int)mx-(int)fx);
            double y = ((int)my-(int)fy);
            x -= width/2;
            y -= height/2;
            x/=zoomscale;
            y/=zoomscale;
            x += width/2;
            y += height/2;
            double realx = x-xoffset;
            double realy = y-yoffset;
            g2d.fillOval((int)realx+xoffset-3,(int)realy+yoffset-3,6,6);
            g2d.setColor(new Color(0,0,0,200)); 
            g2d.drawOval((int)realx+xoffset-3,(int)realy+yoffset-3,6,6);
        }
        
        if (memberconnected) {
            double xcord = (firstnode.getX()+xoffset);
            double ycord = (firstnode.getY()+yoffset);
            Point mpoint = MouseInfo.getPointerInfo().getLocation();
            double mx = mpoint.getX();
            double my = mpoint.getY();
            Point dpoint = this.getLocationOnScreen();
            double fx = dpoint.getX();
            double fy = dpoint.getY();
            double x = ((int)mx-(int)fx);
            double y = ((int)my-(int)fy);
            x-= width/2;
            y-= height/2;
            x/=zoomscale;
            y/=zoomscale;
            x += width/2;
            y += height/2;
            
            //g.drawLine((int)xcord-(nodesize/2), (int)ycord-(nodesize/2), (int)x, (int)y);
            if (locked) {
                g.setColor(Color.GREEN);
            } else {
                g.setColor(Color.BLACK);
            }
            g.drawLine((int)xcord,(int)ycord,(int)x,(int)y);
        }
        
        
        if (locked && (toolselected==0 || toolselected==1)) {
            //display lock icon when placement locked
            Point mpoint = MouseInfo.getPointerInfo().getLocation();
            double mx = mpoint.getX();
            double my = mpoint.getY();
            Point dpoint = this.getLocationOnScreen();
            double fx = dpoint.getX();
            double fy = dpoint.getY();
            original.drawImage(lockimage,(int)mx-(int)fx,(int)my-(int)fy,this);
        }
        
        
        g2d.setColor(Color.GREEN);
        g2d.fillOval(-8+xoffset, -8+yoffset, 16, 16);
        //System.out.println(zoomscale);
    }
    public void drawBridge(Bridge b) {
        this.b = b;
        this.repaint();
    }
    public void actionPerformed(ActionEvent evt) {
        Object src = evt.getSource();
        if (src != memberbutton) {
            memberconnected = false;
        }
        if (src == nodebutton) {
            toolselected = 0;
        } else if (src == memberbutton) {
            toolselected = 1;
        } else if (src == forcebutton) {
            toolselected = 2;
        } else if (src == erasebutton) {
            toolselected = 3;
        } else if (src == homebutton) {
            this.goHome();
        } else if (src == clearbutton) {
            b = new Bridge();
        } else if (src == simulatebutton) {
            if (!simulating) {
                simulating = true;
                ArrayList<Node> tempLocked = new ArrayList<Node>();
                for(Node n : b.getNodesLocked()) {
                    tempLocked.add(new Node(n.getX()+2000,n.getY()+2000,n.getLocked()));
                }
                GenerationManager gm = new GenerationManager(750,500,.05,new double[]{1000,1000,1100,1100},tempLocked,b.forces,50,45,this);
                (new Thread(gm)).start();
                System.out.println("finished");
                simulating=false;
            }
        }
        superpanel.requestFocus();
        draw();
    }
    public void stateChanged(ChangeEvent e) {
        JSlider source = (JSlider)e.getSource();
        if (source==nodesizeslider) {
            int tempval = source.getValue()/2;
            nodesize = tempval*2;
        } else if (source==movespeedslider) {
            movespeed = source.getValue();
        }
        superpanel.requestFocus();
    }
    public void mouseClicked(double x, double y) {
        if (toolselected != 1) {
            memberconnected = false;
        }
        if (toolselected == 0) {
            if(!snap){
                x -= width/2;
                y -= height/2;
                x/=zoomscale;
                y/=zoomscale;
                x += width/2;
                y += height/2;
                double realx = x-xoffset;
                double realy = y-yoffset;
                Node newn = new Node(realx,realy,locked);
                b.addNode(newn,locked);
            }
            else if(snap){
                x -= width/2;
                y -= height/2;
                x/=zoomscale;
                y/=zoomscale;
                x += width/2;
                y += height/2;
                double realx = x-xoffset;
                double realy = y-yoffset;
                if(realx%snapScale < snapScale/2){
                    realx -= x%snapScale;
                }
                else{
                    realx += snapScale-(x%snapScale);
                }
                if(realy%snapScale < snapScale/2){
                    realy -= y%snapScale;
                }
                else{
                    realy+= snapScale-(y%snapScale);
                }
                Node newn = new Node(realx,realy,locked);
                b.addNode(newn,locked);
            }
        } else if (toolselected == 1) {
            if (!memberconnected) {
                x-= width/2;
                y -= height/2;
                x/=zoomscale;
                y/=zoomscale;
                x += width/2;
                y += height/2;
                double realx = x-xoffset;
                double realy = y-yoffset;
                boolean found = false;
                ArrayList<Node> bnodes = b.getNodes();
                for(int i=bnodes.size()-1;i>-1;i--) {
                    Node n = bnodes.get(i);
                    double nx = n.getX();
                    double ny = n.getY();
                    if (distance(nx,ny,realx,realy) <= nodesize/2) {
                        memberconnected = true;
                        firstnode=n;
                        found=true;
                        break;
                    }
                }
                if (!found) {
                    ArrayList<Node> blocknodes = b.getNodesLocked();
                    for(int i=blocknodes.size()-1;i>-1;i--) {
                        Node n = blocknodes.get(i);
                        double nx = n.getX();
                        double ny = n.getY();
                        if (distance(nx,ny,realx,realy) <= nodesize/2) {
                            memberconnected = true;
                            firstnode = n;
                            found=true;
                            break;
                        }
                    }
                }
            } else {
                x-= width/2;
                y -= height/2;
                x/=zoomscale;
                y/=zoomscale;
                x += width/2;
                y += height/2;
                double realx = x-xoffset;
                double realy = y-yoffset;
                boolean found = false;
                ArrayList<Node> bnodes = b.getNodes();
                for(int i=bnodes.size()-1;i>-1;i--) {
                    Node n = bnodes.get(i);
                    double nx = n.getX();
                    double ny = n.getY();
                    if (distance(nx,ny,realx,realy) <= nodesize/2) {
                        memberconnected = false;
                        b.addMember(firstnode, n, true, locked);
                        firstnode=null;
                        found=true;
                        break;
                    }
                }
                if (!found) {
                    ArrayList<Node> blocknodes = b.getNodesLocked();
                    for(int i=blocknodes.size()-1;i>-1;i--) {
                        Node n = blocknodes.get(i);
                        double nx = n.getX();
                        double ny = n.getY();
                        if (distance(nx,ny,realx,realy) <= nodesize/2) {
                            memberconnected = false;
                            firstnode = null;
                            b.addMember(firstnode, n, true, locked);
                            found=true;
                            break;
                        }
                    }
                }
            }
        } else if (toolselected == 3) {
            x -= width/2;
            y -= height/2;
            x/=zoomscale;
            y/=zoomscale;
            x += width/2;
            y += height/2;
            double realx = x-xoffset;
            double realy = y-yoffset;
            boolean found = false;
            ArrayList<Node> bnodes = b.getNodes();
            for(int i=bnodes.size()-1;i>-1;i--) {
                Node n = bnodes.get(i);
                double nx = n.getX();
                double ny = n.getY();
                if (distance(nx,ny,realx,realy) <= nodesize/2) {
                    b.removeNode(n,false);
                    found=true;
                    break;
                }
            }
            if (!found) {
                ArrayList<Node> blocknodes = b.getNodesLocked();
                for(int i=blocknodes.size()-1;i>-1;i--) {
                    Node n = blocknodes.get(i);
                    double nx = n.getX();
                    double ny = n.getY();
                    if (distance(nx,ny,realx,realy) <= nodesize/2) {
                        b.removeNode(n,true);
                        break;
                    }
                }
            }
        }
        superpanel.requestFocus();
    }
    private double distance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(((x2-x1)*(x2-x1))+((y2-y1)*(y2-y1)));
    }
    public void scroll(int amount) {
        if (!(zoomscale+(amount*(Math.sqrt(zoomscale))) <= 1)) {
            zoomscale+=amount*(Math.sqrt(zoomscale));
        } else {
            zoomscale = 1;
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
        zoomscale=1;
    }
    public void lock() {
        locked = true;
    }
    public void unlock() {
        locked = false;
    }
    public void escape() {
        memberconnected = false;
    }
}