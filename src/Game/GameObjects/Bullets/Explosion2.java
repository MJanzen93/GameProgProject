package Game.GameObjects.Bullets;

import Game.Animation;
import Game.AudioPlayer;
import Game.GameObjects.GameObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;

public class Explosion2 extends GameObject {

    private int radius;
    private boolean isPlayerExplosion = false;

    private Image[] image;

    int i = 5;
    private Animation animation;

    public Explosion2(double startX, double startY, int radius, boolean isPlayerExplosion) {
        super(startX, startY-15, 0, 0);
        hasCollision = false;
        isSolid = false;
        isFixed = true;
        destructible = false;
        hp = 1;
        this.radius = radius;
        this.isPlayerExplosion = isPlayerExplosion;
        image = new Image[40];

        DecimalFormat format = new DecimalFormat("00");

        try {
            for (int i = 0; i < image.length; i++){
                image[i] = ImageIO.read(new File(".\\src\\Game\\Textures\\ExplosionAnimation2\\frame_" + format.format(i) + "_delay-0.04s.gif"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        animation = new Animation(image, this);
        animation.repeat = false;
        animation.speed = 0.01;
    }

    @Override
    public void move(double diffSeconds) {
        super.move(diffSeconds);
        world.worldPartX+= i;
        world.worldPartY+= i;
        i = -i;

        animation.update(diffSeconds);
    }

    @Override
    public void checkCollision() {
        super.checkCollision();
    }


    @Override
    public void draw(Graphics2D graphics) {
        int x = (int) (this.x - world.worldPartX);
        int y = (int) (this.y - world.worldPartY);

        animation.draw(graphics, x-125, y-125, 240, 160);
    }

    public void explode(){
        for (int i = 0; i < 90; i++) {
            ExplosionBullet bullet = new ExplosionBullet(x, y, 5, 5);
            bullet.alfa = i*2;
            bullet.range = radius-i;
            bullet.speed = 400;
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
        AudioPlayer.shortSound2(".\\src\\Game\\Sounds\\boom-bang.aiff",1.0, world.player.x - x,world.player.y - y);
    }
}
