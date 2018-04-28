package Game.GameObjects;

import Game.GameObjects.Items.ItemObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SupplyDropObject extends GameObject {

    private double fallSpeed = 50;

    private BufferedImage par;
    private Image scaled;

    public SupplyDropObject(double startX, double startY, int width, int height) {
        super(startX, startY, width, height);
        hasHP = true;
        hp = 10;
        maxHP = 10;
        setColor(Color.ORANGE);
        isSolid  = true;
        try {
            par = ImageIO.read(new File(".\\src\\Game\\Textures\\parachute.png"));
            scaled = par.getScaledInstance(80,80, 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void move(double diffSeconds) {
        super.move(diffSeconds);
        if(hp <= 0){
            ItemObject item = ItemObject.createRandomItem();
            item.x = x;
            item.y = y;
            world.gameObjects.add(item);
        }
        if(hp == maxHP){
            ySpeed = 5000*diffSeconds;
        }
    }

    @Override
    public void checkCollision() {
        super.checkCollision();
    }

    @Override
    public void draw(Graphics graphics) {
        super.draw(graphics);
        int x = (int) (this.x - world.worldPartX);
        int y = (int) (this.y - world.worldPartY);
        if(!onGround && hp == maxHP && scaled != null){
            graphics.drawImage(scaled, x-scaled.getWidth(null)/2 + width/2, y-scaled.getHeight(null), null, null);
        }
        graphics.setColor(COLOR);
        graphics.fillRect(x, y, width, height);
        graphics.setColor(Color.BLACK);
        graphics.drawRect(x, y, width, height);
    }

}
