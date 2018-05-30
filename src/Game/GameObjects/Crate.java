package Game.GameObjects;

import Game.GameObjects.BackgroundObjects.BrokenPart;
import Game.GameObjects.Items.ItemObject;
import Game.Physics;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class Crate extends GameObject {
    public Crate(double startX, double startY) {
        super(startX, startY, 50, 50);
        destructible = true;
        hp = 10;
        maxHP = 10;

        isSolid = true;
        isFixed = false;

        try {
            image = ImageIO.read(new File(".\\src\\Game\\Textures\\objects\\Resized\\Crate.png"));
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

        if(hp <= 0){
            world.fixedObjects.add(new BrokenPart(this, 1));
            world.fixedObjects.add(new BrokenPart(this, 2));
            world.fixedObjects.add(new BrokenPart(this, 3));
            world.fixedObjects.add(new BrokenPart(this, 4));
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

        }
    }
}
