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
    //Object is moving
    public boolean isMoving = true;
    //??
    public boolean onGround = false;
    //Object is fixed (no gravity)
    public boolean isFixed = false;
    //Object is solid (Collision)
    public boolean isSolid = false;
    //Object is player
    public boolean isPlayer = false;
    //Object is Buff
    public boolean isBuff = false;
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

    public GameObject(double startX, double startY, int width, int height) {
        this.x = startX;
        this.y = startY;
        this.width = width;
        this.height = height;
    }

    public void move(double diffSeconds) {
    }

    public static void setPhysics(Physics ph) {
        physics = ph;
    }
    public static void setWorld(World w) { world = w; }

    public void setColor(Color color) {
        this.COLOR = color;
    }
}
