package Game.GameObjects.CharacterObjects.Enemies;

import Game.GameObjects.Bullets.BulletObject;
import Game.GameObjects.Bullets.ShootBullet;

public class SimpleEnemyObject extends EnemyObject{
    public SimpleEnemyObject(double startX, double startY, int width, int height) {
        super(startX, startY, width, height);
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

    public void shootBullet() {
        ShootBullet bullet;

        bullet = new ShootBullet(x + width/2, y + height/2, 5, 5);
        bullet.alfa  =  Math.atan2(world.player.y - y, world.player.x - x);

        bullet.setIsPlayerBullet(false);

        world.bulletObjects.add(bullet);
    }
}
