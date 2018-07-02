package Game.GameObjects.Bullets;

import Game.GameObjects.GameObject;
import Game.GameObjects.SWATTeamMate;
import Game.GameObjects.Platfrom.FixedPlatform;
import Game.Physics;

import java.awt.*;
import java.util.List;

public class ShootBullet extends BulletObject {

    private double[] oldXArr;
    private double[] oldYArr;

    public double oldWidth = 0;
    public double oldHeight = 0;

    public ShootBullet(double startX, double startY, int width, int height) {
        super(startX, startY, width, height);
        oldXArr = new double[]{x, x, x, x, x, x, x, x, x, x};
        oldYArr = new double[]{y, y, y, y, y, y, y, y, y, y};
    }

    @Override
    public void move(double diffSeconds) {
        this.oldX = this.x;
        this.oldY = this.y;

        super.move(diffSeconds);
        saveOldPosition();
    }

    @Override
    public void checkCollision() {
        if(isSolid){
            List<GameObject> collidingObjects = Physics.getCollisions(this);
            if(collidingObjects.size() > 0 && collidingObjects.get(0).isSolid&& ((isPlayerBullet && !collidingObjects.get(0).isPlayer)|| (!isPlayerBullet && !collidingObjects.get(0).isEnemy
            		&& (collidingObjects.get(0).isPlayer|| collidingObjects.get(0).isSolid)))
                    && !collidingObjects.get(0).isItem&&!(isPlayerBullet && collidingObjects.get(0) instanceof SWATTeamMate)) {
                hp = 0;
                if(collidingObjects.get(0) instanceof FixedPlatform) {

                    FixedPlatform collidingPlatform = (FixedPlatform) collidingObjects.get(0);
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

                    /*if(!hitDir.equals("")) {
                        world.fixedObjects.add(new Spark(x+ width/2,y + height/2, xSpeed, ySpeed, collidingPlatform, hitDir));
                        world.fixedObjects.add(new Spark(x+ width/2,y + height/2, xSpeed, ySpeed, collidingPlatform, hitDir));
                        world.fixedObjects.add(new Spark(x+ width/2,y + height/2, xSpeed, ySpeed, collidingPlatform, hitDir));
                        world.fixedObjects.add(new Spark(x+ width/2,y + height/2, xSpeed, ySpeed, collidingPlatform, hitDir));
                        world.fixedObjects.add(new Spark(x+ width/2,y + height/2, xSpeed, ySpeed, collidingPlatform, hitDir));
                        world.fixedObjects.add(new Spark(x+ width/2,y + height/2, xSpeed, ySpeed, collidingPlatform, hitDir));
                        world.fixedObjects.add(new Spark(x+ width/2,y + height/2, xSpeed, ySpeed, collidingPlatform, hitDir));
                        world.fixedObjects.add(new Spark(x+ width/2,y + height/2, xSpeed, ySpeed, collidingPlatform, hitDir));
                        world.fixedObjects.add(new Spark(x+ width/2,y + height/2, xSpeed, ySpeed, collidingPlatform, hitDir));
                        world.fixedObjects.add(new Spark(x+ width/2,y + height/2, xSpeed, ySpeed, collidingPlatform, hitDir));
                        world.fixedObjects.add(new Spark(x+ width/2,y + height/2, xSpeed, ySpeed, collidingPlatform, hitDir));
                        world.fixedObjects.add(new Spark(x+ width/2,y + height/2, xSpeed, ySpeed, collidingPlatform, hitDir));
                        world.fixedObjects.add(new Spark(x+ width/2,y + height/2, xSpeed, ySpeed, collidingPlatform, hitDir));
                        world.fixedObjects.add(new Spark(x+ width/2,y + height/2, xSpeed, ySpeed, collidingPlatform, hitDir));
                        world.fixedObjects.add(new Spark(x+ width/2,y + height/2, xSpeed, ySpeed, collidingPlatform, hitDir));
                        world.fixedObjects.add(new Spark(x+ width/2,y + height/2, xSpeed, ySpeed, collidingPlatform, hitDir));
                        world.fixedObjects.add(new Spark(x+ width/2,y + height/2, xSpeed, ySpeed, collidingPlatform, hitDir));
                        world.fixedObjects.add(new Spark(x+ width/2,y + height/2, xSpeed, ySpeed, collidingPlatform, hitDir));
                        world.fixedObjects.add(new Spark(x+ width/2,y + height/2, xSpeed, ySpeed, collidingPlatform, hitDir));
                        world.fixedObjects.add(new Spark(x+ width/2,y + height/2, xSpeed, ySpeed, collidingPlatform, hitDir));
                        world.fixedObjects.add(new Spark(x+ width/2,y + height/2, xSpeed, ySpeed, collidingPlatform, hitDir));
                        world.fixedObjects.add(new Spark(x+ width/2,y + height/2, xSpeed, ySpeed, collidingPlatform, hitDir));
                        world.fixedObjects.add(new Spark(x+ width/2,y + height/2, xSpeed, ySpeed, collidingPlatform, hitDir));
                        world.fixedObjects.add(new Spark(x+ width/2,y + height/2, xSpeed, ySpeed, collidingPlatform, hitDir));
                    }*/

                }


                if(collidingObjects.get(0).destructible) {
                    collidingObjects.get(0).hp -= damage;
                }
            }
        }
    }

    @Override
    public void draw(Graphics2D graphics) {
        float alpha = 0f;


        for(int i = this.oldXArr.length-1; i >= 0; i--) {
            alpha += 0.03;
            graphics.setColor(new Color((float)COLOR.getRed()/255, (float)COLOR.getGreen()/255, (float)COLOR.getBlue()/255, alpha));
            graphics.fillRect((int)(oldXArr[i]-world.worldPartX), (int)(oldYArr[i]-world.worldPartY), width, height);
            graphics.setColor(new Color(0, 0, 0, alpha));
            graphics.drawRect((int)(oldXArr[i]-world.worldPartX), (int)(oldYArr[i]-world.worldPartY), width, height);
        }
        graphics.setColor(Color.RED);
        graphics.fillRect(1000, -200, 30, 30);

        super.draw(graphics);
    }

    public void saveOldPosition() {

        for(int i = this.oldXArr.length-1; i > 0; i--) {
            this.oldXArr[i] = this.oldXArr[i-1] + ((this.x - this.oldX)/3);
            this.oldYArr[i] = this.oldYArr[i-1] + ((this.y - this.oldY)/3);
        }
        this.oldXArr[0] = this.oldX + ((this.x - this.oldX)/3);
        this.oldYArr[0] = this.oldY + ((this.y - this.oldY)/3);



        this.oldWidth = this.width;
        this.oldHeight = this.height;
    }
}
