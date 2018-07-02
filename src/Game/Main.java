package Game;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowEvent;

import javax.swing.*;

import Game.GameObjects.GameObject;
import Game.Menu.GameMenu;

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



        JPanel menu = new GameMenu();
        gameFrame.getContentPane().add(menu);
        gameFrame.setVisible(true);
        int clickedPanel;
        int level = 1;
        while(true) {
            clickedPanel = ((GameMenu) menu).clickedPanel;
            if(clickedPanel != 100) {
                System.out.print("");
            }
            if(clickedPanel == 100) {
                InputSystem inputSystem = new InputSystem();
                World world = new World(level);
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
                if(world.gameOver) {
                    menu = new GameMenu();
                    gameFrame.getContentPane().removeAll();
                    gameFrame.setContentPane(menu);
                    gameFrame.setVisible(true);
                    ((GameMenu) menu).clickedPanel = 0;
                }
                if(world.nextLevel) {
                    level++;
                }
            }

        }



    }
}
