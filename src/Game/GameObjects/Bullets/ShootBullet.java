package Game.GameObjects.Bullets;

import Game.Physics;

public class ShootBullet extends BulletObject {

    public ShootBullet(double startX, double startY, int width, int height) {
        super(startX, startY, width, height);
        isSolid = true;
    }

    @Override
    public void move(double diffSeconds) {
        super.move(diffSeconds);
    }
}
