package Game.GameObjects.CharacterObjects.Enemies;

import Game.GameObjects.CharacterObjects.CharacterObject;
import Game.GameObjects.GameObject;
import Game.GameObjects.Items.ItemObject;
import Game.Physics;

import java.awt.*;
import java.util.List;

/**
 * Enemy
 */

public abstract class EnemyObject extends CharacterObjects {
	
	public int touchDamge = 2;
	public boolean isEnemyTouched = false;

    public EnemyObject(double startX, double startY, int width, int height) {
        super(startX, startY, width, height);
        destructible = true;
        hp = 10;
        maxHP = 10;
        COLOR = new Color(190, 30, 30);
        isSolid = true;
        isEnemy= true;
        
    }

    @Override
    public void move(double diffSeconds) {

        super.move(diffSeconds);

        if(dropItem){
            if(hp <= 0){
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
        for(int i = 0; i < collidingObjects.size(); i++) {
            Game.GameObjects.GameObject collidingObject = collidingObjects.get(i);

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

    }

    public void idle(){

    }

    public void goLeft(){

    }

    public void goRight(){

    }

    public void jump(){

    }
}
