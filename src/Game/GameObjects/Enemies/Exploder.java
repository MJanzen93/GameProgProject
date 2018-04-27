package Game.GameObjects.Enemies;

import Game.GameObjects.Explosion;

public class Exploder extends  EnemyObject {
    public Exploder(double startX, double startY, int width, int height) {
        super(startX, startY, width, height);
    }

    @Override
    public void move(double diffSeconds) {
        super.move(diffSeconds);

        double distanceToPlayer = world.player.x - x;

        if(Math.abs(distanceToPlayer) < 400 || maxHP > hp) {
            if(distanceToPlayer > 20 ) {
                xSpeed = 100;
            } else if(distanceToPlayer < -20) {
                xSpeed = -100;
            } else{
                shootBullet();
            }
        }

    }

    public void shootBullet() {
        Explosion explosion = new Explosion(x+width/2,y+height/2,0,0);
        hp = 0;
        explosion.explode();
    }
}