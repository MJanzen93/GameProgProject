package Game.GameObjects.Items;

import Game.GameObjects.GameObject;
import Game.GameObjects.MovableObject;

import java.util.Random;

public abstract class ItemObject extends MovableObject {

    public static int width = 20;
    public static int height = 20;

    private static Random random;

    public ItemObject(double startX, double startY) {
        super(startX, startY, width, height);
        random = new Random();
        isItem = true;
    }

    public static ItemObject createItem(){
        int num = random.nextInt(5);
        return new DoubleDamageItem(0,0);
    }
}
