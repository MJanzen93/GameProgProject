package Game.GameObjects.Items;

import Game.GameObjects.CharacterObjects.CharacterObject;

import java.awt.*;

public class SpeedUpItem extends ItemObject {
    public SpeedUpItem(double startX, double startY) {
        super(startX, startY);
        setColor(Color.yellow);
    }

    @Override
    public void applyItem(CharacterObject obj) {
        super.applyItem(obj);
        obj.xForce = 500;
    }
}
