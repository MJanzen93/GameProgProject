package Game.GameObjects.CharacterObjects.Enemies;

import Game.AudioPlayer;
import Game.GameObjects.Bullets.ShootBullet;
import Game.GameObjects.CharacterObjects.CharacterObject;
import Game.GameObjects.GameObject;
import Game.GameObjects.Items.ItemObject;
import Game.GameObjects.Items.RapidFireItem;
import Game.Physics;

import java.awt.*;
import java.util.List;

/**
 * Enemy
 */

public abstract class EnemyObject extends CharacterObject {

    public int touchDamge = 2;
    public boolean isEnemyTouched = false;

    public EnemyObject(double startX, double startY, int width, int height) {
        super(startX, startY, width, height);
        destructible = true;
        hp = 10;
        maxHP = 10;
        COLOR = new Color(190, 30, 30);
        isSolid = true;
        isEnemy = true;

    }

    @Override
    public void move(double diffSeconds) {

        super.move(diffSeconds);

        if (dropItem) {
            if (hp <= 0) {
                ItemObject item = ItemObject.createRandomItem();
                item.x = x;
                item.y = y;
                world.gameObjects.add(item);
                world.gameObjects.remove(this);
            }
        }
    }

    @Override
    public void checkCollision() {
        List<GameObject> collidingObjects = Physics.getCollisions(this);
        for (int i = 0; i < collidingObjects.size(); i++) {
            Game.GameObjects.GameObject collidingObject = collidingObjects.get(i);

            if (collidingObject.isSolid && !collidingObject.isItem) {
                //check if Enemy is on Object
                if (y + height > collidingObject.y && oldY + height <= collidingObject.y && ySpeed >= 0) {

                    y = collidingObject.y - height;
                    ySpeed = 0;
                    onGround = true;
                    jumping = false;
                }

                //check if Enemy is touching bottom side of object
                if (y < collidingObject.y + collidingObject.height && oldY >= collidingObject.y + collidingObject.height && ySpeed <= 0) {

                    y = collidingObject.y + collidingObject.height;
                    ySpeed *= 0.99;
                }

                //Check vertical Collision again after setting Y
                if(!(y + height > collidingObject.y && y < collidingObject.y + collidingObject.height)) {
                    continue;
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

        if (collidingObjects.size() == 0) {
            jumping = true;
            onGround = false;
        }
    }

    public void shootBullet(){
        ShootBullet bullet;

        bullet = new ShootBullet(x + width/2, y + height/2, 5, 5);
        bullet.alfa  =  Math.atan2(world.player.y + world.player.height/2 - y-height/2, world.player.x + world.player.width/2 - x-width/2);

        bullet.isPlayerBullet = false;

        world.bulletObjects.add(bullet);
        AudioPlayer.shortSound2(".\\src\\Game\\Sounds\\shot.wav",0.05, world.player.x - x,world.player.y - y);
    }

    public void idle() {

    }

    public void goLeft() {

    }

    public void goRight() {

    }

    public void jump() {

    }
}
