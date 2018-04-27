package Game.GameObjects.Items;

import Game.GameObjects.CharacterObjects;
import Game.GameObjects.GameObject;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public abstract class ItemObject extends GameObject {

    public static int width = 20;
    public static int height = 20;

    private static Random random;

    BufferedImage image;

    public ItemObject(double startX, double startY) {
        super(startX, startY, width, height);
        random = new Random();
        isItem = true;
        isSolid = true;
    }

    public static ItemObject createRandomItem(){
        int num = random.nextInt(5);
        switch (num){
            case 0:
                return new HealthItem(0,0);
            case 1:
                return new HealthItem(0,0);
            case 2:
                return new HealthItem(0,0);
            case 3:
                return new HealthItem(0,0);
            case 4:
                return new HealthItem(0,0);
        }
        return new DoubleDamageItem(0,0);
    }

    public static ItemObject createHealthItem(){
        return new HealthItem(0,0);
    }

    //todo create more Items

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

    public void applyItem(CharacterObjects obj){
        hp = 0;
    }
}
