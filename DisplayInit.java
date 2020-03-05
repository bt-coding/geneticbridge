import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.util.*;
import java.io.*;
//import com.seaglasslookandfeel.*;
public class DisplayInit {
    public static void main(String[] args) {
        int WIDTH = 1920;
        int HEIGHT = 1080;
        
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            //UIManager.setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel");
        } catch (Exception e) {
            System .err.println("Failed to set look and feel");
            e.printStackTrace();
        }
        
        
        JFrame frame = new JFrame("Genetic Truss Analysis");
        frame.setSize(WIDTH,HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        //frame.setUndecorated(true);
        BufferedImage iconImage = null;
        try {
            iconImage = ImageIO.read(new File("splashimage.png"));
        } catch (Exception e) {
            System.out.println("Error occured while loading icon image");
            e.printStackTrace();
            return;
        }
        frame.setIconImage(iconImage);
        frame.setVisible(true);

        JButton nodebutton = new JButton("node");
        JButton memberbutton = new JButton("member");
        JButton forcebutton = new JButton("force");
        JButton erasebutton = new JButton("erase");
        JButton homebutton = new JButton("home");
        JButton clearbutton = new JButton("clear");
        JButton simulatebutton = new JButton("simulate");
        
        JPanel panel = new JPanel(new BorderLayout());
        
        JPanel controlpanel = new JPanel();
        
        controlpanel.add(nodebutton);
        controlpanel.add(memberbutton);
        controlpanel.add(forcebutton);
        controlpanel.add(erasebutton);
        controlpanel.add(homebutton);
        controlpanel.add(clearbutton);
        controlpanel.add(simulatebutton);
        
        //controlpanel.setBackground(Color.GRAY);
        controlpanel.setBackground(new Color(41, 163, 102));
        panel.add(controlpanel, BorderLayout.NORTH);
        
        controlpanel.setFocusable(false);
        controlpanel.setPreferredSize(new Dimension(0,36));
        controlpanel.setMinimumSize(new Dimension(400,36));
        
        JPanel leftpanel = new JPanel();
        leftpanel.setLayout(new BoxLayout(leftpanel, BoxLayout.Y_AXIS));
        
        JSlider nodesize = new JSlider(JSlider.HORIZONTAL,2,44,6);
        nodesize.setMajorTickSpacing(4);
        nodesize.setMinorTickSpacing(2);
        nodesize.setPaintTicks(true);
        nodesize.setPaintLabels(true);
        
        JLabel nodesizetitle = new JLabel("Node Size");
        nodesizetitle.setFont(new Font("Dialog", Font.BOLD, 22));
        
        JSlider movespeed = new JSlider(JSlider.HORIZONTAL,1,50,10);
        movespeed.setMajorTickSpacing(10);
        movespeed.setMajorTickSpacing(5);
        movespeed.setPaintTicks(true);
        movespeed.setPaintLabels(true);
        
        JLabel movespeedtitle = new JLabel("Movement Speed");
        movespeedtitle.setFont(new Font("Dialog", Font.BOLD, 22));

        JSlider snapsize = new JSlider(JSlider.HORIZONTAL,1,41,5);
        snapsize.setMajorTickSpacing(5);
        snapsize.setMinorTickSpacing(1);
        snapsize.setPaintTicks(true);
        snapsize.setPaintLabels(true);
        
        JLabel snapsizetitle = new JLabel("Grid Size");
        snapsizetitle.setFont(new Font("Dialog", Font.BOLD, 22));
        
        JCheckBox snapbox = new JCheckBox("Snap to Grid");
        snapbox.setSelected(false);
        snapbox.setMnemonic(KeyEvent.VK_S);
        
        Box.Filler spacer = new Box.Filler(new Dimension(0,0),new Dimension(0,50),new Dimension(0,50));
        
        
        //leftpanel.add(new Box.Filler(new Dimension(0,0),new Dimension(0,50),new Dimension(0,50)));
        leftpanel.add(nodesizetitle);
        leftpanel.add(nodesize);
        
        leftpanel.add(new Box.Filler(new Dimension(0,0),new Dimension(0,50),new Dimension(0,50)));
        leftpanel.add(movespeedtitle);
        leftpanel.add(movespeed);
        
        leftpanel.add(new Box.Filler(new Dimension(0,0),new Dimension(0,50),new Dimension(0,50)));
        leftpanel.add(snapsizetitle);
        leftpanel.add(snapsize);
        leftpanel.add(new Box.Filler(new Dimension(0,0),new Dimension(0,10),new Dimension(0,10)));
        leftpanel.add(snapbox);
        
        
        //leftpanel.setBackground(Color.GRAY);
        leftpanel.setBackground(new Color(66, 138, 102));
        
        panel.add(leftpanel, BorderLayout.WEST);
        
    
        
        
        ArrayList<Force> forces = new ArrayList<Force>();
        
        Bridge bridge = new Bridge(100,new double[]{-800,-400,800,400},forces,new ArrayList<Node>());
        //Bridge bridge = new Bridge();
        
        Display display = new Display(panel,nodebutton, memberbutton, forcebutton, erasebutton, homebutton, clearbutton, nodesize, movespeed, snapsize, simulatebutton, snapbox, WIDTH, HEIGHT, bridge);
        
        
        panel.add(display, BorderLayout.CENTER);
        
        display.setVisible(true);
        display.draw();
        
        MouseClicker mlistener = new MouseClicker(display);
        MouseScrollWheelThread mscrollthread = new MouseScrollWheelThread(display);
        display.addMouseListener(mlistener);
        panel.addMouseWheelListener(mscrollthread);
        
        KeyboardThread keylistener = new KeyboardThread(display);
        panel.addKeyListener(keylistener);
        panel.setFocusable(true);
        
        
        Bridge sample = new Bridge(null);
        
        
        frame.add(panel);
        panel.setVisible(true);
        frame.show();
        
        FrameRateThread frt = new FrameRateThread(display,60);
        (new Thread(frt)).start();
        panel.requestFocus();
    }
}