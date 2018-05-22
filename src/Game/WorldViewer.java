package Game;

import Game.GameObjects.GameObject;
import Game.GameObjects.SupplyDropObject;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class WorldViewer extends JPanel {
    private GraphicsConfiguration graphicsConf =
            GraphicsEnvironment.getLocalGraphicsEnvironment().
                    getDefaultScreenDevice().getDefaultConfiguration();
    private BufferedImage imageBuffer;
    private Graphics graphics;
    private World world;

    private Image background;


    public WorldViewer(InputSystem inputSystem) {
        this.setSize(ConstantValues.WORLDPART_WIDTH, ConstantValues.WORLDPART_HEIGHT);
        imageBuffer = graphicsConf.createCompatibleImage(
                this.getWidth(), this.getHeight());
        graphics = imageBuffer.getGraphics();
        this.addKeyListener(inputSystem);
        this.addMouseListener(inputSystem);
        this.addMouseMotionListener(inputSystem);
        try {
            background = ImageIO.read(new File(".\\src\\Game\\Textures\\BG.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void clear() {
        //graphics.setColor(new Color(192, 253, 255));
        //graphics.fillRect(0, 0, ConstantValues.WORLDPART_WIDTH, ConstantValues.WORLD_HEIGHT);
        graphics.drawImage(background, 0, 0, ConstantValues.WORLDPART_WIDTH, ConstantValues.WORLDPART_HEIGHT, null);
        /*
        if (world.worldPartX == 0) {
            graphics.drawImage(background, 0, 0, ConstantValues.WORLDPART_WIDTH, ConstantValues.WORLDPART_HEIGHT, null);
        } else if (world.worldPartX > 0 && world.worldPartX < ConstantValues.WORLDPART_WIDTH) {
            graphics.drawImage(background, -(int) world.worldPartX, 0, ConstantValues.WORLDPART_WIDTH, ConstantValues.WORLDPART_HEIGHT, null);
            graphics.drawImage(background, ConstantValues.WORLDPART_WIDTH - (int) world.worldPartX, 0, ConstantValues.WORLDPART_WIDTH, ConstantValues.WORLDPART_HEIGHT, null);
        }
        */
    }

    public boolean draw(GameObject gObj) {
        //check if obj is not in cameras view
        //For x
        if ((gObj.x + gObj.width) < world.worldPartX || gObj.x > (world.worldPartX + ConstantValues.WORLDPART_WIDTH)) {
            return false;
        }
        //for y
        if (gObj.y + gObj.height < world.worldPartY || gObj.y > world.worldPartY + ConstantValues.WORLDPART_HEIGHT +100) {
            return false;
        }

        gObj.draw(graphics);
        return true;
    }

    public void redraw() {
        this.getGraphics().drawImage(imageBuffer, 0, 0, this);
    }

    public final void setWorld(World world_) {
        this.world = world_;
    }
}
