package Game.GameObjects;

import Game.GameObjects.Bullets.Explosion;
import Game.Physics;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class Missile extends GameObject {

    public Missile(double startX, double startY) {
        super(startX, startY, 30, 30);
        isFixed = false;
        isSolid = false;
        destructible = true;
        hp = 1;
        try {
            image = ImageIO.read(new File(".\\src\\Game\\Textures\\bomb.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void move(double diffSeconds) {
        super.move(diffSeconds);
    }

    @Override
    public void checkCollision() {
        List<GameObject> collidingObjects = Physics.getCollisions(this);

        for (int i = 0; i < collidingObjects.size(); i++) {
            GameObject collidingObject = collidingObjects.get(i);

            if (collidingObject.isSolid) {
                //check if Game.GameObjects.CharacterObject.Player is on Object
                if (y + height > collidingObject.y && oldY + height <= collidingObject.y && ySpeed >= 0) {

                    y = collidingObject.y - height;
                    ySpeed = 0;
                    onGround = true;
                    Explosion explosion = new Explosion(x,y,200, true);
                    explosion.explode();
                    hp = 0;
                }

                //check if Game.GameObjects.CharacterObject.Player is touching bottom side of object
                if (y < collidingObject.y + collidingObject.height && oldY >= collidingObject.y + collidingObject.height && ySpeed <= 0) {

                    y = collidingObject.y + collidingObject.height;
                    ySpeed *= 0.99;
                }

                //left side
                if (x + width > collidingObject.x && oldX + width <= collidingObject.x && xSpeed >= 0) {
                    x = collidingObject.x - width;
                    xSpeed = 0;
                }

                //right side
                if (x < collidingObject.x + collidingObject.width && oldX >= collidingObject.x + collidingObject.width && xSpeed <= 0) {
                    x = collidingObject.x + collidingObject.width;
                    xSpeed = 0;
                }
            }
        }
    }
}
