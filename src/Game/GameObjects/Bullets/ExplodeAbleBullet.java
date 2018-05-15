package Game.GameObjects.Bullets;

import Game.Physics;

public class ExplodeAbleBullet extends BulletObject {
    public ExplodeAbleBullet(double startX, double startY, int width, int height) {
        super(startX, startY, width, height);
        isFixed = false;

        range = 3000;
    }

    @Override
    public void move(double diffSeconds) {
        super.move(diffSeconds);
        if(hp <= 0){
            Explosion explosion = new Explosion(x,y, 200, isPlayerBullet);
            world.fixedObjects.add(explosion);
            explosion.explode();
        }
    }
}
