package Game.GameObjects.Items;

import Game.GameObjects.CharacterObjects.CharacterObject;

import java.awt.*;

public class RapidFireItem extends ItemObject {
    public RapidFireItem(double startX, double startY) {
        super(startX, startY);
        COLOR = new Color(226, 211, 0);
    }

    @Override
    public void applyItem(CharacterObject obj) {
        super.applyItem(obj);
        obj.bulletCooldownfinal = 0.1;
    }
}
