import java.util.*;
import java.awt.*;
import javax.swing.*;
public class DisplayInit {
    public static void main(String[] args) {
        int WIDTH = 1920;
        int HEIGHT = 1080;
        
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
                
        panel.add(controlpanel, BorderLayout.NORTH);
        
        controlpanel.setFocusable(false);
        
        ArrayList<Force> forces = new ArrayList<Force>();
        
        //Bridge bridge = new Bridge(30,new double[]{-400,-200,400,200},forces,null);
        Bridge bridge = new Bridge();
        
        Display display = new Display(panel,nodebutton, memberbutton, forcebutton, erasebutton, homebutton, clearbutton, WIDTH, HEIGHT, bridge);
        
        
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
    }
}