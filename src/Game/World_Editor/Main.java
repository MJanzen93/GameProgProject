package Game.World_Editor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

    public Main(){
        JFrame gameFrame = new JFrame("Game.World");
        gameFrame.add(createToolbar());

        gameFrame.setVisible(true);
        gameFrame.setSize(1000, 800);
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        InputSystem inputSystem = new InputSystem();

        World world = new World();
        world.setInputSystem(inputSystem);

        GameObject.setWorld(world);

        Camera wViewer = new Camera(inputSystem);
        wViewer.setFocusable(true);
        wViewer.setWorld(world);
        gameFrame.setContentPane(wViewer);

        gameFrame.setVisible(true);

        world.setGraphicSystem(wViewer);
        world.init();
        world.run();

    }

    public JToolBar createToolbar(){
        JToolBar toolBar = new JToolBar();
        JButton button = new JButton("New");
        JLabel labelWidth = new JLabel("Width: ");
        JLabel labelHeight = new JLabel("Height: ");
        JTextField textX = new JTextField();
        JTextField textY = new JTextField();
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
        toolBar.add(button);
        toolBar.add(labelWidth);
        toolBar.add(textX);
        toolBar.add(labelHeight);
        toolBar.add(textY);
        return toolBar;
    }

    public static void main(String[] args) {
        new Main();
    }
}
