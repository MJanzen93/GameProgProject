package Game.GameObjects.Bullets;

import Game.Animation;
import Game.GameObjects.CharacterObjects.CharacterObject;
import Game.GameObjects.GameObject;
import Game.Physics;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

public class Fire2 extends GameObject {

    private Image[] image;
    private double timeout = 3;

    private Animation animation;

    private GameObject gObject;

    public Fire2(GameObject gObject) {
        super(gObject.x, gObject.y, gObject.width, gObject.height);
        this.gObject = gObject;
        isSolid = false;
        destructible = false;
        image = new Image[17];

        DecimalFormat format = new DecimalFormat("00");

        try {
            for (int i = 0; i < image.length; i++){
                image[i] = ImageIO.read(new File(".\\src\\Game\\Textures\\Fire2Animation\\Frame_" + format.format(i) + ".png"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        animation = new Animation(image, this);
        animation.repeat = true;
    }

    @Override
    public void move(double diffSeconds) {
        x = gObject.x;
        y = gObject.y;
        animation.update(diffSeconds);

        timeout -= diffSeconds;
        if(timeout < 0) {
            hp = 0;
        }
    }

    @Override
    public void draw(Graphics2D graphics) {
        int x = (int) (this.x - world.worldPartX);
        int y = (int) (this.y - world.worldPartY);
        graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
        animation.draw(graphics,x-10,y-10, width+20, height+10);
        graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }
}
