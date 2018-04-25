package Game.World_Editor;

import java.awt.event.*;

public class InputSystem implements KeyListener, MouseListener, MouseMotionListener, MouseWheelListener {

    int mouseX, mouseY;
    int mouseXd, mouseYd;
    double zoom = 0;

    boolean mousePressed = false;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        zoom = 0;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
        mouseXd = mouseX;
        mouseYd = mouseY;
        mousePressed  = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mousePressed  = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseXd = e.getX();
        mouseYd = e.getY();
        mousePressed  = true;
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        if (e.isAltDown()) {
            if (e.getWheelRotation() > 0) {
                //zoom in
                zoom += 1;
            } else {
                //zoom out
                zoom -= 1;
            }
        }
    }
}
