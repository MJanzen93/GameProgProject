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
    static World world;
    public boolean hasHP = false;
    public int maxHP = 1;
    public int hp = 1;

    public Color COLOR  = new Color(96,96,255);

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
    static void setWorld(World w) { world = w; }

    public void setColor(Color color) {
        this.COLOR = color;
    }
}
