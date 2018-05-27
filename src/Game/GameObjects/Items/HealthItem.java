package Game.GameObjects.Items;

import Game.GameObjects.CharacterObjects.CharacterObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class HealthItem extends ItemObject{

    public HealthItem(double startX, double startY) {
        super(startX, startY);
        setColor(Color.red);
        try {
            image = ImageIO.read(new File(".\\src\\Game\\Textures\\health.png"));
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

    @Override
    public void applyItem(CharacterObject obj) {
        super.applyItem(obj);
        obj.hp = obj.maxHP;
    }
}
