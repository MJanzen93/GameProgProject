package Game.GameObjects.BackgroundObjects;

import Game.GameObjects.GameObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class BackgroundObject extends GameObject {

    public BackgroundObject(double startX, double startY, int width, int height, String imagePath) {
        super(startX, startY, width, height);
        try {
            image = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BackgroundObject(double startX, double startY, String imagePath) {
        super(startX, startY, 0, 0);
        try {
            image = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw(Graphics graphics) {
        int x = (int) (this.x - world.worldPartX);
        int y = (int) (this.y - world.worldPartY);

        if(width != 0 && height != 0){
            graphics.drawImage(image, x, y, width, height, null);
        }else
            graphics.drawImage(image, x, y, null);
    }

    @Override
    public void move(double diffSeconds) {
    }

    @Override
    public void checkCollision() {
    }
}
