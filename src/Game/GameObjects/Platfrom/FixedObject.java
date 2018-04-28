package Game.GameObjects.Platfrom;


import Game.GameObjects.GameObject;

import java.awt.*;

//Fixed objects: no gravity is applied (but they can move) but they have a collision Box
@Deprecated
public class FixedObject extends GameObject {

    public FixedObject(double startX, double startY, int width, int height) {
        super(startX, startY, width, height);
        isFixed = true;
        isSolid = true;
        COLOR = new Color(7, 120, 5);
    }

    @Override
    public void move(double diffSeconds) {
    }
}
