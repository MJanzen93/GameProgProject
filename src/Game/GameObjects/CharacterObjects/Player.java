
package Game.GameObjects.CharacterObjects;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import Game.AudioPlayer;
import Game.InputSystem;
import Game.Physics;
import Game.GameObjects.Bomber;
import Game.GameObjects.GameObject;
import Game.GameObjects.SWATTeamMate;
import Game.GameObjects.Bullets.ShootBullet;
import Game.GameObjects.CharacterObjects.Enemies.Speedy;
import Game.GameObjects.Items.ItemObject;
import Game.GameObjects.Weapons.WeaponObject;


public class Player extends CharacterObject {


    public int jumps = 2;

    public WeaponObject[] weapons;
    public WeaponObject currentWeapon;

    public double hitSpeed = 0;
    public double hitSide;
    public boolean hitFromObjectBool = false;


    public int coolDownMissile = 200;
    public boolean missileReady = false;
    
    public boolean mate = false;

    public double oldWidth = 0;
    public double oldHeight = 0;


    public Player(double startX, double startY) {
        super(startX, startY, 30, 30);
        destructible = true;
        hp = 10;
        maxHP = 10;
        isPlayer = true;
        isSolid = true;
        //COLOR = new Color(0, 217, 241);
        weapons = new WeaponObject[2];
        currentWeapon = null;
        hasShield = false;
        try {
            image = ImageIO.read(new File(".\\src\\Game\\Textures\\player.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Move the player
     *
     * @param diffSeconds
     */
    @Override
    public void move(double diffSeconds) {
        Physics.applyGravity(this, diffSeconds);
        saveOldPosition();

        x += xSpeed * diffSeconds + (calculateHitSpeed(diffSeconds) * hitSide);
        y += ySpeed * diffSeconds;

        if (hasShield && shieldDuration <= 0) {
            hasShield = false;
            destructible = true;
            shieldDuration = 500;
        }
        if (hasShield) {
            shieldDuration -= diffSeconds;
        }

        if (coolDownMissile <= 0) {
            missileReady = true;
        } else
            coolDownMissile -= diffSeconds;

    }


	@Override
    public void checkCollision() {
        if (isSolid) {
            List<GameObject> collidingObjects = Physics.getCollisions(this);

            for (int i = 0; i < collidingObjects.size(); i++) {
                GameObject collidingObject = collidingObjects.get(i);

                //apply Item
                if (collidingObject.isItem) {
                    ItemObject item = (ItemObject) collidingObject;
                    item.applyItem(this);
                }


                if (collidingObject.isSolid && !collidingObject.isItem && !collidingObject.isEnemy && !(collidingObject instanceof SWATTeamMate) || collidingObject instanceof Speedy) {

                    //check if Game.GameObjects.CharacterObject.Player is on Object

                    if (y + height > collidingObject.y && oldY + height <= collidingObject.y && ySpeed >= 0) {

                        //safety check whether object is not just on left/right side of wall
                        if (!(x < collidingObject.x + collidingObject.width && x > collidingObject.x || x + width > collidingObject.x && x + width < collidingObject.x + collidingObject.width)) {
                            continue;
                        }

                        y = collidingObject.y - height;
                        ySpeed = 0;
                        onGround = true;
                        jumping = false;
                    }

                    //check if Game.GameObjects.CharacterObject.Player is touching bottom side of object
                    if (y < collidingObject.y + collidingObject.height && oldY >= collidingObject.y + collidingObject.height && ySpeed <= 0) {

                        y = collidingObject.y + collidingObject.height;
                        ySpeed *= 0.99;
                    }

                    //Check vertical Collision again after setting Y
                    if(!(y + height > collidingObject.y && y < collidingObject.y + collidingObject.height)) {
                        continue;
                    }

                    //left side
                    if (x + width > collidingObject.x && oldX + oldWidth <= collidingObject.x && xSpeed >= 0 ) {
                        x = collidingObject.x - width - 1;
                        xSpeed = 0;
                        if (ySpeed >= 0) {
                            ySpeed *= 0.5;
                            jumping = false;
                        }
                    }

                    //right side
                    if (x < collidingObject.x + collidingObject.width && oldX >= collidingObject.x + collidingObject.width && xSpeed <= 0) {
                        x = collidingObject.x + collidingObject.width;
                        xSpeed = 0;
                        if (ySpeed >= 0) {
                            ySpeed *= 0.5;
                            jumping = false;
                        }
                    }
                }
            }

        }
    }

    public void saveOldPosition() {
        this.oldX = x;
        this.oldY = y;
        this.oldWidth = width;
        this.oldHeight = height;
    }


    /**
     * Player goes left
     */
    public void goLeft() {
        xSpeed = -xForce;
    }

    /**
     * Player goes right
     */
    public void goRight() {
        xSpeed = xForce;
    }

    /**
     * Player jumps
     */
    public void jump() {
        jumping = true;
        onGround = false;
        ySpeed = -jumpForce;
        AudioPlayer.shortSound(".\\src\\Game\\Sounds\\jump.wav", 0.99);
    }

    public void fly(){
        ySpeed = -500;
    }

    /**
     * Player stops
     */
    public void stop() {
        xSpeed = 0;
    }

    /**
     * Player shoots
     */
    public void shootBullet(InputSystem inputSystem) {
        ShootBullet bullet;

        bullet = new ShootBullet(x + width / 2, y + height / 2, 5, 5);
        bullet.damage = damage;
        bullet.alfa = Math.atan2(inputSystem.mouseY + world.worldPartY - y - width / 2, inputSystem.mouseX + world.worldPartX - x - height / 2);
        bullet.isPlayerBullet = true;
        world.bulletObjects.add(bullet);
        AudioPlayer.shortSound(".\\src\\Game\\Sounds\\shot.wav", 0.05);
        //currentWeapon.shootBullet();
    }

    public void fireMissels(InputSystem inputSystem) {
        if (!missileReady) {
            return;
        }
        coolDownMissile = 200;
        missileReady = false;

        //add new Bomber plane
        Bomber bomber = new Bomber(world.worldPartX - 200, world.worldPartY);
        bomber.setPosition(inputSystem);
        world.fixedObjects.add(bomber);

        missile--;
    }

    private double calculateHitSpeed(double diffSeconds) {
        if (hitFromObjectBool) {
            if (hitSpeed >= (2000 * diffSeconds))
                hitFromObjectBool = false;
            return hitSpeed = hitSpeed + (300 * diffSeconds);
        } else {
            if (hitSpeed > (2000 * diffSeconds) || hitSpeed > 0) {
                return hitSpeed = hitSpeed - (20 * diffSeconds);

            }
            return 0;
        }
    }
}
