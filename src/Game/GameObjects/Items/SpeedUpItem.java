package Game.GameObjects.Items;

import Game.GameObjects.Player;

import java.awt.*;

public class SpeedUpItem extends ItemObject {
    public SpeedUpItem(double startX, double startY) {
        super(startX, startY);
        setColor(Color.yellow);
    }

    @Override
    public void applyItem(Player player) {
        super.applyItem(player);
        player.xForce = 500;
    }
}
