package Game.GameObjects.Bullets;

import Game.GameObjects.GameObject;
import Game.GameObjects.Platfrom.FixedPlattform;
import Game.Physics;

import java.util.List;

public class ShootBullet extends BulletObject {

    public ShootBullet(double startX, double startY, int width, int height) {
        super(startX, startY, width, height);
    }

    @Override
    public void move(double diffSeconds) {
        super.move(diffSeconds);
    }

    @Override
    public void checkCollision() {
        if(isSolid){
            List<GameObject> collidingObjects = Physics.getCollisions(this);
            if(collidingObjects.size() > 0 && collidingObjects.get(0).isSolid
                    && ((isPlayerBullet && !collidingObjects.get(0).isPlayer)
                    || (!isPlayerBullet && !collidingObjects.get(0).isEnemy && (collidingObjects.get(0).isPlayer
                    || collidingObjects.get(0).isSolid)))
                    && !collidingObjects.get(0).isItem) {
                hp = 0;
                if(collidingObjects.get(0) instanceof FixedPlattform) {

                    FixedPlattform collidingPlatform = (FixedPlattform) collidingObjects.get(0);
                    String hitDir = "";
                    if(collidingPlatform.x > oldX + width && collidingPlatform.platformType.contains("Left")) {
                        hitDir = "Left";
                    } else if(collidingPlatform.x + collidingPlatform.width < oldX && collidingPlatform.platformType.contains("Right")) {
                        hitDir = "Right";
                    } else if(collidingPlatform.y > oldY + height && collidingPlatform.platformType.contains("Top")) {
                        hitDir = "Top";
                    } else if(collidingPlatform.y + collidingPlatform.height < oldY && collidingPlatform.platformType.contains("Bottom")) {
                        hitDir = "Bottom";
                    }
                    /*world.fixedObjects.add(new Spark(x+ width/2,y + height/2, xSpeed, ySpeed, collidingPlatform, hitDir));
                    world.fixedObjects.add(new Spark(x+ width/2,y + height/2, xSpeed, ySpeed, collidingPlatform, hitDir));
                    world.fixedObjects.add(new Spark(x+ width/2,y + height/2, xSpeed, ySpeed, collidingPlatform, hitDir));
                    world.fixedObjects.add(new Spark(x+ width/2,y + height/2, xSpeed, ySpeed, collidingPlatform, hitDir));*/
                }


                if(collidingObjects.get(0).destructible) {
                    collidingObjects.get(0).hp -= damage;
                }
            }
        }
    }
}
