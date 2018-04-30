package Game.GameObjects.Items;

import Game.GameObjects.CharacterObjects.CharacterObject;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class MissileItem extends ItemObject {

    public MissileItem(double startX, double startY) {
        super(startX, startY);
        try {
            image = ImageIO.read(new File(".\\src\\Game\\Textures\\bomb.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void applyItem(CharacterObject obj) {
        super.applyItem(obj);
        obj.missile++;
    }
}
