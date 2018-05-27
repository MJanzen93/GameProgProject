package Game.GameObjects.Platfrom;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class FixedPlattform extends Plattform {

    public FixedPlattform(double startX, double startY, int width, int height, String imagePath) {
        super(startX, startY, width, height);
        try {
            image = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
        /*Funny haha*/
       // destructible = true;
      //  hp = 5;
       // maxHP = 5;
        
    }

    @Override
    public void move(double diffSeconds) {
        super.move(diffSeconds);
    }

    @Override
    public void checkCollision() {
        if (!isFixed && isSolid) {
            super.checkCollision();
        }
    }

    public void draw(Graphics graphics){
        int x = (int) (this.x - world.worldPartX);
        int y = (int) (this.y - world.worldPartY);

        if(image == null){
            graphics.setColor(COLOR);
            graphics.fillRect(x, y, width, height);
            graphics.setColor(Color.BLACK);
            graphics.drawRect(x, y, width, height);
        }else
            graphics.drawImage(image, x, y, width, height, null, null);
    }
}
