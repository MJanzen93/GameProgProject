package Game.GameObjects.Enemies;

import Game.GameObjects.GameObject;
import Game.GameObjects.Items.*;

import java.awt.*;
import java.util.List;

public class StealerObject extends EnemyObject {
    public StealerObject(double startX, double startY, int width, int height) {
        super(startX, startY, width, height);
        setColor(Color.BLACK);
    }

    @Override
    public void move(double diffSeconds) {
        double oldX = x;
        double oldY = y;

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
                bulletCooldown = 1;
                shootBullet();
            }
        }



        x+=xSpeed*diffSeconds;
        y+=ySpeed*diffSeconds;




        List<GameObject> collidingObjects = physics.getCollisions(this);

        for(int i = 0; i < collidingObjects.size(); i++) {
            Game.GameObjects.GameObject collidingObject = collidingObjects.get(i);

            //todo change to item.applyItem(this);
            if(collidingObject.isItem){
                if(collidingObject instanceof HealthItem){
                    HealthItem item = (HealthItem) collidingObject;
                    hp = maxHP;
                    collidingObject.hp = 0;
                }else if(collidingObject instanceof RapidFireItem){
                    RapidFireItem item = (RapidFireItem) collidingObject;
                    collidingObject.hp = 0;
                    bulletCooldown = 0.1;
                }else if(collidingObject instanceof DoubleDamageItem){

                }else if(collidingObject instanceof SWATItem){

                }else if(collidingObject instanceof SpeedUpItem){

                }else if(collidingObject instanceof JumpItem){

                }
            }

            if(collidingObject.isSolid) {
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
        Game.GameObjects.BulletObject bullet;

        bullet = new Game.GameObjects.BulletObject(x + width/2, y + height/2, 5, 5);
        bullet.alfa  =  Math.atan2(world.player.y - y, world.player.x - x);

        bullet.setIsPlayerBullet(false);

        world.gameObjects.add(bullet);
    }
}
