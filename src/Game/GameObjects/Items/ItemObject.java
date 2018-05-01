package Game.GameObjects.Items;

import Game.GameObjects.CharacterObjects.CharacterObject;
import Game.AudioPlayer;
import Game.GameObjects.GameObject;

import java.awt.*;
import java.util.Random;

public abstract class ItemObject extends GameObject {

    public static int width = 30;
    public static int height = 30;

    private static Random random;

    public ItemObject(double startX, double startY) {
        super(startX, startY, width, height);
        random = new Random();
        isItem = true;
        isSolid = true;
        isFixed = false;
        hp = 1;
        destructible = false;
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

    @Override
    public void move(double diffSeconds) {
        super.move(diffSeconds);
    }

    @Override
    public void checkCollision() {
        super.checkCollision();
    }

    public void applyItem(CharacterObject obj){
        hp = 0;
        AudioPlayer.shortSound(".\\src\\Game\\Sounds\\pickup.aiff",0.15);
    }

    @Override
    public void draw(Graphics graphics) {
        super.draw(graphics);
    }
}
