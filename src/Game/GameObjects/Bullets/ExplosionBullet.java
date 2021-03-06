package Game.GameObjects.Bullets;


import java.awt.*;

public class ExplosionBullet extends BulletObject {

    public ExplosionBullet(double startX, double startY, int width, int height) {
        super(startX, startY, width, height);
    }

    public void move(double diffSeconds) {
        super.move(diffSeconds);
    }

    public void setIsPlayerBullet(boolean isPlayerBullet) {
        this.isPlayerBullet = isPlayerBullet;
    }

    @Override
    public void draw(Graphics2D graphics) {
    }
}
