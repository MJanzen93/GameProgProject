package Game.GameObjects.Items;

import Game.GameObjects.MovableObject;
import Game.GameObjects.Player;

import java.awt.*;

public class RapidFireItem extends ItemObject {
    public RapidFireItem(double startX, double startY) {
        super(startX, startY);
        COLOR = new Color(226, 211, 0);
    }

    @Override
    public void applyItem(Player player) {
        super.applyItem(player);
        player.bulletCooldown = 0.01;
    }
}
