package Game.GameObjects;


import java.awt.*;

//Fixed objects: no gravity is applied (but they can move) but they have a collision Box
public class FixedObject extends GameObject {

    public FixedObject(double startX, double startY, int width, int height) {
        super(startX, startY, width, height);
        isFixed = true;
        isSolid = true;
        COLOR = new Color(7, 120, 5);
    }
}
