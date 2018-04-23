package Game.GameObjects;


import java.awt.*;
import java.util.List;

public class Player extends GameObject {

    public double bulletCooldown = 0.3;

    public Player(double startX, double startY) {

        super(startX, startY, 30, 30);
        hasHP = true;
        hp = 10;
        maxHP = 10;
        isPlayer = true;
        //COLOR = new Color(0, 217, 241);
    }

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

    public void checkCollision(double oldX, double oldY) {
        List<GameObject> collidingObjects = physics.getCollisions(this);

        for(int i = 0; i < collidingObjects.size(); i++) {
            GameObject collidingObject = collidingObjects.get(i);

            if(collidingObject.isBuff) {
                BuffObject buff = (BuffObject) collidingObject;
                collidingObject.hp = 0;
                buff.buffPlayer();
            }

            if(collidingObject.isFixed && collidingObject.isSolid) {
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
}
