package Game.GameObjects;

public class Explosion extends GameObject {
    public Explosion(double startX, double startY, int width, int height) {
        super(startX, startY, width, height);
    }

    @Override
    public void move(double diffSeconds) {
    }

    public void explode(){
        for (int i = 0; i < 180; i++) {
            ExplosionBullet bullet = new ExplosionBullet(x, y, 5, 5);
            bullet.alfa = i*2;
            bullet.range = 300;
            bullet.speed = 800;
            bullet.setIsPlayerBullet(true);
            world.bulletObjects.add(bullet);
        }
        for (int i = 0; i < 90; i++) {
            ExplosionBullet bullet = new ExplosionBullet(x, y, 5, 5);
            bullet.alfa = i*4;
            bullet.range = 150;
            bullet.speed = 250;
            bullet.setIsPlayerBullet(true);
            world.bulletObjects.add(bullet);
        }
        for (int i = 0; i < 45; i++) {
            ExplosionBullet bullet = new ExplosionBullet(x, y, 5, 5);
            bullet.alfa = i*8;
            bullet.range = 100;
            bullet.speed = 125;
            bullet.setIsPlayerBullet(true);
            world.bulletObjects.add(bullet);
        }
        hp = 0;
    }
}
