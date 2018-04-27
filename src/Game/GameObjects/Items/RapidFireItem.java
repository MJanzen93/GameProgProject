package Game.GameObjects.Items;

import Game.GameObjects.CharacterObjects;

import java.awt.*;

public class RapidFireItem extends ItemObject {
    public RapidFireItem(double startX, double startY) {
        super(startX, startY);
        COLOR = new Color(226, 211, 0);
    }

    @Override
    public void applyItem(CharacterObjects obj) {
        super.applyItem(obj);
        obj.bulletCooldownfinal = 0.1;
    }

    @Override
    public void move(double diffSeconds) {
        super.move(diffSeconds);
    }
}
