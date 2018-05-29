package Game;

import Game.GameObjects.GameObject;

import java.awt.*;

public class Animation{

    private Image[] images;
    private int numImages;
    private int currImage = 0;

    private double currSpeed;
    public double speed = 0.04;
    public boolean repeat = false;

    private GameObject object;

    public Animation(Image[] images, GameObject gameObject){
        this.images = images;
        numImages = images.length;
        object = gameObject;
        currSpeed = speed;
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
    }

    public void draw(Graphics graphics, int x, int y, int width, int height){
        graphics.drawImage(images[currImage],x, y, width, height, null);
    }
}
