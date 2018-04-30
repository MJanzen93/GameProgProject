package Game.GameObjects.Items;

import Game.GameObjects.CharacterObjects.CharacterObject;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class ShieldItem extends ItemObject {

    public ShieldItem(double startX, double startY) {
        super(startX, startY);
        try {
            image = ImageIO.read(new File(".\\src\\Game\\Textures\\shield.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void applyItem(CharacterObject obj) {
        super.applyItem(obj);
        obj.hasShield = true;
        obj.destructible = false;
    }
}
