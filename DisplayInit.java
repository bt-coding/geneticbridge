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
        
        JPanel panel = new JPanel(new BorderLayout());
        
        JPanel controlpanel = new JPanel();
        
        controlpanel.add(nodebutton);
        controlpanel.add(memberbutton);
        controlpanel.add(forcebutton);
        
        panel.add(controlpanel, BorderLayout.NORTH);
        
        Display display = new Display(nodebutton, memberbutton, forcebutton, WIDTH, HEIGHT);
        
        panel.add(display, BorderLayout.CENTER);
        
        display.setVisible(true);
        display.draw();
        
        MouseClicker mlistener = new MouseClicker(display);
        MouseScrollWheelThread mscrollthread = new MouseScrollWheelThread(display);
        panel.addMouseListener(mlistener);
        panel.addMouseWheelListener(mscrollthread);
        
        KeyboardThread keylistener = new KeyboardThread(display);
        panel.addKeyListener(keylistener);
        panel.setFocusable(true);
        
        
        Bridge sample = new Bridge(null);
        
        
        frame.add(panel);
        panel.setVisible(true);
        frame.show();
        
    }
}