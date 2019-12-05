import javax.swing.*;
import java.awt.*; 
public class Display extends JComponent{
    Bridge b;
    public Display(){
        super();
    }
    public void draw(){
        super.repaint();
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        if (b==null) {
            System.out.println("Value of bridge is null");
            return;
        }
    }
    public void drawBridge(Bridge b) {
        this.b = b;
        this.repaint();
    }
}