package Game.GameObjects.Items;

import Game.GameObjects.MovableObject;
import Game.GameObjects.Player;

import java.awt.*;

public class JumpItem extends ItemObject {
    public JumpItem(double startX, double startY) {
        super(startX, startY);
        setColor(Color.MAGENTA);
    }

    @Override
    public void applyItem(Player player) {
        super.applyItem(player);
        player.jumpForce = 1000;
    }
}
