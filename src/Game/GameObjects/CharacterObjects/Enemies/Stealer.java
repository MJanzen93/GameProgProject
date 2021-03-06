package Game.GameObjects.CharacterObjects.Enemies;

import Game.GameObjects.Bullets.BulletObject;
import Game.GameObjects.Bullets.ShootBullet;
import Game.GameObjects.GameObject;
import Game.GameObjects.Items.*;
import Game.AudioPlayer;
import Game.Physics;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class Stealer extends EnemyObject {
    public Stealer(double startX, double startY, int width, int height) {
        super(startX, startY, width, height);
        setColor(new Color(54, 54, 54, 255));
        try {
            image = ImageIO.read(new File(".\\src\\Game\\Textures\\stealer.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void move(double diffSeconds) {
        super.move(diffSeconds);

        double distanceToPlayer = world.player.x - x;

        if(Math.abs(distanceToPlayer) < 400 || maxHP > hp) {
            if(distanceToPlayer > 200 ) {
                xSpeed = 100;
            } else if(distanceToPlayer < -200) {
                xSpeed = -100;
            } else {
                xSpeed = 0;
            }

            if(bulletCooldown > 0) {
                bulletCooldown -= diffSeconds;
            } else {
                bulletCooldown = bulletCooldownfinal;
                shootBullet();
            }
        }

    }

    @Override
    public void checkCollision() {
        List<GameObject> collidingObjects = Physics.getCollisions(this);

        for(int i = 0; i < collidingObjects.size(); i++) {
            Game.GameObjects.GameObject collidingObject = collidingObjects.get(i);

            //apply Item
            if(collidingObject.isItem){
                ItemObject item = (ItemObject) collidingObject;
                item.applyItem(this);
            }

            if(collidingObject.isSolid && !collidingObject.isItem) {
                //check if Enemy is on Object
                if(y + height > collidingObject.y && oldY + height <= collidingObject.y && ySpeed >= 0) {

                    y = collidingObject.y - height;
                    ySpeed = 0;
                    onGround = true;
                    jumping = false;
                }

                //check if Enemy is touching bottom side of object
                if(y < collidingObject.y + collidingObject.height && oldY >= collidingObject.y + collidingObject.height && ySpeed <= 0) {

                    y = collidingObject.y + collidingObject.height;
                    ySpeed *= 0.99;
                }

                //Check vertical Collision again after setting Y
                if(!(y + height > collidingObject.y && y < collidingObject.y + collidingObject.height)) {
                    continue;
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
            jumping = true;
            onGround = false;
        }
    }
}
