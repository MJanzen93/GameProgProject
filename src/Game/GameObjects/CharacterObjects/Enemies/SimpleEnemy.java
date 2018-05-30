package Game.GameObjects.CharacterObjects.Enemies;

import Game.GameObjects.BackgroundObjects.BrokenPart;
import Game.GameObjects.Bullets.Explosion;
import Game.GameObjects.Bullets.ShootBullet;
import Game.GameObjects.Items.HealthItem;
import Game.GameObjects.Items.ItemObject;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class SimpleEnemy extends EnemyObject{
    public SimpleEnemy(double startX, double startY, int width, int height) {
        super(startX, startY, 35,35);
        try {
            image = ImageIO.read(new File(".\\src\\Game\\Textures\\enemy.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Random random = new Random();
        int r = random.nextInt(4);
        if(r == 3){
            dropItem = true;
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
