package Game.GameObjects;

import java.awt.*;

public class Explosion extends GameObject {

    private int radius = 200;

    public Explosion(double startX, double startY, int radius) {
        super(startX, startY, 0, 0);
        hasCollision = false;
        isSolid = false;
        isFixed = false;
        this.radius = radius;
    }

    @Override
    public void move(double diffSeconds) {
    }

    public void explode(){
        hp = 0;
        for (int i = 0; i < 180; i++) {
            ExplosionBullet bullet = new ExplosionBullet(x, y, 5, 5);
            bullet.alfa = i*2;
            bullet.range = radius;
            bullet.speed = 800;
            bullet.setIsPlayerBullet(true);
            world.bulletObjects.add(bullet);
        }
        /*
        for (int i = 0; i < 90; i++) {
            ExplosionBullet bullet = new ExplosionBullet(x, y, 5, 5);
            bullet.alfa = i*4;
            bullet.range = 100;
            bullet.speed = 400;
            bullet.setIsPlayerBullet(true);
            world.bulletObjects.add(bullet);
        }
        for (int i = 0; i < 45; i++) {
            ExplosionBullet bullet = new ExplosionBullet(x, y, 5, 5);
            bullet.alfa = i*8;
            bullet.range = 50;
            bullet.speed = 200;
            bullet.setIsPlayerBullet(true);
            world.bulletObjects.add(bullet);
        }
        */
    }
}
