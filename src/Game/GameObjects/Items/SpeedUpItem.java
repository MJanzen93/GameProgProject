package Game.GameObjects.Items;

import Game.GameObjects.CharacterObjects.CharacterObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class SpeedUpItem extends ItemObject {
    public SpeedUpItem(double startX, double startY) {
        super(startX, startY);
        setColor(Color.yellow);
        try {
            image = ImageIO.read(new File(".\\src\\Game\\Textures\\speed.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void applyItem(CharacterObject obj) {
        super.applyItem(obj);
        obj.xForce = 500;
    }
}
