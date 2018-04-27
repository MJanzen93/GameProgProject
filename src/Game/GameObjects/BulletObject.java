package Game.GameObjects;

import Game.GameObjects.Enemies.EnemyObject;

import java.awt.*;
import java.util.List;

public class BulletObject extends GameObject {

    public double alfa = 0;
    public double speed = 1000;
    private double range = 20000;
    public boolean isPlayerBullet = false;
    public int damage = 1;


    public BulletObject(double startX, double startY, int width, int height) {
        super(startX, startY, width, height);
        hasHP = true;
        hp = 1;

        COLOR = new Color(144, 57, 0);
        hasCollision = true;
        isFixed = true;
    }

    public void move(double diffSeconds) {
        range--;
        if(range < 0) {
            hp = 0;
        }

        List<Game.GameObjects.GameObject> collidingObjects = physics.getCollisions(this);
        if(collidingObjects.size() > 0
                && ((isPlayerBullet && !collidingObjects.get(0).isPlayer) || (!isPlayerBullet && !collidingObjects.get(0).isEnemy
                && (collidingObjects.get(0).isPlayer || collidingObjects.get(0).isSolid)))
                && !collidingObjects.get(0).isItem ) {
            hp = 0;
            if(collidingObjects.get(0).hasHP) {
                collidingObjects.get(0).hp -= damage;
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
