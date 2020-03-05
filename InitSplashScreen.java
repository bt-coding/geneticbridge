import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.util.*;
import java.io.*;
public class InitSplashScreen {
    public static void openSplash() {
        JFrame frame = new JFrame("Splash Screen");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setSize(512,512);
        frame.setSize(512,556);
        frame.setLocationRelativeTo(null);
        frame.setUndecorated(true); 
        frame.setResizable(false);
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
        
        JPanel loadingscreen = new JPanel();
        loadingscreen.setLayout(new BoxLayout(loadingscreen, BoxLayout.Y_AXIS));
        
        
        //JPanel titlePanel = new JPanel();
        //titlePanel.setSize(512, 22);
        
        JPanel titlePanel = new JPanel();
        //titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.setPreferredSize(new Dimension(512,30));
        titlePanel.setMinimumSize(new Dimension(0,0));
        titlePanel.setMaximumSize(new Dimension(512,30));
        
        
        JLabel apptitle = new JLabel("GENETIC BRIDGE", JLabel.CENTER);
        apptitle.setOpaque(true);
        apptitle.setPreferredSize(new Dimension(512, 22));
        apptitle.setBackground(new Color(146, 255, 51));
        
        //apptitle.setFont(22);
        apptitle.setFont(new Font("Dialog", Font.BOLD, 22));
        //apptitle.setSize(512, 22);
        //apptitle.setHorizontalAlignment(SwingConstants.CENTER);
        
        titlePanel.add(apptitle);
        
        JPanel splash = new SplashScreen();
        
        JPanel creditPanel = new JPanel();
        creditPanel.setPreferredSize(new Dimension(512,30));
        creditPanel.setMinimumSize(new Dimension(0,0));
        creditPanel.setMaximumSize(new Dimension(512,30));
        
        JLabel credittitle = new JLabel("Created by Tristan Lonsway & Brian Hulbert", JLabel.CENTER);
        credittitle.setOpaque(true);
        credittitle.setPreferredSize(new Dimension(512, 22));
        credittitle.setBackground(new Color(146, 255, 51));
        
        creditPanel.add(credittitle);
        
        
        //loadingscreen.add(apptitle);
        loadingscreen.add(titlePanel);
        loadingscreen.add(splash);
        loadingscreen.add(creditPanel);
        
        frame.add(loadingscreen);
        
        //frame.add(titlePanel);
        //frame.add(splash);
        
        
        
        
        splash.setVisible(true);
        //splash.draw();
        
        frame.show();
        
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        frame.setVisible(false);
        frame.dispose();
        
        return;
    } 
}