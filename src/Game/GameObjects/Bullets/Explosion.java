package Game.GameObjects.Bullets;

import Game.GameObjects.GameObject;

import java.awt.*;

public class Explosion extends GameObject {

    private int radius = 200;
    private boolean isPlayerExplosion = false;

    public Explosion(double startX, double startY, int radius, boolean isPlayerExplosion) {
        super(startX, startY, 0, 0);
        hasCollision = false;
        isSolid = false;
        isFixed = true;
        destructible = false;
        hp = 0;
        this.radius = radius;
        this.isPlayerExplosion = isPlayerExplosion;
    }

    @Override
    public void move(double diffSeconds) {
        super.move(diffSeconds);
    }

    @Override
    public void checkCollision() {
        super.checkCollision();
    }

    @Override
    public void draw(Graphics graphics) {
    }

    public void explode(){
        for (int i = 0; i < 180; i++) {
            ExplosionBullet bullet = new ExplosionBullet(x, y, 5, 5);
            bullet.alfa = i*2;
            bullet.range = radius-i;
            bullet.speed = 800;
            bullet.setIsPlayerBullet(isPlayerExplosion);
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
