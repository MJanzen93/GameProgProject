package Game.GameObjects.Bullets;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import Game.Animation;
import Game.Physics;
import Game.GameObjects.GameObject;
import Game.GameObjects.CharacterObjects.CharacterObject;

public class Fire extends GameObject {

    private Image[] image;
    private double timeout = 0.1;

    public Fire(double startX, double startY) {
        super(startX, startY+32, 150, 50);
        hasCollision = true;
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
        animation = new Animation(image, this);
        animation.repeat = true;
    }

    Animation animation;

    @Override
    public void move(double diffSeconds) {
        super.move(diffSeconds);

        animation.move(diffSeconds);

        timeout -= diffSeconds;
    }

    @Override
    public void checkCollision() {
        if(timeout > 0){
            return;
        }
        timeout = 0.1;
        List<GameObject> collisions =Physics.getCollisions(this);

        for (int i = 0; i < collisions.size(); i++){
            if(collisions.get(i).isPlayer || collisions.get(i).isEnemy){
                CharacterObject player = (CharacterObject) collisions.get(i);
                player.hp--;
            }
        }
    }

    @Override
    public void draw(Graphics graphics) {
        int x = (int) (this.x - world.worldPartX);
        int y = (int) (this.y - world.worldPartY);

        animation.draw(graphics,x,y-32, 150, 82);
    }
}
