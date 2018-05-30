package Game.GameObjects.Bullets;

import Game.GameObjects.GameObject;
import Game.Physics;

import java.awt.*;
import java.util.List;

public abstract class BulletObject extends GameObject {

    public double alfa = 0;
    public double speed = 1000;
    public double range = 2000;
    public boolean isPlayerBullet = false;
    public boolean isEnemyBullet = false;
    public int damage = 1;


    public BulletObject(double startX, double startY, int width, int height) {
        super(startX, startY, width, height);
        destructible = true; // or false does not matter, because bullets do not check collisions with each other
        hp = 1;
        COLOR = new Color(144, 57, 0);

        isSolid = true;

        isFixed = true;
    }

    public void move(double diffSeconds) {
        super.move(diffSeconds);
        range-=speed*diffSeconds;
        if(range < 0) {
            hp = 0;
        }

        xSpeed=Math.cos(alfa)*speed;
        ySpeed=Math.sin(alfa)*speed;
    }

    @Override
    public void checkCollision() {
        if(isSolid){
            List<Game.GameObjects.GameObject> collidingObjects = Physics.getCollisions(this);
            if(collidingObjects.size() > 0 && collidingObjects.get(0).isSolid
                    && ((isPlayerBullet && !collidingObjects.get(0).isPlayer)
                    || (!isPlayerBullet && !collidingObjects.get(0).isEnemy && (collidingObjects.get(0).isPlayer
                    || collidingObjects.get(0).isSolid)))
                    && !collidingObjects.get(0).isItem) {
                hp = 0;

                if(collidingObjects.get(0).destructible) {
                    collidingObjects.get(0).hp -= damage;
                }
            }
        }
    }

    @Override
    public void draw(Graphics2D graphics) {
        super.draw(graphics);
    }
}
