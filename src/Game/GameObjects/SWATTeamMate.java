package Game.GameObjects;

import Game.GameObjects.Enemies.EnemyObject;

import java.util.List;

public class SWATTeamMate extends MovableObject {

    private double bulletCooldown =0.5;
    private EnemyObject enemyObject;

    public SWATTeamMate(double startX, double startY, int width, int height) {
        super(startX, startY, width, height);
    }

    @Override
    public void move(double diffSeconds) {
        double oldX = x;
        double oldY = y;

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
                bulletCooldown = 0.5;
                enemyObject = searchEnemy();
                if(enemyObject != null){
                    shootBullet();
                }

            }
        }



        x+=xSpeed*diffSeconds;
        y+=ySpeed*diffSeconds;




        List<GameObject> collidingObjects = physics.getCollisions(this);

        for(int i = 0; i < collidingObjects.size(); i++) {
            Game.GameObjects.GameObject collidingObject = collidingObjects.get(i);

            if(collidingObject.isSolid) {
                //check if Enemy is on Object
                if(y + height > collidingObject.y && oldY + height <= collidingObject.y && ySpeed >= 0) {

                    y = collidingObject.y - height;
                    ySpeed = 0;
                    onGround = true;
                    jumping = false;
                }

                //check if Enemy is touching bottom side of object
                if(y < collidingObject.y + collidingObject.height && oldY >= collidingObject.y + collidingObject.height && ySpeed <= 0) {

                    y = collidingObject.y + collidingObject.height;
                    ySpeed *= 0.99;
                }

                //left side
                if(x + width > collidingObject.x && oldX + width <= collidingObject.x && xSpeed >= 0) {
                    x = collidingObject.x - width;
                    xSpeed = 0;
                }

                //right side
                if(x < collidingObject.x + collidingObject.width && oldX >= collidingObject.x + collidingObject.width && xSpeed <= 0) {
                    x = collidingObject.x + collidingObject.width;
                    xSpeed = 0;
                }
            }

        }

        if(collidingObjects.size() == 0) {
            jumping = true;
            onGround = false;
        }

        if(y + height > 760){
            y = 760-height;
            ySpeed = 0;
            onGround = true;
            jumping = false;
        }
    }

    public void shootBullet() {
        Game.GameObjects.BulletObject bullet;

        bullet = new Game.GameObjects.BulletObject(x + width/2, y + height/2, 5, 5);
        bullet.alfa  =  Math.atan2(enemyObject.y - y, enemyObject.x - x);

        bullet.setIsPlayerBullet(false);

        world.gameObjects.add(bullet);
    }

    private EnemyObject searchEnemy(){
        EnemyObject enemyObject = null;
        for(int i = 0; i < world.gameObjects.size(); i++){
            if(world.gameObjects.get(i) instanceof EnemyObject){
                return (EnemyObject) world.gameObjects.get(i);
            }
        }
        return enemyObject;
    }
}
