import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
public class MouseClicker implements MouseListener {
    Display display;
    public MouseClicker(Display display) {
        this.display = display;
    }
    public void mouseClicked(MouseEvent e) {
        Point mpoint = MouseInfo.getPointerInfo().getLocation();
        double mx = mpoint.getX();
        double my = mpoint.getY();
        Point dpoint = display.getLocationOnScreen();
        double fx = dpoint.getX();
        double fy = dpoint.getY();
        display.mouseClicked(mx-fx, my-fy);
    }
    public void mouseEntered(MouseEvent e) {
        
    }
    public void mouseExited(MouseEvent e) {
        
    }
    public void mousePressed(MouseEvent e) {
        
    }
    public void mouseReleased(MouseEvent e) {
        
    }
}