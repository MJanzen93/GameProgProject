package Game.GameObjects.Items;

import Game.GameObjects.CharacterObjects.CharacterObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class JumpItem extends ItemObject {
    public JumpItem(double startX, double startY) {
        super(startX, startY);
        setColor(Color.MAGENTA);
        try {
            image = ImageIO.read(new File(".\\src\\Game\\Textures\\jump.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
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
