package Game.GameObjects.Platfrom;

import Game.GameObjects.GameObject;

import java.awt.*;

public abstract class Plattform extends GameObject {

    public Plattform(double startX, double startY, int width, int height) {
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
    }

    @Override
    public void checkCollision() {
        if(!isFixed && isSolid){
            super.checkCollision();
        }
    }
}
