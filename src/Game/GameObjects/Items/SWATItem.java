package Game.GameObjects.Items;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class SWATItem extends ItemObject{

    public SWATItem(double startX, double startY) {
        super(startX, startY);
        try {
            image = ImageIO.read(new File(".\\src\\Game\\Textures\\swat.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
