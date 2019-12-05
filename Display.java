import javax.swing.*;
import java.awt.*; 
public class Display extends JComponent{
    public Display(){
        super();
    }
    public void draw(){
        super.repaint();
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.RED);
        g.fillRect(100,100,100,100);
    }
}