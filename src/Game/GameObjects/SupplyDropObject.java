package Game.GameObjects;

import Game.GameObjects.Items.ItemObject;
import Game.Physics;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class SupplyDropObject extends GameObject {

    private double fallSpeed = 50;

    private Image par;
    private Image scaled;

    public SupplyDropObject(double startX, double startY, int width, int height) {
        super(startX, startY, width, height);
        destructible = true;
        hp = 10;
        maxHP = 10;
        setColor(Color.ORANGE);
        isSolid  = true;
        try {
            par = ImageIO.read(new File(".\\src\\Game\\Textures\\parachute2.png"));
            scaled = par.getScaledInstance(160,160, 0);
            image = ImageIO.read(new File(".\\src\\Game\\Textures\\SimpleCrate.png"));
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
            ySpeed =5000*diffSeconds;
        }
        if(onGround){
            scaled = null;
        }
    }

    @Override
    public void checkCollision() {
        if(isSolid){
            List<GameObject> collidingObjects = Physics.getCollisions(this);

            for(int i = 0; i < collidingObjects.size(); i++) {
                GameObject collidingObject = collidingObjects.get(i);

                if(collidingObject.isSolid && !collidingObject.isItem) {
                    //check if Game.GameObjects.CharacterObject.Player is on Object
                    if(y + height > collidingObject.y && oldY + height <= collidingObject.y && ySpeed >= 0) {

                        y = collidingObject.y - height;
                        ySpeed = 0;
                        onGround = true;
                    }

                    //check if Game.GameObjects.CharacterObject.Player is touching bottom side of object
                    if(y < collidingObject.y + collidingObject.height && oldY >= collidingObject.y + collidingObject.height && ySpeed <= 0) {

                        y = collidingObject.y + collidingObject.height;
                        ySpeed *= 0.99;
                    }

                    //left side
                    if(x + width > collidingObject.x && oldX + width <= collidingObject.x && xSpeed >= 0) {
                        x = collidingObject.x - width;
                        xSpeed = 0;
                    }

                    //right side
                    if(x < collidingObject.x + collidingObject.width && oldX >= collidingObject.x + collidingObject.width && xSpeed <= 0) {
                        x = collidingObject.x + collidingObject.width;
                        xSpeed = 0;
                    }
                }

            }

            if(collidingObjects.size() == 0) {
                onGround = false;
            }

            /*
            if(y + height > 760){
                y = 760-height;
                ySpeed = 0;
                onGround = true;
                jumping = false;
            }
            */
        }
    }

    @Override
    public void draw(Graphics graphics) {
        super.draw(graphics);
        int x = (int) (this.x - world.worldPartX);
        int y = (int) (this.y - world.worldPartY);
        if(!onGround && hp == maxHP && scaled != null){
            graphics.drawImage(scaled, x-scaled.getWidth(null)/2 + width/2, y-scaled.getHeight(null), null, null);
        }
    }

}
