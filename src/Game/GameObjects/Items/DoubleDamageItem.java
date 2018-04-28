package Game.GameObjects.Items;

import Game.GameObjects.CharacterObjects.CharacterObjects;

import java.awt.*;

public class DoubleDamageItem extends ItemObject {
    public DoubleDamageItem(double startX, double startY) {
        super(startX, startY);
        setColor(Color.CYAN);
    }

    @Override
    public void applyItem(CharacterObjects obj) {
        super.applyItem(obj);
        obj.damage = 10;
    }
}
