package Game.GameObjects.CharacterObjects.Enemies;


import Game.GameObjects.Bullets.ExplodeAbleBullet;
import Game.GameObjects.Bullets.ShootBullet;
import Game.AudioPlayer;
import Game.GameObjects.Bullets.SpiderBullet;
import Game.GameObjects.GameObject;

import java.awt.*;

import java.util.Random;

import Game.GameObjects.GameObject;
import Game.GameObjects.Bullets.ShootBullet;

public class SimpleBoss extends Boss {

    public SimpleBoss(double startX, double startY, int width, int height) {
        super(startX, startY, width, height);
    }

    @Override
    public void move(double diffSeconds) {
        super.move(diffSeconds);

        double distanceToPlayer = GameObject.world.player.x - x;

        if(Math.abs(distanceToPlayer) < 500 || maxHP > hp) {
            if(distanceToPlayer > 250 ) {
                xSpeed = 100;
            } else if(distanceToPlayer < -250) {
                xSpeed = -100;
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
    public void specialMove() {
        ExplodeAbleBullet bullet;

        //ExplosionBullet
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
        //world.bulletObjects.add(bullet);

        //SpiderBullet
        SpiderBullet bullet1 = new SpiderBullet(x + width/2, y + height/2, 5, 5);
        bullet1.alfa  =  specialMoveTick*0.2;
        bullet1.range = 200;
        bullet1.speed = 1000;
        bullet1.isPlayerBullet = false;
        world.gameObjects.add(bullet1);

        AudioPlayer.shortSound2(".\\src\\Game\\Sounds\\shot.wav",0.05, world.player.x - x,world.player.y - y);
    }
}
