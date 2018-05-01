package Game.GameObjects.CharacterObjects.Enemies;

import Game.GameObjects.Bullets.BulletObject;
import Game.GameObjects.Bullets.ShootBullet;
import Game.GameObjects.GameObject;
import Game.GameObjects.Items.*;
import Game.AudioPlayer;
import Game.Physics;

import java.awt.*;
import java.util.List;

public class StealerObject extends EnemyObject {
    public StealerObject(double startX, double startY, int width, int height) {
        super(startX, startY, width, height);
        setColor(new Color(54, 54, 54, 255));
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

            if(collidingObject.isSolid && !collidingObject.isItem && !collidingObject.isEnemy) {
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

        if(y + height > 760){
            y = 760-height;
            ySpeed = 0;
            onGround = true;
            jumping = false;
        }
    }

    public void shootBullet() {
        ShootBullet bullet;

        bullet = new ShootBullet(x + width/2, y + height/2, 5, 5);
        bullet.alfa  =  Math.atan2(world.player.y - y, world.player.x - x);

        bullet.isPlayerBullet = false;
        world.bulletObjects.add(bullet);
        AudioPlayer.shortSound(".\\src\\Game\\Sounds\\laser.wav",0.25);
    }
}
