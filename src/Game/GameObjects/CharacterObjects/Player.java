<<<<<<< HEAD:src/Game/GameObjects/CharacterObjects/Player.java
package Game.GameObjects.CharacterObjects;

import Game.GameObjects.Bullets.ShootBullet;
import Game.GameObjects.GameObject;
=======
package Game.GameObjects;

>>>>>>> Resovled merg confilct:src/Game/GameObjects/Player.java
import Game.GameObjects.Items.*;
import Game.GameObjects.Missile;
import Game.GameObjects.Weapons.WeaponObject;
import Game.InputSystem;
import Game.Physics;

import java.awt.*;
import java.util.List;

<<<<<<< HEAD:src/Game/GameObjects/CharacterObjects/Player.java
public class Player extends CharacterObject {
=======

public class Player extends CharacterObjects{
>>>>>>> Resovled merg confilct:src/Game/GameObjects/Player.java

    public int jumps = 2;

    public WeaponObject[] weapons;
    public WeaponObject currentWeapon;
    
    public double hitAlphaSpeed = 0;
	public boolean hitFromObjectBool = false;

    public int missile = 1;

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
    }

    /**
     * Move the player
     * @param diffSeconds
     */
    @Override
    public void move(double diffSeconds) {

        super.move(diffSeconds);

    }

    @Override
    public void checkCollision() {
        if(isSolid){
            List<GameObject> collidingObjects = Physics.getCollisions(this);

            for(int i = 0; i < collidingObjects.size(); i++) {
                GameObject collidingObject = collidingObjects.get(i);

                //apply Item
                if(collidingObject.isItem){
                    ItemObject item = (ItemObject) collidingObject;
                    item.applyItem(this);
                }

                //if(collidingObject.canCollideWithPlayer)
                if(collidingObject.isSolid && !collidingObject.isItem && !collidingObject.isEnemy) {
                    //check if Game.GameObjects.CharacterObject.Player is on Object

                    if(y + height > collidingObject.y && oldY + height <= collidingObject.y && ySpeed >= 0) {

                        y = collidingObject.y - height;
                        ySpeed = 0;
                        onGround = true;
                        jumping = false;
                    }

                    //check if Game.GameObjects.CharacterObject.Player is touching bottom side of object
                    if(y < collidingObject.y + collidingObject.height && oldY >= collidingObject.y + collidingObject.height && ySpeed <= 0) {

                        y = collidingObject.y + collidingObject.height;
                        ySpeed *= 0.99;
                    }

                    //left side
                    if(x + width > collidingObject.x && oldX + width <= collidingObject.x && xSpeed >= 0) {
                        x = collidingObject.x - width-1;
                        xSpeed = 0;
                        if(ySpeed >= 0){
                            ySpeed *= 0.5;
                            jumping = false;
                        }
                    }

                    //right side
                    if(x < collidingObject.x + collidingObject.width && oldX >= collidingObject.x + collidingObject.width && xSpeed <= 0) {
                        x = collidingObject.x + collidingObject.width;
                        xSpeed = 0;
                        if(ySpeed >= 0){
                            ySpeed *= 0.5;
                            jumping = false;
                        }
                    }
                }


            }

            /*
            if(y + height > 760){
                y = 760-height;
                ySpeed = 0;
                onGround = true;
                jumping = false;
            }
            */

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
        ShootBullet bullet;

        bullet = new ShootBullet(x+width/2, y+height/2, 5, 5);
        bullet.damage = damage;
        bullet.alfa  =  Math.atan2(inputSystem.mouseY+world.worldPartY - y-width/2, inputSystem.mouseX+world.worldPartX - x-height/2);
        bullet.isPlayerBullet = true;
        world.bulletObjects.add(bullet);

        //currentWeapon.shootBullet();
    }

    public void fireMissels(InputSystem inputSystem){
        int x = (int) (inputSystem.mouseX + world.worldPartX);
        int y = (int) (inputSystem.mouseY + world.worldPartY);

        world.gameObjects.add(new Missile(x-200,y-2000));
        world.gameObjects.add(new Missile(x-100,y-2000));
        world.gameObjects.add(new Missile(x+100,y-2000));
        world.gameObjects.add(new Missile(x+200,y-2000));
    }

 // calculate the x speed when hit with an enemy
 	// the problem is when key is released or is pressed it will be either 0 or
 	// pressed XSpeed
 	private double calculateHitAlphaSpeed() {
 		// TODO Auto-generated method stub
     	if(hitFromObjectBool) {
     		if(hitAlphaSpeed >= 1000 && hitAlphaSpeed > 0)
     			hitFromObjectBool = false;
     		return hitAlphaSpeed = hitAlphaSpeed +60;
     	} else {
     		if(hitAlphaSpeed > 1000 || hitAlphaSpeed >= 10 ) {
     	
     			return hitAlphaSpeed = hitAlphaSpeed -10;
     	}
 		return 0 ;
 	}
 	}
}
