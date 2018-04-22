import javax.swing.*;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Main {

    public static void main(String[] args) {

        JFrame gameFrame = new JFrame("World");

        gameFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        gameFrame.setSize(ConstantValues.WORLDPART_WIDTH,ConstantValues.WORLDPART_HEIGHT);

        InputSystem inputSystem = new InputSystem();

        World world = new World();
        world.setInputSystem(inputSystem);

        GameObject.setPhysics(world.getPhysics());
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
