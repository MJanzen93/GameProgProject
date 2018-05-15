package Game.GameObjects.Bullets;

import Game.AudioPlayer;
import Game.GameObjects.GameObject;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

public class Explosion extends GameObject {

    private int radius = 200;
    private boolean isPlayerExplosion = false;

    private Image[] image;
    private int imageC = 0;
    private double delay = 0.04;

    public Explosion(double startX, double startY, int radius, boolean isPlayerExplosion) {
        super(startX, startY, 0, 0);
        hasCollision = false;
        isSolid = false;
        isFixed = true;
        destructible = false;
        hp = 1;
        this.radius = radius;
        this.isPlayerExplosion = isPlayerExplosion;
        image = new Image[7];

        try {
            image[0] = ImageIO.read(new File(".\\src\\Game\\Textures\\ExplosionAnimation\\frame_0_delay-0.1s.gif"));
            image[1] = ImageIO.read(new File(".\\src\\Game\\Textures\\ExplosionAnimation\\frame_1_delay-0.1s.gif"));
            image[2] = ImageIO.read(new File(".\\src\\Game\\Textures\\ExplosionAnimation\\frame_2_delay-0.1s.gif"));
            image[3] = ImageIO.read(new File(".\\src\\Game\\Textures\\ExplosionAnimation\\frame_3_delay-0.1s.gif"));
            image[4] = ImageIO.read(new File(".\\src\\Game\\Textures\\ExplosionAnimation\\frame_4_delay-0.1s.gif"));
            image[5] = ImageIO.read(new File(".\\src\\Game\\Textures\\ExplosionAnimation\\frame_5_delay-0.1s.gif"));
            image[6] = ImageIO.read(new File(".\\src\\Game\\Textures\\ExplosionAnimation\\frame_6_delay-0.1s.gif"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void move(double diffSeconds) {
        super.move(diffSeconds);
        delay -= diffSeconds;
    }

    @Override
    public void checkCollision() {
        super.checkCollision();
    }


    @Override
    public void draw(Graphics graphics) {
        int x = (int) (this.x - world.worldPartX);
        int y = (int) (this.y - world.worldPartY);

        if(delay <= 0 && imageC < 7){
            delay = 0.04;
            imageC++;
        }else if(imageC >= 7){
            hp = 0;
            return;
        }

        if(imageC < 7){
            graphics.drawImage(image[imageC], x-120, y-80, 240, 160, null);
        }else{
            hp = 0;
        }

    }

    public void explode(){
        for (int i = 0; i < 180; i++) {
            ExplosionBullet bullet = new ExplosionBullet(x, y, 5, 5);
            bullet.alfa = i*2;
            bullet.range = radius-i;
            bullet.speed = 800;
            bullet.setColor(Color.gray);
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
        AudioPlayer.shortSound(".\\src\\Game\\Sounds\\boom-bang.aiff",1.0);
    }
}
