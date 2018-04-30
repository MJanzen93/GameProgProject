package Game.GameObjects.Items;

import Game.GameObjects.CharacterObjects.CharacterObject;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class RapidFireItem extends ItemObject {
    public RapidFireItem(double startX, double startY) {
        super(startX, startY);
        try {
            image = ImageIO.read(new File(".\\src\\Game\\Textures\\rapidFire.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void move(double diffSeconds) {
        super.move(diffSeconds);
    }

    @Override
    public void draw(Graphics graphics) {
        super.draw(graphics);
    }
}
