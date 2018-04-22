import java.awt.*;

abstract class GameObject {

    double x, y;
    double xSpeed = 0;
    double ySpeed = 0;
    boolean isMoving = true;
    boolean onGround = false;
    boolean isFixed = false;
    boolean isSolid = false;
    boolean isPlayer = false;
    public boolean jumping = false;
    int width;
    int height;
    static Physics physics;
    public boolean hasHP = false;
    public int hp = 1;

    final Color COLOR  = new Color(96,96,255);

    public GameObject(double startX, double startY, int width, int height) {
        this.x = startX;
        this.y = startY;
        this.width = width;
        this.height = height;
    }

    public void move(double diffSeconds) {

    }

    static void setPhysics(Physics ph) {
        physics = ph;
    }
}
