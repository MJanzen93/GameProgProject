package Game.GameObjects;

import Game.Physics;
import Game.World;

import java.awt.*;
import java.util.List;


public abstract class GameObject {

    //Position of the object
    public double x, y;
    //Speed of the object
    public double xSpeed = 0;
    public double ySpeed = 0;

    public boolean onGround = false;
    //Object is fixed (no gravity)
    public boolean isFixed = false;
    //Object is solid (Collision)
    public boolean isSolid = false;
    //Object is player
    public boolean isPlayer = false;
    public boolean isEnemy = false;
    //??
    public boolean jumping = false;
    //Dimensions of object
    public int width;
    public int height;
    //??
    public static Physics physics;
    public static World world;
    //HP of object
    public boolean hasHP = false;
    public int maxHP = 1;
    public int hp = 1;
    //Object has collions
    public boolean hasCollision = true;
    //Object is Item
    public boolean isItem = false;

    //Color of game object
    public Color COLOR  = new Color(96,96,255);

    public boolean dropItem = false;

    public GameObject(double startX, double startY, int width, int height) {
        this.x = startX;
        this.y = startY;
        this.width = width;
        this.height = height;
    }

    double oldX, oldY;

    public void move(double diffSeconds) {
        oldX = x;
        oldY = y;

        x+=xSpeed*diffSeconds;
        y+=ySpeed*diffSeconds;

        //todo anpassen
        checkCollision();
    }

    public void draw(Graphics graphics){
        int x = (int) (this.x - world.worldPartX);
        int y = (int) (this.y - world.worldPartY);

        graphics.setColor(COLOR);
        graphics.fillRect(x, y, width, height);
        graphics.setColor(Color.BLACK);
        graphics.drawRect(x, y, width, height);

        if(hasHP && this.maxHP > this.hp){

            int hp = this.hp;
            int maxHP = this.maxHP;
            if(this.maxHP < 30) {
                hp = (hp*10/maxHP)*3;
                maxHP = 30;
            }
            graphics.setColor(new Color(201, 0, 0));
            graphics.fillRect((int)x+this.width/2-maxHP/2, (int)y-30, hp, 10);
            graphics.setColor(new Color(0, 0, 0));
            graphics.drawRect((int)x+this.width/2-maxHP/2, (int)y-30, maxHP, 10);
        }
    }

    public void checkCollision(){
        if(isSolid){  //-->collision with enemies
            List<GameObject> collidingObjects = physics.getCollisions(this);

            for(int i = 0; i < collidingObjects.size(); i++) {
                GameObject collidingObject = collidingObjects.get(i);

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
    }

    public static void setPhysics(Physics ph) {
        physics = ph;
    }
    public static void setWorld(World w) { world = w; }

    public void setColor(Color color) {
        this.COLOR = color;
    }
}
