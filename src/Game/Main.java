package Game;

import Game.GameObjects.GameObject;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Main {

    public static void main(String[] args) {

        JFrame gameFrame = new JFrame("Game.World");

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        gameFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        gameFrame.setSize(ConstantValues.WORLDPART_WIDTH,ConstantValues.WORLDPART_HEIGHT);

        //set Fullscreen
        ConstantValues.WORLDPART_WIDTH = screenSize.width;
        ConstantValues.WORLDPART_HEIGHT = screenSize.height;
        gameFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        gameFrame.getRootPane().addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                ConstantValues.WORLDPART_WIDTH = gameFrame.getWidth();
                ConstantValues.WORLDPART_HEIGHT = gameFrame.getHeight();
            }
        });

        InputSystem inputSystem = new InputSystem();

        World world = new World();
        world.setInputSystem(inputSystem);

        //GameObject.setPhysics(world.getPhysics());
        Physics.setWorld(world);
        GameObject.setWorld(world);

        WorldViewer wViewer = new WorldViewer(inputSystem);
        wViewer.setFocusable(true);
        wViewer.setWorld(world);
        gameFrame.setContentPane(wViewer);

        gameFrame.setVisible(true);



        world.setGraphicSystem(wViewer);
        world.init();
        world.run();


    }
}
