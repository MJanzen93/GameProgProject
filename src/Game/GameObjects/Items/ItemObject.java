package Game.GameObjects.Items;

import Game.GameObjects.GameObject;
import Game.GameObjects.MovableObject;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public abstract class ItemObject extends MovableObject {

    public static int width = 20;
    public static int height = 20;

    private static Random random;

    BufferedImage image;

    public ItemObject(double startX, double startY) {
        super(startX, startY, width, height);
        random = new Random();
        isItem = true;
    }

    public static ItemObject createItem(){
        int num = random.nextInt(5);
        return new DoubleDamageItem(0,0);
    }

    @Override
    public void move(double diffSeconds) {
        super.move(diffSeconds);
    }

    @Override
    public void draw(Graphics graphics) {
        if(image == null){
            int x = (int) (this.x - world.worldPartX);
            int y = (int) (this.y - world.worldPartY);

            graphics.setColor(COLOR);
            graphics.fillRect(x, y, width, height);
            graphics.setColor(Color.BLACK);
            graphics.drawRect(x, y, width, height);
        }
    }
}
