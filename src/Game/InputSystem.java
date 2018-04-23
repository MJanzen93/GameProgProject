package Game;

import java.awt.event.*;

public class InputSystem implements KeyListener, MouseListener, MouseMotionListener {

    boolean leftPressed, rightPressed, upPressed, downPressed, mousePressed;
    int mouseX, mouseY;

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyChar()) {
            case 'w':
                upPressed = true;

                break;
            case 's':
                downPressed = true;
                break;
            case 'a':
                leftPressed = true;
                break;
            case 'd':
                rightPressed = true;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch(e.getKeyChar()) {
            case 'w':
                upPressed = false;
                break;
            case 's':
                downPressed = false;
                break;
            case 'a':
                leftPressed = false;
                break;
            case 'd':
                rightPressed = false;
                break;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        mousePressed = true;
        mouseX = e.getX();
        mouseY = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mousePressed = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
