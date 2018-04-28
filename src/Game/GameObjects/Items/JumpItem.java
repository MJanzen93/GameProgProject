package Game.GameObjects.Items;

import Game.GameObjects.CharacterObjects.CharacterObject;

import java.awt.*;

public class JumpItem extends ItemObject {
    public JumpItem(double startX, double startY) {
        super(startX, startY);
        setColor(Color.MAGENTA);
    }

    @Override
    public void applyItem(CharacterObject obj) {
        super.applyItem(obj);
        obj.jumpForce = 1000;
    }

    @Override
    public void checkCollision() {
        super.checkCollision();
    }
}
