import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.util.*;
import java.io.*;
public class SplashScreen extends JPanel {
    int WIDTH = 512;
    int HEIGHT = 512;
    BufferedImage splashImage;
    public SplashScreen() {
        super();
        splashImage = null;
        try {
            splashImage = ImageIO.read(new File("splashimage.png"));
        } catch (Exception e) {
            System.out.println("Error occured while loading splash screen image");
            e.printStackTrace();
            return;
        }
        this.draw();
    }
    public void draw() {
        this.repaint();
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g); 
        g.drawImage(resize(splashImage,WIDTH,HEIGHT),0,0,null);
    }    
    public static BufferedImage resize(BufferedImage img, int newW, int newH) { 
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return dimg;
    }      
}