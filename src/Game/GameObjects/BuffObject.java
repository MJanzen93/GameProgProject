package Game.GameObjects;

import java.awt.*;

public class BuffObject extends ItemObject {
    public BuffObject(double startX, double startY, int width, int height) {
        super(startX, startY);
        isBuff = true;
        COLOR = new Color(226, 211, 0);
    }

    public void buffPlayer() {
        world.player.bulletCooldown = 0.1;
    }
}
