package Game.GameObjects;

import java.awt.*;

public class HealthItem extends ItemObject {

    public HealthItem(double startX, double startY) {
        super(startX, startY);
        isItem = true;
        setColor(Color.MAGENTA);
    }

    @Override
    public void move(double diffSeconds) {
        super.move(diffSeconds);
    }
}
