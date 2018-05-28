package Game.GameObjects.Items;

import Game.GameObjects.CharacterObjects.CharacterObject;
import Game.GameObjects.CharacterObjects.Player;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class ParachuteItem extends ItemObject {

    public ParachuteItem(double startX, double startY) {
        super(startX, startY);
        try {
            image = ImageIO.read(new File(".\\src\\Game\\Textures\\parachute2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void applyItem(CharacterObject obj) {
        super.applyItem(obj);
        if(obj.isPlayer){
            ((Player) obj).hasParachuteItem = true;
        }
    }
}
