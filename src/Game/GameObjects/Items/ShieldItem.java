package Game.GameObjects.Items;

import Game.GameObjects.CharacterObjects;
import Game.GameObjects.Player;

public class ShieldItem extends ItemObject {

    public ShieldItem(double startX, double startY) {
        super(startX, startY);
    }

    @Override
    public void applyItem(CharacterObjects obj) {
        super.applyItem(obj);
        //player.hasShield = true;
        //player.shieldHp = player.width/2;
    }
}
