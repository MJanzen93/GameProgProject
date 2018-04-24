package Game.GameObjects.Items;

import Game.GameObjects.MovableObject;
import Game.GameObjects.Player;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

public class HealthItem extends StaticItemObject{

    public HealthItem(double startX, double startY) {
        super(startX, startY);
        setColor(Color.red);
        try {
            image = ImageIO.read(new File(".\\src\\Game\\Textures\\Health_Texture.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setHealth(Player player){
        player.hp = player.maxHP;
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
}
