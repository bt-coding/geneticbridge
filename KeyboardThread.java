import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
public class KeyboardThread extends KeyAdapter {
    Display display;
    public KeyboardThread(Display display) {
        this.display = display;
    }
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            display.keypressed(new int[]{1,0,0,0});
            //display.offset(10, 0);
        } else if (key == KeyEvent.VK_UP) {
            display.keypressed(new int[]{0,1,0,0});
            //display.offset(0,10);
        } else if (key == KeyEvent.VK_RIGHT) {
            display.keypressed(new int[]{0,0,1,0});
            //display.offset(-10,0);
        } else if (key == KeyEvent.VK_DOWN) {
            display.keypressed(new int[]{0,0,0,1});
            //display.offset(0,-10);
        }
    }
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            display.keyreleased(new int[]{1,0,0,0});
            //display.offset(10, 0);
        } else if (key == KeyEvent.VK_UP) {
            display.keyreleased(new int[]{0,1,0,0});
            //display.offset(0,10);
        } else if (key == KeyEvent.VK_RIGHT) {
            display.keyreleased(new int[]{0,0,1,0});
            //display.offset(-10,0);
        } else if (key == KeyEvent.VK_DOWN) {
            display.keyreleased(new int[]{0,0,0,1});
            //display.offset(0,-10);
        }
    }
}