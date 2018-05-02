package Game.GameObjects.CharacterObjects.Enemies;


import Game.GameObjects.Bullets.ExplodeAbleBullet;
import Game.GameObjects.Bullets.ShootBullet;
import Game.AudioPlayer;
import Game.GameObjects.GameObject;

import java.awt.*;

import java.util.Random;

import Game.GameObjects.GameObject;
import Game.GameObjects.Bullets.ShootBullet;

public class Boss1 extends EnemyObject {

    public double bulletCooldown = 0;
    private Random rnd;
    private double specialMoveCooldown = 5;
    private int specialMoveTick = 0;

    public Boss1(double startX, double startY, int width, int height) {
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
                xSpeed = 500;
            } else if(distanceToPlayer < -250) {
                xSpeed = -500;
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

        bullet.isPlayerBullet = false;

        world.bulletObjects.add(bullet);
        AudioPlayer.shortSound(".\\src\\Game\\Sounds\\shot.wav",0.05);
    }

    public void specialMove() {
        ExplodeAbleBullet bullet;

        bullet = new ExplodeAbleBullet(x + width/2, y + height/2, 15, 15);
        bullet.alfa  =  specialMoveTick*0.2;
        bullet.range = 200;
        specialMoveTick++;
        specialMoveCooldown = 0.1;
        if(specialMoveTick*0.2 > Math.PI*4) {
            specialMoveCooldown = 5;
            specialMoveTick = 0;
        }
        bullet.speed = 1000;
        bullet.isPlayerBullet = false;

        world.bulletObjects.add(bullet);
        AudioPlayer.shortSound(".\\src\\Game\\Sounds\\shot.wav",0.05);


    }
}
