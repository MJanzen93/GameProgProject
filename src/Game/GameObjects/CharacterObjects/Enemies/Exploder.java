package Game.GameObjects.CharacterObjects.Enemies;

import java.awt.Color;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Game.GameObjects.Bullets.Explosion;

public class Exploder extends  EnemyObject {
    public Exploder(double startX, double startY, int width, int height) {
        super(startX, startY, width, height);
        this.COLOR = Color.ORANGE;
       
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

    @Override
    public void checkCollision() {
        super.checkCollision();
    }

    public void shootBullet() {
        Explosion explosion = new Explosion(x+width/2,y+height/2,200, false);
        hp = 0;
        world.fixedObjects.add(explosion);
        explosion.explode();
    }
}
