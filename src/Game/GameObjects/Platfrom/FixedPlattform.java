package Game.GameObjects.Platfrom;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class FixedPlattform extends Plattform {
    public FixedPlattform(double startX, double startY, int width, int height) {
        super(startX, startY, width, height);
        try {
            image = ImageIO.read(new File(".\\src\\Game\\Textures\\platform2.jpeg"));
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
}
