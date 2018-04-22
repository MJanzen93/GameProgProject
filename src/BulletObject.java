import java.util.List;

public class BulletObject extends GameObject {

    public double alfa = 0;
    public double speed = 1000;

    public BulletObject(double startX, double startY, int width, int height) {
        super(startX, startY, width, height);
        hp = 1;
    }

    public void move(double diffSeconds) {

        List<GameObject> collidingObjects = physics.getCollisions(this, diffSeconds);

        if(collidingObjects.size() > 0 && !collidingObjects.get(0).isPlayer) {
            hp = 0;
            if(collidingObjects.get(0).hasHP) {
                collidingObjects.get(0).hp -= 1;
                System.out.println("test");
            }

        }

        x+=Math.cos(alfa)*speed*diffSeconds;
        y+=Math.sin(alfa)*speed*diffSeconds;
    }
}
