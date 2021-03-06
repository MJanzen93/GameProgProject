package Game.GameObjects.Bullets;

import Game.GameObjects.GameObject;
import Game.Physics;

import java.util.List;

public class ExplodeAbleBullet extends BulletObject {
    public ExplodeAbleBullet(double startX, double startY, int width, int height) {
        super(startX, startY, width, height);
        isFixed = false;
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
