package Game.GameObjects.Enemies;

import Game.GameObjects.BulletObject;
import Game.GameObjects.GameObject;

import java.awt.*;
import java.util.List;
import java.util.Random;

public class BossObject extends EnemyObject {

    public double bulletCooldown = 0;
    private Random rnd;
    private double specialMoveCooldown = 5;
    private int specialMoveTick = 0;

    public BossObject(double startX, double startY, int width, int height) {
        super(startX, startY, width, height);
        rnd = new Random();
        hasHP = true;
        hp = 50;
        maxHP = 50;
        dropItem = true;
        COLOR = new Color(108, 22, 22);
    }

    @Override
    public void move(double diffSeconds) {
        super.move(diffSeconds);
        double oldX = x;
        double oldY = y;

        double distanceToPlayer = GameObject.world.player.x - x;

        if(Math.abs(distanceToPlayer) < 500 || maxHP > hp) {
            if(distanceToPlayer > 250 ) {
                xSpeed = 50;
            } else if(distanceToPlayer < -250) {
                xSpeed = -50;
            } else {
                xSpeed = 0;
            }

            if(bulletCooldown > 0) {
                bulletCooldown -= diffSeconds;

            } else {
                bulletCooldown = 0.3;
                shootBullet();
            }

            if(specialMoveCooldown > 0) {
                specialMoveCooldown -= diffSeconds;

            } else {
                specialMove();
            }
        }



        x+=xSpeed*diffSeconds;
        y+=ySpeed*diffSeconds;




        List<GameObject> collidingObjects = GameObject.physics.getCollisions(this);

        for(int i = 0; i < collidingObjects.size(); i++) {
            Game.GameObjects.GameObject collidingObject = collidingObjects.get(i);
            if(collidingObject.isFixed && collidingObject.isSolid) {
                //check if Boss is on Object
                if(y + height > collidingObject.y && oldY + height <= collidingObject.y && ySpeed >= 0) {

                    y = collidingObject.y - height;
                    ySpeed = 0;
                    onGround = true;
                    jumping = false;
                }

                //check if Boss is touching bottom side of object
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
        BulletObject bullet;

        int randomOffset = rnd.nextInt(101)-50;

        bullet = new Game.GameObjects.BulletObject(x + width/2, y + height/2, 15, 15);
        bullet.alfa  =  Math.atan2(GameObject.world.player.y+randomOffset - y-height/2, GameObject.world.player.x - x-width/2);
        bullet.speed = 1000;

        bullet.setIsPlayerBullet(false);

        world.gameObjects.add(bullet);
    }

    public void specialMove() {
        Game.GameObjects.BulletObject bullet;

        bullet = new Game.GameObjects.BulletObject(x + width/2, y + height/2, 15, 15);
        bullet.alfa  =  specialMoveTick*0.2;
        specialMoveTick++;
        specialMoveCooldown = 0.1;
        if(specialMoveTick*0.2 > Math.PI*4) {
            specialMoveCooldown = 5;
            specialMoveTick = 0;
        }
        bullet.speed = 1000;

        bullet.setIsPlayerBullet(false);

        GameObject.world.gameObjects.add(bullet);


    }
}
