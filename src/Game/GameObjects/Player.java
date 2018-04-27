package Game.GameObjects;


import Game.GameObjects.Items.*;
import Game.GameObjects.Weapons.WeaponObject;
import Game.InputSystem;

import java.awt.*;
import java.util.List;
import java.util.Random;

public class Player extends CharacterObjects{

    public int jumps = 2;

    public WeaponObject[] weapons;
    public WeaponObject currentWeapon;

    public boolean hasShield = false;
    public int shieldHp = 0;

    public Player(double startX, double startY) {
        super(startX, startY, 30, 30);
        hasHP = true;
        hp = 10;
        maxHP = 10;
        isPlayer = true;
        isSolid = true;
        //COLOR = new Color(0, 217, 241);
        weapons = new WeaponObject[2];
        currentWeapon = null;
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

        checkCollision(oldX, oldY);

        if(y + height > 760){
            y = 760-height;
            ySpeed = 0;
            onGround = true;
            jumping = false;
        }
    }

    @Override
    public void draw(Graphics graphics) {
        super.draw(graphics);
        int x = (int) (this.x - world.worldPartX);
        int y = (int) (this.y - world.worldPartY);


        graphics.setColor(COLOR);
        graphics.fillRect(x, y, width, height);
        if(hasShield){
            graphics.setColor(Color.GRAY);
            graphics.fillRect(x, y+shieldHp, width, height-shieldHp);
        }
        graphics.setColor(Color.BLACK);
        graphics.drawRect(x, y, width, height);
    }

    /**
     * Check collision for player
     * @param oldX
     * @param oldY
     */
    public void checkCollision(double oldX, double oldY) {

        if(isSolid){
            List<GameObject> collidingObjects = physics.getCollisions(this);

            for(int i = 0; i < collidingObjects.size(); i++) {
                GameObject collidingObject = collidingObjects.get(i);

                //apply Item
                if(collidingObject.isItem){
                    ItemObject item = (ItemObject) collidingObject;
                    item.applyItem(this);
                }

                if(collidingObject.isSolid && !collidingObject.isItem && !collidingObject.isEnemy) {
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

            if(collidingObjects.size() == 0) {
                jumping = true;
                onGround = false;
            }
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
        world.bulletObjects.add(bullet);

        //currentWeapon.shootBullet();
    }

}
