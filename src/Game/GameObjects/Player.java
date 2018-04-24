package Game.GameObjects;


import Game.GameObjects.Items.*;
import Game.InputSystem;

import java.util.List;

public class Player extends MovableObject{

    public double bulletCooldown = 0.3;

    public int jumps = 2;

    private int damage = 1;

    public Player(double startX, double startY) {
        super(startX, startY, 30, 30);
        hasHP = true;
        hp = 10;
        maxHP = 10;
        isPlayer = true;
        isSolid = true;
        //COLOR = new Color(0, 217, 241);
    }

    /**
     * Move the player
     * @param diffSeconds
     */
    @Override
    public void move(double diffSeconds) {
        double oldX = x;
        double oldY = y;

        x+=xSpeed*diffSeconds;
        y+=ySpeed*diffSeconds;

        if(hp <= 0){
            world.gameObjects.remove(this);
        }

        checkCollision(oldX, oldY);



        if(y + height > 760){
            y = 760-height;
            ySpeed = 0;
            onGround = true;
            jumping = false;
        }
    }

    /**
     * Check collision for player
     * @param oldX
     * @param oldY
     */
    public void checkCollision(double oldX, double oldY) {
        List<GameObject> collidingObjects = physics.getCollisions(this);

        for(int i = 0; i < collidingObjects.size(); i++) {
            GameObject collidingObject = collidingObjects.get(i);

            if(collidingObject.isItem){
                if(collidingObject instanceof HealthItem){
                    HealthItem item = (HealthItem) collidingObject;
                    item.setHealth(this);
                    collidingObject.hp = 0;
                }else if(collidingObject instanceof RapidFireItem){
                    RapidFireItem item = (RapidFireItem) collidingObject;
                    collidingObject.hp = 0;
                    item.setRapidFire(this);
                }else if(collidingObject instanceof DoubleDamageItem){
                    damage = 10;
                    collidingObject.hp = 0;
                }else if(collidingObject instanceof SWATItem){

                }else if(collidingObject instanceof SpeedUpItem){
                    xForce = 500;
                    collidingObject.hp = 0;
                }else if(collidingObject instanceof JumpItem){
                    jumpForce = 1000;
                    collidingObject.hp = 0;
                }
            }

            if(collidingObject.isSolid) {
                //check if Game.GameObjects.Player is on Object
                if(y + height > collidingObject.y && oldY + height <= collidingObject.y && ySpeed >= 0) {

                    y = collidingObject.y - height;
                    ySpeed = 0;
                    onGround = true;
                    jumping = false;
                }

                //check if Game.GameObjects.Player is touching bottom side of object
                if(y < collidingObject.y + collidingObject.height && oldY >= collidingObject.y + collidingObject.height && ySpeed <= 0) {

                    y = collidingObject.y + collidingObject.height;
                    ySpeed *= 0.99;
                }

                //left side
                if(x + width > collidingObject.x && oldX + width <= collidingObject.x && xSpeed >= 0) {
                    x = collidingObject.x - width-1;
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

    /**
     * Player goes left
     */
    public void goLeft(){
        xSpeed = - xForce;
    }

    /**
     * Player goes right
     */
    public void goRight(){
        xSpeed = xForce;
    }

    /**
     * Player jumps
     */
    public void jump(){
        jumping = true;
        onGround = false;
        ySpeed = -jumpForce;
    }

    /**
     * Player stops
     */
    public void stop(){
        xSpeed = 0;
    }

    /**
     * Player shoots
     */
    public void shootBullet(InputSystem inputSystem){
        BulletObject bullet;

        bullet = new BulletObject(x+width/2, y+height/2, 5, 5);
        bullet.damage = damage;
        bullet.alfa  =  Math.atan2(inputSystem.mouseY+world.worldPartY - y-width/2, inputSystem.mouseX+world.worldPartX - x-height/2);
        bullet.setIsPlayerBullet(true);

        world.gameObjects.add(bullet);
    }

}
