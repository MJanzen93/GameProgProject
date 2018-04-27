package Game.GameObjects;

import Game.GameObjects.Enemies.EnemyObject;

public class SWATTeamMate extends GameObject {

    private double bulletCooldown =0.5;
    private EnemyObject enemyObject;

    public SWATTeamMate(double startX, double startY, int width, int height) {
        super(startX, startY, width, height);
        isSolid = true;
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
                bulletCooldown = 0.5;
                enemyObject = searchEnemy();
                if(enemyObject != null){
                    shootBullet();
                }

            }
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
