package Game.GameObjects.Items;

import Game.GameObjects.CharacterObjects.CharacterObject;

import java.awt.*;

public class DoubleDamageItem extends ItemObject {
    public DoubleDamageItem(double startX, double startY) {
        super(startX, startY);
        setColor(Color.CYAN);
    }

    @Override
    public void applyItem(CharacterObject obj) {
        super.applyItem(obj);
        obj.damage = 10;
    }
}
