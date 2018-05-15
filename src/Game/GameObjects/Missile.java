package Game.GameObjects;

import Game.GameObjects.Bullets.Explosion;
import Game.Physics;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class Missile extends GameObject {

    public Missile(double startX, double startY) {
        super(startX, startY, 30, 30);
        isFixed = false;
        isSolid = false;
        destructible = true;
        hp = 1;
        explodable = true;
        try {
            image = ImageIO.read(new File(".\\src\\Game\\Textures\\bomb.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void move(double diffSeconds) {
        super.move(diffSeconds);
    }

    @Override
    public void checkCollision() {
        List<GameObject> collidingObjects = Physics.getCollisions(this);
        if(collidingObjects.size() > 0){
            hp = 0;
            Explosion explosion = new Explosion(x+ width/2,y + height/2,200, true);
            world.fixedObjects.add(explosion);
            explosion.explode();
        }
    }
}
