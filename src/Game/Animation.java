package Game;

import Game.GameObjects.GameObject;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public class Animation{

    private Image[] images;
    private Image[] flippedImages;
    private int numImages;
    private int currImage = 0;

    private double currSpeed;
    public double speed = 0.04;
    public boolean repeat = false;
    public boolean useFlippedImages = false;
    private boolean flip = false;

    private GameObject object;

    public Animation(Image[] images, GameObject gameObject){
        this.images = images;
        this.flippedImages = new Image[images.length];
        numImages = images.length;
        object = gameObject;
        currSpeed = speed;
        AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
        tx.translate(-images[0].getWidth(null), 0);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        for(int i = 0; i < images.length; i++) {
            this.flippedImages[i] = op.filter((BufferedImage) this.images[i], null);
        }

    }

    public void update(double diffSeconds){
        currSpeed -= diffSeconds;

        if(currSpeed <= 0 && currImage < numImages){
            currSpeed = speed;
            currImage++;
        }
        if(currImage >= numImages && !repeat){
            object.hp = 0;
        }else if(currImage >= numImages && repeat){
            currImage = 0;
        }

        if(object.xSpeed < 0){
            flip = true;
        }else if(object.xSpeed > 0){
            flip = false;
        }
    }

    public void draw(Graphics2D graphics, int x, int y, int width, int height){
        if(useFlippedImages){
            if(flip){
                graphics.drawImage(flippedImages[currImage],x, y, width, height, null);
            }else
                graphics.drawImage(images[currImage],x, y, width, height, null);
        } else {
            graphics.drawImage(images[currImage],x, y, width, height, null);
        }
    }
}
