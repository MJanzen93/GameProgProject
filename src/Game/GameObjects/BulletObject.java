package Game.GameObjects;

import java.awt.*;
import java.util.List;

public class BulletObject extends Game.GameObjects.GameObject {

    public double alfa = 0;
    public double speed = 1000;
    private double lifetime = 3;
    public boolean isPlayerBullet = false;

    public BulletObject(double startX, double startY, int width, int height) {
        super(startX, startY, width, height);
        hp = 1;

        COLOR = new Color(144, 57, 0);
        hasCollision = false;
    }

    public void move(double diffSeconds) {
        lifetime -= diffSeconds;
        if(lifetime < 0) {
            hp = 0;
        }

        List<Game.GameObjects.GameObject> collidingObjects = physics.getCollisions(this);

        if(collidingObjects.size() > 0 && ((isPlayerBullet && !collidingObjects.get(0).isPlayer) || (!isPlayerBullet && collidingObjects.get(0).isPlayer || collidingObjects.get(0).isFixed))) {
            hp = 0;
            if(collidingObjects.get(0).hasHP) {
                collidingObjects.get(0).hp -= 1;
            }

        }

        x+=Math.cos(alfa)*speed*diffSeconds;
        y+=Math.sin(alfa)*speed*diffSeconds;
    }

    public void setIsPlayerBullet(boolean isPlayerBullet) {
        this.isPlayerBullet = isPlayerBullet;
        if(isPlayerBullet) {
            COLOR = new Color(0, 72, 144);
        }

    }
}
