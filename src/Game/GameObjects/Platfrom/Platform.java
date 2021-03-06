package Game.GameObjects.Platfrom;

import Game.GameObjects.BackgroundObjects.BrokenPart;
import Game.GameObjects.GameObject;

import java.awt.*;

public abstract class Platform extends GameObject {

    public Platform(double startX, double startY, int width, int height) {
        super(startX, startY, width, height);
        isFixed = true;
        isSolid = true;
        explodable = false;
        destructible = false;
        hp = 1;
        COLOR = new Color(7, 120, 5);
    }


    @Override
    public void move(double diffSeconds) {
        super.move(diffSeconds);
        if(hp <= 0) {
            breakApart();
        }
    }

    @Override
    public void checkCollision() {
        super.checkCollision();
    }

    public void breakApart() {
        world.fixedObjects.add(new BrokenPart(this, 1));
        world.fixedObjects.add(new BrokenPart(this, 2));
        world.fixedObjects.add(new BrokenPart(this, 3));
        world.fixedObjects.add(new BrokenPart(this, 4));
    }
}
