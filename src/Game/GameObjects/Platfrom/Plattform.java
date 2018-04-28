package Game.GameObjects.Platfrom;

import Game.GameObjects.GameObject;

public abstract class Plattform extends GameObject {

    public Plattform(double startX, double startY, int width, int height) {
        super(startX, startY, width, height);
    }


    /*Not call super*/
    @Override
    public void move(double diffSeconds) {
    }

    /*Not call super*/
    @Override
    public void checkCollision() {
    }
}
