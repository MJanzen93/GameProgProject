package Game.GameObjects.BackgroundObjects;

import Game.GameObjects.GameObject;

public abstract class BackgroundObject extends GameObject {

    public BackgroundObject(double startX, double startY, int width, int height) {
        super(startX, startY, width, height);
    }

    @Override
    public void move(double diffSeconds) {
    }

    @Override
    public void checkCollision() {
    }
}
