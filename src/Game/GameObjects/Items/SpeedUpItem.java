package Game.GameObjects.Items;

import Game.GameObjects.CharacterObjects.CharacterObjects;

import java.awt.*;

public class SpeedUpItem extends ItemObject {
    public SpeedUpItem(double startX, double startY) {
        super(startX, startY);
        setColor(Color.yellow);
    }

    @Override
    public void applyItem(CharacterObjects obj) {
        super.applyItem(obj);
        obj.xForce = 500;
    }
}
