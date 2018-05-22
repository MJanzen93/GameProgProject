package Game.GameObjects.CharacterObjects.Enemies;

import Game.AudioPlayer;
import Game.GameObjects.Bullets.Explosion;
import Game.GameObjects.Bullets.ShootBullet;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class SimpleEnemy extends EnemyObject{
    public SimpleEnemy(double startX, double startY, int width, int height) {
        super(startX, startY, 30,30);
        try {
            image = ImageIO.read(new File(".\\src\\Game\\Textures\\enemy.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void move(double diffSeconds) {
        super.move(diffSeconds);

        double distanceToPlayer = world.player.x - x;

        if(Math.abs(distanceToPlayer) < 400 || maxHP > hp) {
            if(distanceToPlayer > 200 ) {
                xSpeed = 100;
            } else if(distanceToPlayer < -200) {
                xSpeed = -100;
            } else {
                xSpeed = 0;
            }

            if(bulletCooldown > 0) {
                bulletCooldown -= diffSeconds;
            } else {
                bulletCooldown = 1;
                shootBullet();
            }
        }
    }

    @Override
    public void checkCollision() {
        super.checkCollision();
    }
}
