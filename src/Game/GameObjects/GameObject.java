package Game.GameObjects;

import Game.Physics;
import Game.World;

import java.awt.*;


public abstract class GameObject {

    public double x, y;
    public double xSpeed = 0;
    public double ySpeed = 0;
    public boolean isMoving = true;
    public boolean onGround = false;
    public boolean isFixed = false;
    public boolean isSolid = false;
    public boolean isPlayer = false;
    public boolean isBuff = false;
    public boolean jumping = false;
    public int width;
    public int height;
    public static Physics physics;
    public static World world;
    public boolean hasHP = false;
    public int maxHP = 1;
    public int hp = 1;
    public boolean hasCollision = true;

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
