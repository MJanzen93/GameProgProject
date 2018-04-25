package Game.GameObjects.Items;

import Game.GameObjects.Player;

import java.awt.*;

public class DoubleDamageItem extends ItemObject {
    public DoubleDamageItem(double startX, double startY) {
        super(startX, startY);
        setColor(Color.CYAN);
    }

    @Override
    public void applyItem(Player player) {
        super.applyItem(player);
        player.damage = 10;
    }
}
