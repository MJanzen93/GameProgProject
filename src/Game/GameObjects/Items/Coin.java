package Game.GameObjects.Items;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Coin extends ItemObject {

    public Coin(double startX, double startY) {
        super(startX, startY);
        setColor(Color.yellow);
        isFixed = true;
        try {
            image = ImageIO.read(new File(".\\src\\Game\\Textures\\coin.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
