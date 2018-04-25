package Game.GameObjects.Items;

import Game.GameObjects.Player;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
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
        if(!isFixed){
            super.move(diffSeconds);
        }
    }

    @Override
    public void draw(Graphics graphics) {
        super.draw(graphics);
        int x = (int) (this.x - world.worldPartX);
        int y = (int) (this.y - world.worldPartY);
        graphics.drawImage(image, x, y, null, null);
    }

    @Override
    public void applyItem(Player player) {
        super.applyItem(player);
        player.hp = player.maxHP;
    }
}
