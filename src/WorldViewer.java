import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class WorldViewer extends JPanel
{
    private GraphicsConfiguration graphicsConf =
            GraphicsEnvironment.getLocalGraphicsEnvironment().
                    getDefaultScreenDevice().getDefaultConfiguration();
    private BufferedImage imageBuffer;
    private Graphics      graphics;
    private World world;


    public WorldViewer(InputSystem inputSystem)
    {
        this.setSize(ConstantValues.WORLDPART_WIDTH,ConstantValues.WORLDPART_HEIGHT);
        imageBuffer = graphicsConf.createCompatibleImage(
                this.getWidth(), this.getHeight());
        graphics = imageBuffer.getGraphics();
        this.addKeyListener(inputSystem);
        this.addMouseListener(inputSystem);
        this.addMouseMotionListener(inputSystem);
    }

    public void clear()
    { graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, ConstantValues.WORLDPART_WIDTH, ConstantValues.WORLD_HEIGHT);
    }

    public void draw(GameObject gObj)
    {

        int x = (int) (gObj.x - world.worldPartX);
        int y = (int) (gObj.y - world.worldPartY);

        graphics.setColor(gObj.COLOR);
        graphics.fillRect(x, y, gObj.width, gObj.height);
        graphics.setColor(Color.BLACK);
        graphics.drawRect(x, y, gObj.width, gObj.height);
    }

    public void redraw()
    { this.getGraphics().drawImage(imageBuffer, 0, 0, this);
    }

    public final void setWorld(World world_)  {this.world = world_;}
}
