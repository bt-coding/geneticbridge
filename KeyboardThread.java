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
            display.offset(-10, 0);
        } else if (key == KeyEvent.VK_UP) {
            display.offset(0,10);
        } else if (key == KeyEvent.VK_RIGHT) {
            display.offset(10,0);
        } else if (key == KeyEvent.VK_DOWN) {
            display.offset(0,-10);
        }
    }
}