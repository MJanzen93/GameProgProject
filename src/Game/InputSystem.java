package Game;

import ch.aplu.xboxcontroller.XboxController;
import ch.aplu.xboxcontroller.XboxControllerListener;

import java.awt.event.*;

public class InputSystem implements KeyListener, MouseListener, MouseMotionListener, XboxControllerListener {

    public boolean leftPressed, rightPressed, upPressed, downPressed, mousePressed,altPressed, spacePressed, yPressed;
    public int mouseX, mouseY;

    //used for controllerInputs
    public double moveMagnitude= 0.0;
    public double shootMagnitude = 0.0;
    public double shootDirection = 0.0;
    private XboxController xc;

    public InputSystem () {
        xc = new XboxController();
        xc.addXboxControllerListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        moveMagnitude = 1.0; // is set to full force since keyboard is used
        switch(e.getKeyCode()) {
            case KeyEvent.VK_W:
                upPressed = true;
                break;
            case KeyEvent.VK_S:
                downPressed = true;
                break;
            case KeyEvent.VK_A:
                leftPressed = true;
                break;
            case KeyEvent.VK_D:
                rightPressed = true;
                break;
            case KeyEvent.VK_ALT:
                altPressed = true;
                break;
            case KeyEvent.VK_SPACE:
                spacePressed = true;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_W:
                upPressed = false;
                break;
            case KeyEvent.VK_S:
                downPressed = false;
                break;
            case KeyEvent.VK_A:
                leftPressed = false;
                break;
            case KeyEvent.VK_D:
                rightPressed = false;
                break;
            case KeyEvent.VK_ALT:
                altPressed = false;
                break;
            case KeyEvent.VK_SPACE:
                spacePressed = false;
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

    @Override
    public void buttonA(boolean b) {
        if(b) {
            //System.out.println("Button A gedrückt");
            upPressed = true;
        } else {
            //System.out.println("Button A losgelassen");
            upPressed = false;
        }
    }

    @Override
    public void buttonB(boolean b) {
        if(b) {
            downPressed = true;
        } else {
            downPressed = false;
        }
    }

    @Override
    public void buttonX(boolean b) {

    }

    @Override
    public void buttonY(boolean b) {
        if(b) {
            yPressed = true;
        } else {
            yPressed = true;
        }
    }

    @Override
    public void back(boolean b) {

    }

    @Override
    public void start(boolean b) {

    }

    @Override
    public void leftShoulder(boolean b) {

    }

    @Override
    public void rightShoulder(boolean b) {

    }

    @Override
    public void leftThumb(boolean b) {

    }

    @Override
    public void rightThumb(boolean b) {

    }

    @Override
    public void dpad(int i, boolean b) {

    }

    @Override
    public void leftTrigger(double v) {

    }

    @Override
    public void rightTrigger(double v) {

    }

    @Override
    public void leftThumbMagnitude(double v) {
        moveMagnitude = v;
    }

    @Override
    public void leftThumbDirection(double v) {
        if(moveMagnitude > 0.25) { //deadzone of the stick
            if (v < 135 && v <= 135) {
                rightPressed = true;
                leftPressed = false;
            } /*else if (v >= 135 && v <= 225) {
                rightPressed = false;
                leftPressed = false;
                downPressed = false;
            } */else if(v >= 225){
                rightPressed = false;
                leftPressed = true;
            }
        } else {
            rightPressed = false;
            leftPressed = false;
            downPressed = false;
        }
    }

    @Override
    public void rightThumbMagnitude(double v) {
        shootMagnitude = v;

    }

    @Override
    public void rightThumbDirection(double v) {
        shootDirection = v;
    }

    @Override
    public void isConnected(boolean b) {

    }
}
