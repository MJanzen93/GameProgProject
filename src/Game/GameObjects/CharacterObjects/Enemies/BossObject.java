package Game.GameObjects.CharacterObjects.Enemies;

import Game.GameObjects.Bullets.ShootBullet;
import Game.GameObjects.GameObject;

import java.awt.*;
import java.util.Random;

public class BossObject extends EnemyObject {

    public double bulletCooldown = 0;
    private Random rnd;
    private double specialMoveCooldown = 5;
    private int specialMoveTick = 0;

    public BossObject(double startX, double startY, int width, int height) {
        super(startX, startY, width, height);
        rnd = new Random();
        destructible = true;
        hp = 50;
        maxHP = 50;
        dropItem = true;
        COLOR = new Color(108, 22, 22);
    }

    @Override
    public void move(double diffSeconds) {
        super.move(diffSeconds);

        double distanceToPlayer = GameObject.world.player.x - x;

        if(Math.abs(distanceToPlayer) < 500 || maxHP > hp) {
            if(distanceToPlayer > 250 ) {
                xSpeed = 50;
            } else if(distanceToPlayer < -250) {
                xSpeed = -50;
            } else {
                xSpeed = 0;
            }

            if(bulletCooldown > 0) {
                bulletCooldown -= diffSeconds;

            } else {
                bulletCooldown = 0.3;
                shootBullet();
            }

            if(specialMoveCooldown > 0) {
                specialMoveCooldown -= diffSeconds;

            } else {
                specialMove();
            }
        }
    }

    @Override
    public void checkCollision() {
        super.checkCollision();
    }

    public void shootBullet() {
        ShootBullet bullet;

        int randomOffset = rnd.nextInt(101)-50;

        bullet = new ShootBullet(x + width/2, y + height/2, 15, 15);
        bullet.alfa  =  Math.atan2(GameObject.world.player.y+randomOffset - y-height/2, GameObject.world.player.x - x-width/2);
        bullet.speed = 1000;

        bullet.setIsPlayerBullet(false);

        world.bulletObjects.add(bullet);
    }

    public void specialMove() {
        ShootBullet bullet;

        bullet = new ShootBullet(x + width/2, y + height/2, 15, 15);
        bullet.alfa  =  specialMoveTick*0.2;
        specialMoveTick++;
        specialMoveCooldown = 0.1;
        if(specialMoveTick*0.2 > Math.PI*4) {
            specialMoveCooldown = 5;
            specialMoveTick = 0;
        }
        bullet.speed = 1000;
        bullet.setIsPlayerBullet(false);

        GameObject.world.bulletObjects.add(bullet);


    }
}
