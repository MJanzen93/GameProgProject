package Game.GameObjects.Bullets;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

import javax.imageio.ImageIO;

import Game.Animation;
import Game.Physics;
import Game.GameObjects.GameObject;
import Game.GameObjects.CharacterObjects.CharacterObject;

public class Fire extends GameObject {

    private Image[] image;
    private double timeout = 0.1;

    private Animation animation;

    public Fire(double startX, double startY) {
        super(startX, startY+32, 150, 50);
        hasCollision = true;
        isSolid = false;
        isFixed = true;
        destructible = false;
        image = new Image[32];

        DecimalFormat format = new DecimalFormat("00");

        try {
            for (int i = 0; i < image.length; i++){
                image[i] = ImageIO.read(new File(".\\src\\Game\\Textures\\FireAnimation\\frame_" + format.format(i) + "_delay-0.04s.gif"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        animation = new Animation(image, this);
        animation.repeat = true;
    }

    @Override
    public void move(double diffSeconds) {
        super.move(diffSeconds);

        animation.update(diffSeconds);

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
                if(player.destructible){
                    player.hp--;
                    player.castBurningEffect();
                }
            }
        }
    }

    @Override
    public void draw(Graphics2D graphics) {
        int x = (int) (this.x - world.worldPartX);
        int y = (int) (this.y - world.worldPartY);

        animation.draw(graphics,x,y-32, 150, 82);
    }
}
