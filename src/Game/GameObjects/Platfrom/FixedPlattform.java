package Game.GameObjects.Platfrom;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class FixedPlattform extends Plattform {

    public FixedPlattform(double startX, double startY, int width, int height, String imagePath) {
        super(startX, startY, width, height);
        try {
            image = ImageIO.read(new File(".\\src\\Game\\Textures\\testPlatt.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void move(double diffSeconds) {
        super.move(diffSeconds);
    }

    @Override
    public void checkCollision() {
        if(!isFixed && isSolid){
            super.checkCollision();
        }
    }

    //Tiles for platforms 50 x 50
    //width and height is the collision box
    public void draw(Graphics graphics){
        int x = (int) (this.x - world.worldPartX);
        int y = (int) (this.y - world.worldPartY);
        graphics.drawImage(image, x, y, 50, 50, null, null);
    }
}
