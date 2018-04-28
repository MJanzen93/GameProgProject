package Game.GameObjects.Bullets;

import Game.GameObjects.GameObject;
import Game.Physics;

import java.awt.*;
import java.util.List;

public abstract class BulletObject extends GameObject {

    public double alfa = 0;
    public double speed = 1000;
    public double range = 2000;
    public boolean isPlayerBullet = false;
    public int damage = 1;


    public BulletObject(double startX, double startY, int width, int height) {
        super(startX, startY, width, height);
        hasHP = true;
        hp = 1;
        COLOR = new Color(144, 57, 0);
        hasCollision = false;
        isSolid = false;
        isFixed = true;
    }

    public void move(double diffSeconds) {
        range-=speed*diffSeconds;
        if(range < 0) {
            hp = 0;
        }
        x+=Math.cos(alfa)*speed*diffSeconds;
        y+=Math.sin(alfa)*speed*diffSeconds;
    }

    @Override
    public void checkCollision() {
        List<Game.GameObjects.GameObject> collidingObjects = Physics.getCollisions(this);
        if(collidingObjects.size() > 0
                && ((isPlayerBullet && !collidingObjects.get(0).isPlayer)
                || (!isPlayerBullet && !collidingObjects.get(0).isEnemy && (collidingObjects.get(0).isPlayer
                || collidingObjects.get(0).isSolid)))
                && !collidingObjects.get(0).isItem) {
            hp = 0;
            if(collidingObjects.get(0).hasHP) {
                collidingObjects.get(0).hp -= damage;
            }

        }
    }

    @Override
    public void draw(Graphics graphics) {
        int x = (int) (this.x - world.worldPartX);
        int y = (int) (this.y - world.worldPartY);

        graphics.setColor(COLOR);
        graphics.fillOval(x, y, width, height);
        graphics.setColor(Color.BLACK);
        graphics.drawOval(x, y, width, height);
    }

    public void setIsPlayerBullet(boolean isPlayerBullet) {
        this.isPlayerBullet = isPlayerBullet;
        if(isPlayerBullet) {
            COLOR = new Color(0, 72, 144);
        }

    }

}
