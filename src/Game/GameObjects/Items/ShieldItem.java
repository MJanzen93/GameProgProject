package Game.GameObjects.Items;

import Game.GameObjects.CharacterObjects.CharacterObject;

public class ShieldItem extends ItemObject {

    public ShieldItem(double startX, double startY) {
        super(startX, startY);
    }

    @Override
    public void applyItem(CharacterObject obj) {
        super.applyItem(obj);
        obj.hasShield = true;
    }
}
