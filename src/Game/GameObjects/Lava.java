package Game.GameObjects;

import java.awt.*;

public class Lava extends FixedObject {
    public Lava(double startX, double startY, int width, int height) {
        super(startX, startY, width, height);
        isSolid = false;
        setColor(new Color(255, 99, 29, 207));
    }
}
