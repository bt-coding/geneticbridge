import javax.swing.*;
public class Main{
    public static void main(String[] args){
        JFrame frame = new JFrame("Genetic Bridge");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        frame.setUndecorated(false);
        
        int frameRate = 32;
        Display screen = new Display();
        
        frame.add(screen);
        frame.setVisible(true);
        new Thread(new FrameRateThread(screen,frameRate)).start();
    }
}   