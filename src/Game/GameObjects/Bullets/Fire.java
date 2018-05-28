package Game.GameObjects.Bullets;

import Game.GameObjects.GameObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Fire extends GameObject {

    private Image[] image;
    private int imageC = 0;
    private double delay = 0.04;

    public Fire(double startX, double startY) {
        super(startX, startY, 0, 0);
        hasCollision = false;
        isSolid = false;
        isFixed = true;
        destructible = false;
        image = new Image[32];

        try {
            image[0] = ImageIO.read(new File(".\\src\\Game\\Textures\\FireAnimation\\frame_00_delay-0.04s.gif"));
            image[1] = ImageIO.read(new File(".\\src\\Game\\Textures\\FireAnimation\\frame_01_delay-0.04s.gif"));
            image[2] = ImageIO.read(new File(".\\src\\Game\\Textures\\FireAnimation\\frame_02_delay-0.04s.gif"));
            image[3] = ImageIO.read(new File(".\\src\\Game\\Textures\\FireAnimation\\frame_03_delay-0.04s.gif"));
            image[4] = ImageIO.read(new File(".\\src\\Game\\Textures\\FireAnimation\\frame_04_delay-0.04s.gif"));
            image[5] = ImageIO.read(new File(".\\src\\Game\\Textures\\FireAnimation\\frame_05_delay-0.04s.gif"));
            image[6] = ImageIO.read(new File(".\\src\\Game\\Textures\\FireAnimation\\frame_06_delay-0.04s.gif"));
            image[7] = ImageIO.read(new File(".\\src\\Game\\Textures\\FireAnimation\\frame_07_delay-0.04s.gif"));
            image[8] = ImageIO.read(new File(".\\src\\Game\\Textures\\FireAnimation\\frame_08_delay-0.04s.gif"));
            image[9] = ImageIO.read(new File(".\\src\\Game\\Textures\\FireAnimation\\frame_09_delay-0.04s.gif"));
            image[10] = ImageIO.read(new File(".\\src\\Game\\Textures\\FireAnimation\\frame_10_delay-0.04s.gif"));
            image[11] = ImageIO.read(new File(".\\src\\Game\\Textures\\FireAnimation\\frame_11_delay-0.04s.gif"));
            image[12] = ImageIO.read(new File(".\\src\\Game\\Textures\\FireAnimation\\frame_12_delay-0.04s.gif"));
            image[13] = ImageIO.read(new File(".\\src\\Game\\Textures\\FireAnimation\\frame_13_delay-0.04s.gif"));
            image[14] = ImageIO.read(new File(".\\src\\Game\\Textures\\FireAnimation\\frame_14_delay-0.04s.gif"));
            image[15] = ImageIO.read(new File(".\\src\\Game\\Textures\\FireAnimation\\frame_15_delay-0.04s.gif"));
            image[16] = ImageIO.read(new File(".\\src\\Game\\Textures\\FireAnimation\\frame_16_delay-0.04s.gif"));
            image[17] = ImageIO.read(new File(".\\src\\Game\\Textures\\FireAnimation\\frame_17_delay-0.04s.gif"));
            image[18] = ImageIO.read(new File(".\\src\\Game\\Textures\\FireAnimation\\frame_18_delay-0.04s.gif"));
            image[19] = ImageIO.read(new File(".\\src\\Game\\Textures\\FireAnimation\\frame_19_delay-0.04s.gif"));
            image[20] = ImageIO.read(new File(".\\src\\Game\\Textures\\FireAnimation\\frame_20_delay-0.04s.gif"));
            image[21] = ImageIO.read(new File(".\\src\\Game\\Textures\\FireAnimation\\frame_21_delay-0.04s.gif"));
            image[22] = ImageIO.read(new File(".\\src\\Game\\Textures\\FireAnimation\\frame_22_delay-0.04s.gif"));
            image[23] = ImageIO.read(new File(".\\src\\Game\\Textures\\FireAnimation\\frame_23_delay-0.04s.gif"));
            image[24] = ImageIO.read(new File(".\\src\\Game\\Textures\\FireAnimation\\frame_24_delay-0.04s.gif"));
            image[25] = ImageIO.read(new File(".\\src\\Game\\Textures\\FireAnimation\\frame_25_delay-0.04s.gif"));
            image[26] = ImageIO.read(new File(".\\src\\Game\\Textures\\FireAnimation\\frame_26_delay-0.04s.gif"));
            image[27] = ImageIO.read(new File(".\\src\\Game\\Textures\\FireAnimation\\frame_27_delay-0.04s.gif"));
            image[28] = ImageIO.read(new File(".\\src\\Game\\Textures\\FireAnimation\\frame_28_delay-0.04s.gif"));
            image[29] = ImageIO.read(new File(".\\src\\Game\\Textures\\FireAnimation\\frame_29_delay-0.04s.gif"));
            image[30] = ImageIO.read(new File(".\\src\\Game\\Textures\\FireAnimation\\frame_30_delay-0.04s.gif"));
            image[31] = ImageIO.read(new File(".\\src\\Game\\Textures\\FireAnimation\\frame_31_delay-0.04s.gif"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    int i = 5;

    @Override
    public void move(double diffSeconds) {
        super.move(diffSeconds);
        delay -= diffSeconds;

        if(delay <= 0 && imageC < 32){
            delay = 0.04;
            imageC++;
        }
        if(imageC >= 32){
           imageC = 0;
        }
    }

    @Override
    public void checkCollision() {
        super.checkCollision();
    }


    @Override
    public void draw(Graphics graphics) {
        int x = (int) (this.x - world.worldPartX);
        int y = (int) (this.y - world.worldPartY);

        graphics.drawImage(image[imageC], x, y, 150, 82, null);

    }
}
