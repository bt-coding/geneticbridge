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
            display.keypressed(0);
            //display.offset(10, 0);
        } else if (key == KeyEvent.VK_UP) {
            display.keypressed(1);
            //display.offset(0,10);
        } else if (key == KeyEvent.VK_RIGHT) {
            display.keypressed(2);
            //display.offset(-10,0);
        } else if (key == KeyEvent.VK_DOWN) {
            display.keypressed(3);
            //display.offset(0,-10);
        } else if (key == KeyEvent.VK_SPACE) {
            display.goHome();
        }
    }
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            display.keyreleased(0);
            //display.offset(10, 0);
        } else if (key == KeyEvent.VK_UP) {
            display.keyreleased(1);
            //display.offset(0,10);
        } else if (key == KeyEvent.VK_RIGHT) {
            display.keyreleased(2);
            //display.offset(-10,0);
        } else if (key == KeyEvent.VK_DOWN) {
            display.keyreleased(3);
            //display.offset(0,-10);
        }
    }
}