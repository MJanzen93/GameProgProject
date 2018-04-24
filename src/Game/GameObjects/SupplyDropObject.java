package Game.GameObjects;

import Game.GameObjects.Items.CrafteObject;
import Game.GameObjects.Items.ItemObject;

import java.awt.*;

public class SupplyDropObject extends MovableObject {

    private double fallSpeed = 50;

    public SupplyDropObject(double startX, double startY, int width, int height) {
        super(startX, startY, width, height);
        hasHP = true;
        hp = 10;
        maxHP = 10;
        setColor(Color.ORANGE);
    }

    @Override
    public void move(double diffSeconds) {
        super.move(diffSeconds);
        if(hp <= 0){
            ItemObject item = ItemObject.createItem();
            item.x = x;
            item.y = y;
            world.gameObjects.add(item);
            world.gameObjects.remove(this);
        }
    }
}
