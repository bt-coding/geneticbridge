import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
public class MouseScrollWheelThread implements MouseWheelListener {
    Display display;
    public MouseScrollWheelThread(Display display) {
        this.display = display;
    }
    public void mouseWheelMoved(MouseWheelEvent e) {
        display.scroll(e.getWheelRotation());
    }
    
}