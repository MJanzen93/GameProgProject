package Game;

import Game.GameObjects.GameObject;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

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
    { graphics.setColor(new Color(192, 253, 255));
        graphics.fillRect(0, 0, ConstantValues.WORLDPART_WIDTH, ConstantValues.WORLD_HEIGHT);
    }

    public void draw(GameObject gObj)
    {
        //check if obj is not in cameras view
        //For x
        if(gObj.x + gObj.width < world.worldPartX && gObj.x > world.worldPartX + ConstantValues.WORLDPART_WIDTH){
            return;
        }
        //for y
        if(gObj.y + gObj.height < world.worldPartY && gObj.y > world.worldPartY + ConstantValues.WORLDPART_HEIGHT){
            return;
        }

        int x = (int) (gObj.x - world.worldPartX);
        int y = (int) (gObj.y - world.worldPartY);

        graphics.setColor(gObj.COLOR);
        graphics.fillRect(x, y, gObj.width, gObj.height);
        graphics.setColor(Color.BLACK);
        graphics.drawRect(x, y, gObj.width, gObj.height);


        //Quick HP Bar drawing
        if(gObj.hasHP && gObj.maxHP > gObj.hp) {
            int hp = gObj.hp;
            int maxHP = gObj.maxHP;
            if(gObj.maxHP < 30) {
                hp = (hp*10/maxHP)*3;
                maxHP = 30;
            }
            graphics.setColor(new Color(201, 0, 0));
            graphics.fillRect(x+gObj.width/2-maxHP/2, y-30, hp, 10);
            graphics.setColor(new Color(0, 0, 0));
            graphics.drawRect(x+gObj.width/2-maxHP/2, y-30, maxHP, 10);
        }
    }


    public void redraw()
    { this.getGraphics().drawImage(imageBuffer, 0, 0, this);
    }

    public final void setWorld(World world_)  {this.world = world_;}
}
