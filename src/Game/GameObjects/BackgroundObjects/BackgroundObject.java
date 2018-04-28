package Game.GameObjects.BackgroundObjects;

import Game.GameObjects.Platfrom.FixedObject;

public abstract class BackgroundObject extends FixedObject {

    public BackgroundObject(double startX, double startY, int width, int height) {
        super(startX, startY, width, height);
    }
}
