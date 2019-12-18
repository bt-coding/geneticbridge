import java.util.*;
import java.awt.*;
import javax.swing.*;
public class DisplayInit {
    public static void main(String[] args) {
        int WIDTH = 1920;
        int HEIGHT = 1080;
        
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println("Failed to set look and feel");
            e.printStackTrace();
        }
        
        
        JFrame frame = new JFrame("Genetic Truss Analysis");
        frame.setSize(WIDTH,HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
            
        JButton nodebutton = new JButton("node");
        JButton memberbutton = new JButton("member");
        JButton forcebutton = new JButton("force");
        JButton erasebutton = new JButton("erase");
        JButton homebutton = new JButton("home");
        JButton clearbutton = new JButton("clear");
        
        JPanel panel = new JPanel(new BorderLayout());
        
        JPanel controlpanel = new JPanel();
        
        controlpanel.add(nodebutton);
        controlpanel.add(memberbutton);
        controlpanel.add(forcebutton);
        controlpanel.add(erasebutton);
        controlpanel.add(homebutton);
        controlpanel.add(clearbutton);
        
        controlpanel.setBackground(Color.GRAY);
        
        panel.add(controlpanel, BorderLayout.NORTH);
        
        controlpanel.setFocusable(false);
        
        JPanel rightpanel = new JPanel();
        rightpanel.setLayout(new BoxLayout(rightpanel, BoxLayout.Y_AXIS));
        
        JSlider nodesize = new JSlider(JSlider.HORIZONTAL,2,44,6);
        nodesize.setMajorTickSpacing(4);
        nodesize.setMinorTickSpacing(2);
        nodesize.setPaintTicks(true);
        nodesize.setPaintLabels(true);
        
        JLabel nodesizetitle = new JLabel("Node Size");
        nodesizetitle.setFont(new Font("TimesRoman", Font.PLAIN, 22));
        
        JSlider movespeed = new JSlider(JSlider.HORIZONTAL,1,50,10);
        movespeed.setMajorTickSpacing(10);
        movespeed.setMajorTickSpacing(5);
        movespeed.setPaintTicks(true);
        movespeed.setPaintLabels(true);
        
        JLabel movespeedtitle = new JLabel("Movement Speed");
        movespeedtitle.setFont(new Font("TimesRoman", Font.PLAIN, 22));

        Box.Filler spacer = new Box.Filler(new Dimension(0,0),new Dimension(0,50),new Dimension(0,50));
        
        rightpanel.add(spacer);
        rightpanel.add(nodesizetitle);
        rightpanel.add(nodesize);
        
        rightpanel.add(spacer);
        rightpanel.add(movespeedtitle);
        rightpanel.add(movespeed);
        
        rightpanel.setBackground(Color.GRAY);
        
        panel.add(rightpanel, BorderLayout.WEST);
        
    
        
        
        ArrayList<Force> forces = new ArrayList<Force>();
        
        Bridge bridge = new Bridge(100,new double[]{-800,-400,800,400},forces,new ArrayList<Node>());
        //Bridge bridge = new Bridge();
        
        Display display = new Display(panel,nodebutton, memberbutton, forcebutton, erasebutton, homebutton, clearbutton, nodesize, movespeed, WIDTH, HEIGHT, bridge);
        
        
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