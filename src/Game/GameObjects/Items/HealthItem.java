package Game.GameObjects.Items;

import Game.GameObjects.BulletObject;
import Game.GameObjects.CharacterObjects;
import Game.GameObjects.Player;
import Game.GameObjects.SupplyDropObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class HealthItem extends ItemObject{

    public HealthItem(double startX, double startY) {
        super(startX, startY);
        setColor(Color.red);
        try {
            image = ImageIO.read(new File(".\\src\\Game\\Textures\\Health_Texture.png"));
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
        int x = (int) (this.x - world.worldPartX);
        int y = (int) (this.y - world.worldPartY);
        graphics.drawImage(image, x, y, null, null);
    }

    @Override
    public void applyItem(CharacterObjects obj) {
        super.applyItem(obj);
        obj.hp = obj.maxHP;
    }
}
