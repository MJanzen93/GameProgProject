package Game.GameObjects.Items;

import Game.GameObjects.MovableObject;

import java.awt.*;

public class JumpItem extends ItemObject {
    public JumpItem(double startX, double startY) {
        super(startX, startY);
        setColor(Color.MAGENTA);
    }
}
